package dev.vibatista.literarybookstore.domain.carrinho;

import dev.vibatista.literarybookstore.domain.livro.Livro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "item_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private Carrinho carrinho;

    private Livro livro;

    private Integer quantidade;

}
