package dev.vibatista.literarybookstore.domain.services.cliente;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.repositories.cliente.ClienteRepository;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    /*
    * Cria um cliente, o repository chama o método save do JPARepository, e faz isso para os demais métodos, com
    * seus respectivos métodos.
    * */
    public Cliente create(ClienteDTO clienteDTO){
        Cliente cliente = dtoParaEntidade(clienteDTO);
        cliente.setDataRegistro(LocalDate.now());
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    // O Optional indica que pode não existir um cliente com esse ID
    public Optional<Cliente> findById(UUID id){
        return clienteRepository.findById(id);
    }

    public void delete(UUID id){
        clienteRepository.deleteById(id);
    }

    private Cliente dtoParaEntidade(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());

        return cliente;
    }

}
