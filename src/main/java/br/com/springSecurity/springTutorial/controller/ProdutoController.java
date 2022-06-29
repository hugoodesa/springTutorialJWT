package br.com.springSecurity.springTutorial.controller;

import br.com.springSecurity.springTutorial.Entity.Produto;
import br.com.springSecurity.springTutorial.controller.AController.AController;
import br.com.springSecurity.springTutorial.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController extends AController<Produto, ProdutoRepository> {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        super(repository);
    }

    @Override
    @PostMapping
    public ResponseEntity<Produto> post(@RequestBody Produto body) {
        return super.post(body);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Produto> select(@PathVariable Long id) {
        return super.select(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id,@RequestBody Produto body) {
        return super.update(id, body);
    }

    @Override
    @GetMapping("/getAll")
    public Page<Produto> getAll(Pageable pageable) {
        return super.getAll(pageable);
    }

    @DeleteMapping("/deleteAllDescriptionNull")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllProdutosNullName(){
        this.repository.deleteAllProdutosNullName();
    }
    //deleteAllProdutosNullName
}
