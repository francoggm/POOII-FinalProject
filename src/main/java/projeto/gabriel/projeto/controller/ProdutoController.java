package projeto.gabriel.projeto.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import projeto.gabriel.projeto.model.Categoria;
import projeto.gabriel.projeto.model.Produto;
import projeto.gabriel.projeto.payloads.ProdutoDTO;
import projeto.gabriel.projeto.repository.CategoriaRepository;
import projeto.gabriel.projeto.repository.ProdutoRepository;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    
    @PostMapping
    public Produto criarProduto(@RequestBody ProdutoDTO produtoDTO){
        final Produto produto = produtoDTO.toProduto();
        for (Integer categoriaId : produtoDTO.getCategoriasIds()) {
            final Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
            if (categoria != null) {
                produto.getCategorias().add(categoria);
            }
        }

        produtoRepository.save(produto);
        return produto;
    }

    @PutMapping
    public Produto atualizarProduto(@RequestBody Produto produto) {
        if(produto.getId() == null){
            return null;
        }
        
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Integer id){
        produtoRepository.deleteById(id);
    }

    @GetMapping
    public ArrayList<Produto> listarProdutos() {
        return (ArrayList<Produto>) produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Integer id) {
        return produtoRepository.findById(id).get();
    }
}
