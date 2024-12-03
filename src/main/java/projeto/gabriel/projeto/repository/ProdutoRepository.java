package projeto.gabriel.projeto.repository;

import org.springframework.data.repository.CrudRepository;

import projeto.gabriel.projeto.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> { }
