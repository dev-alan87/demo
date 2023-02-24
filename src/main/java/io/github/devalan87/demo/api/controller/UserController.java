package io.github.devalan87.demo.api.controller;

import io.github.devalan87.demo.api.controller.dto.CredentialsDTO;
import io.github.devalan87.demo.api.controller.dto.TokenDTO;
import io.github.devalan87.demo.api.controller.dto.UserDTO;
import io.github.devalan87.demo.domain.entity.User;
import io.github.devalan87.demo.exception.InvalidPasswordException;
import io.github.devalan87.demo.exception.UserAlreadyRegistered;
import io.github.devalan87.demo.exception.UserNotFoundException;
import io.github.devalan87.demo.security.jwt.service.JwtService;
import io.github.devalan87.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController @RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO signUp(@RequestBody User user) {
        try {
            return userService.saveUser(user).dto();
        } catch (Exception ex) {
            throw new UserAlreadyRegistered();
        }
    }

    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO signIn(@RequestBody CredentialsDTO credentials) {
        User requester = User.builder()
                .username(credentials.getLogin())
                .password(credentials.getPassword())
                .build();
        try {
            UserDetails authenticated = userService.authenticate(requester);
            String token = jwtService.generateToken(authenticated.getUsername());
            return new TokenDTO(authenticated.getUsername(), token);
        } catch (Exception ex) {
            throw new InvalidPasswordException();
        }
    }

}
