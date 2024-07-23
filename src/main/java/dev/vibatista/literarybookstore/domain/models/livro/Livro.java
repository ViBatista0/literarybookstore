package dev.vibatista.literarybookstore.domain.models.livro;

import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.CadastrarLivroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "livroId")
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "livroId", updatable = false, nullable = false)
    private UUID livroId;

    @NotNull(message = "Informe o título do livro!")
    private String titulo;

    private String autor;

    private String editora;

    private String descricao;

    private LocalDate dataPublicacao;

    private String isbn;

    private BigDecimal preco;

    private String genero;

    @Enumerated(EnumType.STRING)
    private FormatoLivro formato;

    private Integer qtdEstoque;

    private String imagemCapa;

    private BigDecimal avaliacao;

    public Livro(CadastrarLivroDTO cadastrarLivroDTO){
        this.titulo = cadastrarLivroDTO.titulo();
        this.autor = cadastrarLivroDTO.autor();
        this.editora = cadastrarLivroDTO.editora();
        this.descricao = cadastrarLivroDTO.descricao();
        this.dataPublicacao = cadastrarLivroDTO.dataPublicacao();
        this.isbn = cadastrarLivroDTO.isbn();
        this.preco = cadastrarLivroDTO.preco();
        this.genero = cadastrarLivroDTO.genero();
        this.formato = cadastrarLivroDTO.formatoLivro();
        this.qtdEstoque = cadastrarLivroDTO.qntdEstoque();
        this.imagemCapa = cadastrarLivroDTO.imagemCapa();
    }

}
