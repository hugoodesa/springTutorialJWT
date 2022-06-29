package br.com.springSecurity.springTutorial.controller.interfaces;

import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IController<Entity> {

    ResponseEntity<Entity> post(Entity body);
    ResponseEntity<Entity> select(Long id);
    ResponseEntity<Entity> delete(Long id);
    ResponseEntity<Entity> update(Long id,Entity body);

    Page<Entity> getAll(Pageable pageable);

    default ResponseEntity<Entity> badRequest(){
        return ResponseEntity.badRequest().build();
    }

    default ResponseEntity<Entity> ok(Entity entity){
        return ResponseEntity.ok(entity);
    }

    default ResponseEntity<Entity> okBuild(){
        return ResponseEntity.ok().build();
    }
}
