package projeto.gabriel.projeto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.gabriel.projeto.model.Produto;

@RestController
public class ProdutoController {

    @PostMapping("/produto")
    public Produto criarProduto(Produto p){
        p.create();
        return p;
    }
}
