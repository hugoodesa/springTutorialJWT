package br.com.springSecurity.springTutorial.Entity;

import lombok.*;

import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode
public abstract class AEntity {
    private Long id;
}