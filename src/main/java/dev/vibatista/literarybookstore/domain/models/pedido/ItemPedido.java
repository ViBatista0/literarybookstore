package dev.vibatista.literarybookstore.domain.models.pedido;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "itemId", updatable = false, nullable = false)
    private UUID itemId;

    @ManyToOne
    @JoinColumn(name = "pedidoId", nullable = false)
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "livroId", nullable = false)
    private Livro livro;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal subtotal;

    public ItemPedido(Pedido pedido, Livro livro, Integer quantidade){
        this.pedido = pedido;
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoUnitario = livro.getPreco();
        this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

}
