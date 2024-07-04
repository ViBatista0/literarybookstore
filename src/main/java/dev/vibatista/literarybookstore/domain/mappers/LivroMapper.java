package dev.vibatista.literarybookstore.domain.mappers;

import dev.vibatista.literarybookstore.domain.models.livro.Livro;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.CadastrarLivroDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {
    CadastrarLivroDTO toDto(Livro livro);

    Livro toEntity(CadastrarLivroDTO dto);
}
