package projeto.gabriel.projeto.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import projeto.gabriel.projeto.model.Produto;
import projeto.gabriel.projeto.model.ProdutoRepository;



@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository repository;
    
    @PostMapping
    public Produto criarProduto(Produto produto){
        produto.setId(null);
        return repository.save(produto);
    }

    @PutMapping
    public Produto atualizarProduto(Produto produto) {
        if(produto.getId() == null){
            return null;
        }
        
        return repository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(Produto produto){
        repository.delete(produto);
    }

    @GetMapping
    public ArrayList<Produto> listarProdutos() {
        return (ArrayList<Produto>) repository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable long id) {
        return repository.findById(id).get();
    }
}
