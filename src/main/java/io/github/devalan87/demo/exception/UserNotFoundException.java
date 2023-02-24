package io.github.devalan87.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException
        extends ResponseStatusException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Username or e-mail not found");
    }

    public UserNotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, "Username or e-mail not found.\n"+msg);
    }

}
