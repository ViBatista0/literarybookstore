package dev.vibatista.literarybookstore.domain.services.cliente;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.repositories.cliente.ClienteRepository;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void create(ClienteDTO clienteDTO) {
        Cliente cliente = dtoParaEntidade(clienteDTO);
        cliente.setDataRegistro(LocalDate.now());
        clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // O Optional indica que pode não existir um cliente com esse ID
    public Optional<Cliente> findById(UUID id) {
        return clienteRepository.findById(id);
    }

    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }
    public Cliente findByEmail(String cpf) {
        return clienteRepository.findByEmail(cpf);
    }

    public void delete(UUID id) {
        clienteRepository.deleteById(id);
    }

    public Cliente editarCliente(UUID id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteAntigo = clienteRepository.findById(id);

        if (clienteAntigo.isEmpty())
            return null;

        Cliente cliente = clienteAntigo.get();

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());

        return clienteRepository.save(cliente);

    }

    private Cliente dtoParaEntidade(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());

        return cliente;
    }

}
