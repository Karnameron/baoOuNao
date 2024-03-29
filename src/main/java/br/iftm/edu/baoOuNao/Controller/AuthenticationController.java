package br.iftm.edu.baoOuNao.Controller;

import br.iftm.edu.baoOuNao.Service.TokenService;
import br.iftm.edu.baoOuNao.api.dto.autenticacao.UserLoginDto;
import br.iftm.edu.baoOuNao.api.mapper.UserMapper;
import br.iftm.edu.baoOuNao.domain.model.usuario.Usuario;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping
    //Ajuste Cors
    @CrossOrigin(origins = "*")
    ResponseEntity efetuarLogin(@RequestBody @Valid UserLoginDto dadosLogin){
        var token = new UsernamePasswordAuthenticationToken(dadosLogin.getLogin(), dadosLogin.getSenha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        var tok= String.valueOf(tokenJWT);
        tok = "{ \"token\": \"" + tok + "\" }";
        return ResponseEntity.ok(tok);
    }
}
