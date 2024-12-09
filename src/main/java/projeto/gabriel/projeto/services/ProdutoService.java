package projeto.gabriel.projeto.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import projeto.gabriel.projeto.model.Categoria;
import projeto.gabriel.projeto.model.Produto;
import projeto.gabriel.projeto.payloads.ProdutoDTO;
import projeto.gabriel.projeto.repository.CategoriaRepository;
import projeto.gabriel.projeto.repository.ProdutoRepository;

@Service
@AllArgsConstructor
public class ProdutoService {

  private final ProdutoRepository produtoRepository;
  private final CategoriaRepository categoriaRepository;

  public Produto criarProduto(final ProdutoDTO produtoDTO) {
    final Produto produto = produtoDTO.toProduto();
    updateCategorias(produto, produtoDTO.getCategoriasIds());

    produtoRepository.save(produto);
    return produto;
  }

  public Produto atualizarProduto(final Integer id, final ProdutoDTO produtoDTO) {
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

  public void deletarProduto(final Integer id){
      produtoRepository.deleteById(id);
  }

  public ArrayList<Produto> listarProdutos() {
      return (ArrayList<Produto>) produtoRepository.findAll();
  }

  public Produto buscarProduto(final Integer id) {
      return produtoRepository.findById(id).get();
  }
  
  public ArrayList<Produto> buscarProdutoPorCategoria(final Integer id) {
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
