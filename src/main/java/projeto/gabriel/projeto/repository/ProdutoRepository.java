package projeto.gabriel.projeto.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import projeto.gabriel.projeto.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

  @Query(value="SELECT * FROM produto JOIN produto_categoria pc ON produto.id = pc.produto_id WHERE pc.categoria_id = ?1", nativeQuery=true)
  Iterable<Produto> getProdutosByCategory(String categoryId);
}
