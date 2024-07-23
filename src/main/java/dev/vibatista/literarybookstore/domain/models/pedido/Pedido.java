package dev.vibatista.literarybookstore.domain.models.pedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido.CriarPedidoDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido.ItemPedidoDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "pedidoId")
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "pedidoId", updatable = false, nullable = false)
    private UUID pedidoId;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedido;

    private BigDecimal valorTotal;

    private String enderecoEntrega;

    private MetodoPagamento metodoPagamento;


}
