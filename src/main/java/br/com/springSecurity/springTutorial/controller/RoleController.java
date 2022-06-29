package br.com.springSecurity.springTutorial.controller;

import br.com.springSecurity.springTutorial.Entity.Role;
import br.com.springSecurity.springTutorial.controller.AController.AController;
import br.com.springSecurity.springTutorial.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController extends AController<Role, RoleRepository> {

    @Autowired
    private RoleRepository repository;

    public RoleController(RoleRepository repository) {
        super(repository);
    }

    @Override
    @PostMapping
    public ResponseEntity<Role> post(@RequestBody Role body) {
        return super.post(body);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Role> select(@PathVariable Long id) {
        return super.select(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id,@RequestBody Role body) {
        return super.update(id, body);
    }

    @Override
    @GetMapping("/getAll")
    public Page<Role> getAll(Pageable pageable) {
        return super.getAll(pageable);
    }


}
