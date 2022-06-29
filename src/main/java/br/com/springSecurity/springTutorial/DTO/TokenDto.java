package br.com.springSecurity.springTutorial.DTO;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode
public class TokenDto {

    private String token;
    private String autenticacao;
    public TokenDto(String token) {
        this.token=token;
        autenticacao="Baerer";
    }

}
