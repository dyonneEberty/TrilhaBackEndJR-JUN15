package codigocerto.api.trilhabackend.infra.security;

import codigocerto.api.trilhabackend.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
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

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api") //Nome que identifica a aplicacao | Emissor
                    .withSubject(user.getLogin()) //Salva o login no usuario no Token, p/ identificacao na requisicao
                    .withExpiresAt(genExpirationData()) //Tempo de expiracao
                    .sign(algorithm); //Para fazer a assinatura e geracao final
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar o token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject(); //Recupera o usuario decriptografado no token
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token expirou ou é inválido!", exception);
        }
    }

    private Instant genExpirationData(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}










