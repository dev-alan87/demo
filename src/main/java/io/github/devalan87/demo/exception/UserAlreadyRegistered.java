package io.github.devalan87.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyRegistered
        extends ResponseStatusException {

    public UserAlreadyRegistered() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Username or email already registered");
    }

    public UserAlreadyRegistered(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Username or email already registered\n"+msg);
    }

}
