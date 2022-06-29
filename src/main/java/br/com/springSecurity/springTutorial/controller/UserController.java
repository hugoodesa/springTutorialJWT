package br.com.springSecurity.springTutorial.controller;

import br.com.springSecurity.springTutorial.Entity.User;
import br.com.springSecurity.springTutorial.controller.AController.AController;
import br.com.springSecurity.springTutorial.repository.RoleRepository;
import br.com.springSecurity.springTutorial.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
@RequestMapping("/users")
public class UserController extends AController<User, UserRepository> {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    public UserController(UserRepository repository) {
        super(repository);
    }


    @Override
    @PostMapping
    public ResponseEntity<User> post(@RequestBody User body) {
        try {
            return super.post(body);
        }catch (Exception e){
            boolean is_not_present_in_table = e.getMessage().contains("is not present in table");

            if(is_not_present_in_table){
                System.out.println("ROLE INVALID !");
            }

            return super.post(body);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> select(@PathVariable Long id) {
        return super.select(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    public void criptografarSenha(User body){
        String password = body.getPassword();
        body.setPassword(new BCryptPasswordEncoder().encode(password));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User body) {
        criptografarSenha(body);
        return super.update(id, body);
    }

    @Override
    @GetMapping("/getAll")
    public Page<User> getAll(Pageable pageable) {
        return super.getAll(pageable);
    }

    @DeleteMapping("/deleteUserWhenLoginIsNull")
    public void deleteUserWhenLoginIsNull(){
        this.repository.deleteUserWhenLoginIsNull();
    }

}
