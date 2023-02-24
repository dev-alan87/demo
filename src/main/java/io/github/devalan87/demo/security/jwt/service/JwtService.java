package io.github.devalan87.demo.security.jwt.service;

import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    String generateToken(String username);
    boolean isValidToken(String token);
    String getUsername(String token);

}
