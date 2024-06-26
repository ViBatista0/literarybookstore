package dev.vibatista.literarybookstore.controllers;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.services.cliente.ClienteService;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.ClienteDTO;
import dev.vibatista.literarybookstore.util.UUIDUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    /*
    * A Anotação PostMapping, como o nome diz, esse método vai ser chamado quando houver um POST no "/clientes"
    * Retorno ResponseEntity, é para enviar cabeçalhos HTTP (200, 201, 404...).
    * */
    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO){

        if (clienteDTO.getNome() == null || clienteDTO.getNome().isEmpty())
          return ResponseEntity.badRequest().body("O nome do cliente não pode ser vazio!");

        if (clienteDTO.getEmail() == null || clienteDTO.getEmail().isEmpty())
            return ResponseEntity.badRequest().body("O email do cliente não pode ser vazio!");

        URI uri = URI.create("/clientes/" + clienteDTO.getId());
        service.create(clienteDTO);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> listarClientes(){
        List<Cliente> clientes = service.listarClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @DeleteMapping
    public ResponseEntity<?> deletarCliente(@RequestBody @Valid ClienteDTO clienteDTO){

        if (clienteDTO.getId() == null)
            return ResponseEntity.badRequest().body("O id informado não pode ser vazio!");

        if (!UUIDUtil.isValidUUID(clienteDTO.getId().toString()))
            return ResponseEntity.badRequest().body("O id informado não é válido!");

        service.delete(clienteDTO.getId());
        return ResponseEntity.ok().body("Cliente deletado com sucesso!");
    }


}
