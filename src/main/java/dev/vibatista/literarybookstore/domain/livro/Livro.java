package dev.vibatista.literarybookstore.domain.livro;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String titulo;

    private String autor;

    private String editora;

    private LocalDate dataPublicacao;

    private String isbn;

    private BigDecimal preco;

    private String genero;

    @Enumerated(EnumType.STRING)
    private FormatoLivro formato;

    private Integer qtdEstoque;

    private String imagemCapa;

    private BigDecimal avaliacao;

}
