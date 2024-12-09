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
import projeto.gabriel.projeto.model.Produto;
import projeto.gabriel.projeto.payloads.ProdutoDTO;
import projeto.gabriel.projeto.services.ProdutoService;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;
    
    @PostMapping
    public Produto criarProduto(@RequestBody ProdutoDTO produtoDTO){
        return service.criarProduto(produtoDTO);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
        return service.atualizarProduto(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Integer id){
        service.deletarProduto(id);
    }

    @GetMapping
    public ArrayList<Produto> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Integer id) {
        return service.buscarProduto(id);
    }
    
    @GetMapping("/categoria/{id}")
    public ArrayList<Produto> buscarProdutoPorCategoria(@PathVariable Integer id) {
        return service.buscarProdutoPorCategoria(id);
    }
}
