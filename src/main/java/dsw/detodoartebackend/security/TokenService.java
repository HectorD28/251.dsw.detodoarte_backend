package dsw.detodoartebackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import dsw.detodoartebackend.entity.Personas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Personas personas) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            System.out.println(personas.getRol()+"parte1..7");
            return JWT.create()
                    .withIssuer("DeTodoArte")
                    .withSubject(personas.getUsername())
                    .withClaim("id", personas.getPersonaId())
                    .withClaim("rol",personas.getRol())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
            
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

        public String getSubject(String token) {
            if (token == null) {
                throw new RuntimeException("Token nulo");
            }
            DecodedJWT verifier = null;
            try {
                Algorithm algorithm = Algorithm.HMAC256(apiSecret);
                verifier = JWT.require(algorithm)
                        .withIssuer("DeTodoArte")  // debe coincidir con generarToken
                        .build()
                        .verify(token);
                return verifier.getSubject();
            } catch (JWTVerificationException exception) {
                System.out.println("Error de verificación JWT: " + exception.toString());
                throw new RuntimeException("Token inválido");
            }
        }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }


}
