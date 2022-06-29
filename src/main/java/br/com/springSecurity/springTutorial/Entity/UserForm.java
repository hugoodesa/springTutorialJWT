package br.com.springSecurity.springTutorial.Entity;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode
public class UserForm {

    private String login;
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login,password);
    }
}
