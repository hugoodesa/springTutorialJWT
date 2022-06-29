package br.com.springSecurity.springTutorial.security;

import br.com.springSecurity.springTutorial.Entity.User;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    public boolean isTokenValido(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Expiration.key).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Long findUserIdByToken(String token) {
        Claims body = Jwts.parser().setSigningKey(Expiration.key).parseClaimsJws(token).getBody();
        long idUser = Long.parseLong(body.getSubject());
        return idUser;
    }

    private static class Expiration {
        private Long expirationMillis=86400000L;

        private static String key = "$2a$12$RTRGjYfErZhssoia9Cvaqe/auTDbrcGY/YAzCf.oulmyWE7IhlUPy";

        private Date today = new Date();
        private Date expirated = new Date(today.getTime()+expirationMillis);

        public Date getExpirated() {
            return expirated;
        }
        public Date getToday() {
            return today;
        }

        public static String getKey(){
            return key;
        }
    }

    public String gerarToken(Authentication authenticate) {

        User user = (User) authenticate.getPrincipal();
        Expiration expiration = new Expiration();


        String token = Jwts
                .builder()
                .setIssuer("Autenticacao form alura")
                .setSubject(user.getId().toString())
                .setExpiration(expiration.getExpirated())
                .setIssuedAt(expiration.getToday())
                .signWith(SignatureAlgorithm.HS256, Expiration.key)
                .compact();

        return token;
    }
}
