package com.eventsphere.event_sphere.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eventsphere.event_sphere.modules.user.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("event-sphere-api")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId().toString())
                    .withClaim("role", user.getRole().name())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating");
        }
    }

    public Map<String, Object> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var verifier = JWT.require(algorithm)
                    .withIssuer("event-sphere-api")
                    .build();

            var decodedJWT = verifier.verify(token);
            String email = decodedJWT.getSubject();
            String id = decodedJWT.getClaim("id").asString();
            String role = decodedJWT.getClaim("role").asString();

            Map<String, Object> claims = new HashMap<>();
            claims.put("email", email);
            claims.put("id", id);
            claims.put("role", role);
            return claims;
        } catch (JWTVerificationException exception) {
            System.err.println("Token verification failed: " + exception.getMessage());
            throw new RuntimeException("Invalid token");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
