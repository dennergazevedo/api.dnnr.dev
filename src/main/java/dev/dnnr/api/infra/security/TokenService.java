package dev.dnnr.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.dnnr.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("dnnr-dev")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.genExpirationDate())
                    .sign(algorithm);
        }catch(JWTCreationException exception){
            throw new RuntimeException("Não foi possível gerar o token", exception);
        }
    }

    public String verifyToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("dnnr-dev")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException exception){
            throw new RuntimeException("Token inválido", exception);
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
}
