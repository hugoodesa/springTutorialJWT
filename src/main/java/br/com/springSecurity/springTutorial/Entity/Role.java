package br.com.springSecurity.springTutorial.Entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode @ToString
public class Role extends AEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles" ,fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return this.name;
    }
}
