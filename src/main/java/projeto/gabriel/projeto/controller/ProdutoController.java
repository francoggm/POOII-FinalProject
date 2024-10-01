package projeto.gabriel.projeto.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.gabriel.projeto.model.Produto;

@RestController
public class ProdutoController {

    @PostMapping("/produto")
    public Produto criarProduto(Produto p){
        p.create();
        return p;
    }

    @PutMapping("/produto")
    public Produto atualizarProduto(Produto p) {
        p.update();
        return p;
    }

    @DeleteMapping("/produto")
    public void deleteProduto(Produto p){
        p.delete();
    }

    @GetMapping("/produto")
    public ArrayList<Produto> listarProdutos() {
        return Produto.getAll();
    }

    @GetMapping("/produto/{id}")
    public Produto buscarProduto(@PathVariable int id) {
        return Produto.get(id);
    }
}
