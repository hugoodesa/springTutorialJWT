package br.com.springSecurity.springTutorial.security;

import br.com.springSecurity.springTutorial.Entity.User;
import br.com.springSecurity.springTutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailCustomService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> findUserByUsername = this.repository.findByName(username);

        if(findUserByUsername.isPresent()) {
            return findUserByUsername.get();
        };

        throw new UsernameNotFoundException("User not found");

    }
}
