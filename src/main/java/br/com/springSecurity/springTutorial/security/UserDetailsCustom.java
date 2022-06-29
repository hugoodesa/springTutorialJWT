package br.com.springSecurity.springTutorial.security;

import br.com.springSecurity.springTutorial.Entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDetailsCustom implements UserDetails {

    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsCustom(User user) {
        this.user = user;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
