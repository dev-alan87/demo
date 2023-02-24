package io.github.devalan87.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException
        extends ResponseStatusException {

    public InvalidPasswordException() {
        super(HttpStatus.NOT_FOUND, "Username or password not valid.");
    }

    public InvalidPasswordException(String msg) {
        super(HttpStatus.NOT_FOUND, "Username or password not valid.\n"+msg);
    }

}
