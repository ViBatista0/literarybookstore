package dev.vibatista.literarybookstore.domain.models.pedido;

import dev.vibatista.literarybookstore.domain.models.livro.Livro;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "itemId")
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "item_id", updatable = false, nullable = false)
    private UUID itemId;

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
