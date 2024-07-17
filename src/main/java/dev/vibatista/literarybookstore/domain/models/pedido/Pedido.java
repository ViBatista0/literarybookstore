package dev.vibatista.literarybookstore.domain.models.pedido;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido.CriarPedidoDTO;
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

    public Pedido (CriarPedidoDTO pedidoDTO){
        this.cliente = pedidoDTO.cliente();
        this.dataPedido = LocalDateTime.now();
        this.statusPedido = StatusPedido.PENDENTE;
        this.itensPedido = pedidoDTO.itemPedidoList();
        this.valorTotal = BigDecimal.valueOf(55.63);
        this.enderecoEntrega = pedidoDTO.enderecoEntrega();
        this.metodoPagamento = pedidoDTO.metodoPagamento();
    }
}
