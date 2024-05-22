package dev.vibatista.literarybookstore.domain.pedido;

import dev.vibatista.literarybookstore.domain.livro.Livro;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal subtotal;

}
