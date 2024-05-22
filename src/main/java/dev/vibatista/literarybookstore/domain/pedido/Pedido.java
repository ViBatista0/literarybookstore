package dev.vibatista.literarybookstore.domain.pedido;

import dev.vibatista.literarybookstore.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @OneToMany
    @JoinColumn(name = "itens_pedido_id")
    private List<ItemPedido> itensPedido;

    private BigDecimal valorTotal;

    private String enderecoEntrega;

    private String metodoPagamento;
}
