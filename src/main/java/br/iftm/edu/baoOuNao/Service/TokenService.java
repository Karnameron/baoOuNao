package br.iftm.edu.baoOuNao.Service;

import br.iftm.edu.baoOuNao.api.mapper.UserMapper;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Component
public class TokenService {

    @Autowired
    private UserMapper userMapper;

    @Value("${api.security.token.secret}")
    private String secret;
    public String gerarToken(Usuario usuario){
        if(!usuario.isAtivo()){
           throw new RuntimeException("Usuário está bloqueado, por gentileza entrar em contato com o administrador!");
        }else {
            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                String token = JWT.create()
                        .withIssuer("baoOuNao")
                        .withSubject(usuario.getLogin())
                        .withClaim("id",usuario.getId())
                        .withClaim("role",usuario.getRole().toString())
                        .withExpiresAt(dataExpiracao())
                        .sign(algorithm);
                return token;
            } catch (JWTCreationException exception){
                throw new RuntimeException("Erro ao gerar token JWT",exception);
            }
        }
    }

    public String getSubject(String tokenJWT){
        String jwtToken = "";
        try{
            var algorithm = Algorithm.HMAC256(secret);
            jwtToken = JWT.require(algorithm)
                    .withIssuer("baoOuNao")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch (JWTVerificationException exception){

        }
        return jwtToken;
    }
    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
