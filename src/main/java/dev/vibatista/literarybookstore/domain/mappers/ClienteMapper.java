package dev.vibatista.literarybookstore.domain.mappers;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.CadastroClienteDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.ListarClientesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

// A anotação vem da MapStruct e indica que essa interface será implementada automaticamente no tempo de execução pelo
// framework
@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ListarClientesDTO toListarClientesDto(Cliente cliente);

    Cliente toEntity(CadastroClienteDTO dto);
}
