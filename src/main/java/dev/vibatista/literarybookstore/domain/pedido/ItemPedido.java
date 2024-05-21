package dev.vibatista.literarybookstore.domain.pedido;

import dev.vibatista.literarybookstore.domain.livro.Livro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;


    private Pedido pedido;

    private Livro livro;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal subtotal;

}
