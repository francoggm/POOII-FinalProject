package projeto.gabriel.projeto.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        updateCategorias(produto, produtoDTO.getCategoriasIds());

        produtoRepository.save(produto);
        return produto;
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
        final Produto findProduto = produtoRepository.findById(id).get();
        if(findProduto == null){
            return null;
        }
        
        final Produto updateProduto = produtoDTO.toProduto();
        updateProduto.setId(findProduto.getId());
        updateProduto.setCategorias(new HashSet<>());

        updateCategorias(updateProduto, produtoDTO.getCategoriasIds());

        produtoRepository.save(updateProduto);
        return updateProduto;
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
    
    @GetMapping("/categoria/{id}")
    public ArrayList<Produto> buscarProdutoPorCategoria(@PathVariable Integer id) {
        return (ArrayList<Produto>) produtoRepository.getProdutosByCategory(id.toString());
    }

    private void updateCategorias(final Produto produto, final List<Integer> categoriasIds) {
        for (Integer categoriaId : categoriasIds) {
            final Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
            if (categoria != null) {
                produto.getCategorias().add(categoria);
            }
        }
    }
}
