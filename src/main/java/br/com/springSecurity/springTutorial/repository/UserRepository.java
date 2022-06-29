package br.com.springSecurity.springTutorial.repository;

import br.com.springSecurity.springTutorial.Entity.Role;
import br.com.springSecurity.springTutorial.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("delete from User u where u.login is null")
    public void deleteUserWhenLoginIsNull();

    Optional<User> findByName(String name);

    Optional<User> findById(Long id);

}