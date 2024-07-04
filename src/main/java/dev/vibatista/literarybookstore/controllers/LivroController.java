package dev.vibatista.literarybookstore.controllers;

import dev.vibatista.literarybookstore.domain.models.livro.Livro;
import dev.vibatista.literarybookstore.domain.services.livro.LivroService;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.CadastrarLivroDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.EditarLivroDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.ListarLivrosDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<?> createLivro(@RequestBody @Valid CadastrarLivroDTO cadastrarLivroDTO) {

        Optional<Livro> livro = livroService.findByTitulo(cadastrarLivroDTO.titulo());

        if(livro.isPresent())
            return ResponseEntity.badRequest().body("Livro já existente");

        if (cadastrarLivroDTO.titulo().isEmpty())
            return ResponseEntity.badRequest().body("Informe o título do livro");

        if (cadastrarLivroDTO.isbn().isEmpty())
            return ResponseEntity.badRequest().body("Informe o ISBN do livro");

        if (cadastrarLivroDTO.preco() == null || cadastrarLivroDTO.preco().equals(BigDecimal.valueOf(-0)))
            return ResponseEntity.badRequest().body("Informe o preço do livro");

        if (cadastrarLivroDTO.qntdEstoque() == null || cadastrarLivroDTO.qntdEstoque() < 0)
            return ResponseEntity.badRequest().body("Informe a quantidade de livros no estoque");

        Livro livroCriado = livroService.createLivro(cadastrarLivroDTO);
        URI uri = URI.create("/livros/" + livroCriado.getId());
        return ResponseEntity.created(uri).body(livroCriado);
    }

    @GetMapping
    public ResponseEntity<?> listarLivros() {
        List<ListarLivrosDTO> livros = livroService.livroList();
        return ResponseEntity.ok().body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){

        Optional<Livro> livro = livroService.findById(UUID.fromString(id));

        if (livro.isPresent())
            return ResponseEntity.ok().body(livro);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLivro(@PathVariable String id){
        try {
            Optional<Livro> livro = livroService.findById(UUID.fromString(id));
            if (livro.isPresent()){
                livroService.deleteLivro(livro.get());
                return ResponseEntity.noContent().build();
            }
            else return ResponseEntity.badRequest().body("Livro não encontrado!");

        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body("Ocorreu um erro ao deletar o livro: " + ex);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editarLivro(@PathVariable String id, @RequestBody EditarLivroDTO editarLivroDTO){
        Optional<Livro> livro = livroService.findById(UUID.fromString(id));
        if (livro.isEmpty())
            return ResponseEntity.notFound().build();

        try{
            Livro livroAtualizado = livroService.editarLivro(UUID.fromString(id), editarLivroDTO);
            return ResponseEntity.ok().body(livroAtualizado);
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body("Erro ao editar o livro" + ex);
        }
    }

}
