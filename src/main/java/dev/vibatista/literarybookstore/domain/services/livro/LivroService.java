package dev.vibatista.literarybookstore.domain.services.livro;

import dev.vibatista.literarybookstore.domain.models.livro.FormatoLivro;
import dev.vibatista.literarybookstore.domain.models.livro.Livro;
import dev.vibatista.literarybookstore.domain.repositories.livro.LivroRepository;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.CadastrarLivroDTO;
import dev.vibatista.literarybookstore.domain.mappers.LivroMapper;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.EditarLivroDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.ListarLivrosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper livroMapper;
    public Livro createLivro(CadastrarLivroDTO cadastrarLivroDTO){
//        Ver alguma forma de utilizar o MapStructure
//        Livro livro = livroMapper.toEntity(cadastrarLivroDTO);
        Livro livro = new Livro(cadastrarLivroDTO);
        return livroRepository.save(livro);
    }

    public List<ListarLivrosDTO> livroList(){
        List<Livro> livroList = livroRepository.findAll();
        return livroList.stream()
                .map(ListarLivrosDTO::fromLivros)
                .collect(Collectors.toList());
    }

    public Livro editarLivro(UUID id , EditarLivroDTO editarLivroDTO){
        Optional<Livro> livroAntigo = livroRepository.findById(id);

        if (livroAntigo.isEmpty())
            return null;

        Livro livro = livroAntigo.get();

        livro.setId(editarLivroDTO.id());
        livro.setTitulo(editarLivroDTO.titulo());
        livro.setAutor(editarLivroDTO.autor());
        livro.setDataPublicacao(editarLivroDTO.dataPublicacao());
        livro.setPreco(editarLivroDTO.preco());
        livro.setGenero(editarLivroDTO.genero());

        return livroRepository.save(livro);
    }


    public void deleteLivro(Livro livro){
        livroRepository.delete(livro);
    }

    public Optional<Livro> findById(UUID id){
        return livroRepository.findById(id);
    }

    public Optional<Livro> findByTitulo(String titulo){
        return livroRepository.findByTitulo(titulo);
    }
}
