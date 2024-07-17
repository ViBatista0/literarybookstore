package dev.vibatista.literarybookstore.controllers;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.pedido.Pedido;
import dev.vibatista.literarybookstore.domain.services.pedido.PedidoService;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido.CriarPedidoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody @Valid CriarPedidoDTO pedidoDTO) {
        try {
            Pedido pedido = service.criarPedido(pedidoDTO);
            URI uri = URI.create("/pedido/" + pedido.getPedidoId());
            return ResponseEntity.created(uri).body(pedido);

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar pedido");
        }

    }

//    @GetMapping
//    public ResponseEntity<?> listarPedidos(@PathVariable UUID clienteId){
//        Cliente cliente = service
//        List<Pedido> pedidoList = service.listarPedidos();
//        return ResponseEntity.ok().body(pedidoList);
//    }
}
