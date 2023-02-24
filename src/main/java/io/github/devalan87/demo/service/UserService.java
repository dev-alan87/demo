package io.github.devalan87.demo.service;

import io.github.devalan87.demo.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService
        extends UserDetailsService {

    User saveUser(User user);
    User findUser(String login);
    UserDetails authenticate(User user);

}
