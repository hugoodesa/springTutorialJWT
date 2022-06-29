package br.com.springSecurity.springTutorial.Entity;

import br.com.springSecurity.springTutorial.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@Data @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode
public class Produto extends AEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String descricao;
    private String codigoDeBarras;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();
    private BigDecimal preco;
    private int estoque;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}