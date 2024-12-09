package projeto.gabriel.projeto.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import projeto.gabriel.projeto.model.Categoria;
import projeto.gabriel.projeto.repository.CategoriaRepository;

@Service
@AllArgsConstructor
public class CategoriaService {
  
  private final CategoriaRepository repository;

    public Categoria criarCategoria(@RequestBody Categoria categoria){
        repository.save(categoria);
        return categoria;
    }

    public Categoria atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        final Categoria findCategoria = repository.findById(id).get();
        if(findCategoria == null){
            return null;
        }

        categoria.setId(findCategoria.getId());
        repository.save(categoria);

        return categoria;
    }

    public void deletarCategoria(@PathVariable Integer id){
        repository.deleteById(id);
    }

    public ArrayList<Categoria> listarCategorias() {
        return (ArrayList<Categoria>) repository.findAll();
    }

    public Categoria buscarCategoria(@PathVariable Integer id) {
      return repository.findById(id).get();
    }
}
