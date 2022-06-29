package br.com.springSecurity.springTutorial.controller;

import br.com.springSecurity.springTutorial.DTO.TokenDto;
import br.com.springSecurity.springTutorial.Entity.User;
import br.com.springSecurity.springTutorial.Entity.UserForm;
import br.com.springSecurity.springTutorial.controller.AController.AController;
import br.com.springSecurity.springTutorial.repository.UserRepository;
import br.com.springSecurity.springTutorial.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController extends AController<User, UserRepository> {

    @Autowired
    private TokenService tokenService;

    public AutenticacaoController(UserRepository repository) {
        super(repository);
    }

    @Autowired
    AuthenticationManager auth;

    @PostMapping
    public ResponseEntity<TokenDto> post(@RequestBody UserForm body) {

        UsernamePasswordAuthenticationToken loginData = body.converter();
        try{
            //vai tentar autenticar caso não consiga deve gerar uma exceção
            Authentication authenticate = auth.authenticate(loginData);
            //gerar o token já que foi gerado
            String token = tokenService.gerarToken(authenticate);

            System.out.println("Token : "+token);

            return ResponseEntity.ok(new TokenDto(token));
        }catch (AuthenticationException e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
