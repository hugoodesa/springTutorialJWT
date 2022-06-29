package br.com.springSecurity.springTutorial.security;

import br.com.springSecurity.springTutorial.Entity.User;
import br.com.springSecurity.springTutorial.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(request);

        //System.out.println("Token após autenticar : "+token);

        boolean isTokenValido = tokenService.isTokenValido(token);
        System.out.println("Token valido : "+isTokenValido);

        if(isTokenValido){
            this.autenticarUsuario(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarUsuario(String token) {
        User user = new User();

        Long idUser =  tokenService.findUserIdByToken(token);
        this.userRepository.findById(idUser);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getLogin(),null,user.getRoles());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        boolean token = authorization != null &&
                !authorization.isEmpty() &&
                authorization.startsWith("Baerer ");

        return token ? authorization.substring(7) : null;
    }

}
