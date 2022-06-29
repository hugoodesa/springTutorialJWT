package br.com.springSecurity.springTutorial.repository;

import br.com.springSecurity.springTutorial.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional
    @Modifying
    @Query("delete from Produto p Where p.descricao is null")
    public void deleteAllProdutosNullName();

}