package com.devalrykemes.forumhub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.devalrykemes.forumhub.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("@{api.security.secret.key}")
    private String secretKey;

    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("login-auth")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId().toString())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex) {
            throw new RuntimeException();
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("login-auth")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
