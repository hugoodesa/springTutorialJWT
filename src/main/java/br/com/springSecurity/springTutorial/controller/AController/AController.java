package br.com.springSecurity.springTutorial.controller.AController;

import br.com.springSecurity.springTutorial.Entity.AEntity;
import br.com.springSecurity.springTutorial.controller.interfaces.IController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public class AController<Entity extends AEntity, Repository extends JpaRepository<Entity, Long>> implements IController<Entity> {

    @Autowired
    Repository repository;

    public AController(Repository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Entity> post(Entity body) {
        try {
            Entity savedEntity = this.repository.save(body);
            return ok(savedEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest();
    }

    @Override
    public ResponseEntity<Entity> select(Long id) {
        try {
            Optional<Entity> foundEntity = this.repository.findById(id);
            if (foundEntity.isPresent()) return ok(foundEntity.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest();
    }

    @Override
    public ResponseEntity<Entity> delete(Long id) {
        try {
            this.repository.deleteById(id);
            return okBuild();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest();
    }

    @Override
    public ResponseEntity<Entity> update(Long id, Entity body) {
        try {
            Optional<Entity> entityFound = this.repository.findById(id);
            if (entityFound.isPresent()) {
                body.setId(id);
                Entity savedEntity = this.repository.save(body);
                return ok(savedEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest();
    }

    @Override
    public Page<Entity> getAll(@PageableDefault(size = 5, page = 0) Pageable pageable) {
        Page<Entity> allEntity = this.repository.findAll(pageable);
        return allEntity;
    }


}
