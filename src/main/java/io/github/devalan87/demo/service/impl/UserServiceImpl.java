package io.github.devalan87.demo.service.impl;

import io.github.devalan87.demo.domain.entity.User;
import io.github.devalan87.demo.domain.repository.UserRepository;
import io.github.devalan87.demo.exception.InvalidPasswordException;
import io.github.devalan87.demo.exception.UserAlreadyRegistered;
import io.github.devalan87.demo.exception.UserNotFoundException;
import io.github.devalan87.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public User saveUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            return repository.saveAndFlush(user);
        } catch (Exception ex) {
            throw new UserAlreadyRegistered();
        }
    }

    @Override
    public User findUser(String login) {
        return repository.findByUsername(login)
                .orElseGet(() -> repository.findByEmail(login)
                        .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDetails authenticate(User user) {
        UserDetails details = loadUserByUsername(user.getUsername());
        if(encoder.matches(
                user.getPassword(),
                details.getPassword()
        ) && details.isEnabled()) {
            return details;
        }
        throw new InvalidPasswordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return findUser(username).getDetails();
    }
}
