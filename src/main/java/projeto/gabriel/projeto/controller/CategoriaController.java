package projeto.gabriel.projeto.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import projeto.gabriel.projeto.model.Categoria;
import projeto.gabriel.projeto.model.CategoriaRepository;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaRepository repository;

    @PostMapping("/categoria")
    public Categoria criarCategoria(Categoria categoria){
        categoria.setId(null);
        return repository.save(categoria);
    }

    @PutMapping("/categoria")
    public Categoria atualizarCategoria(Categoria categoria) {
        if(categoria.getId() == null){
            return null;
        }
        
        return repository.save(categoria);
    }

    @DeleteMapping("/categoria")
    public void deletarCategoria(Categoria categoria){
        repository.delete(categoria);
    }

    @GetMapping("/categoria")
    public ArrayList<Categoria> listarCategorias() {
        return (ArrayList<Categoria>) repository.findAll();
    }

    @GetMapping("/categoria/{id}")
    public Categoria buscarCategoria(@PathVariable long id) {
        return repository.findById(id).get();
    }
}
