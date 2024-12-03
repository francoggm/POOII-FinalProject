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
import projeto.gabriel.projeto.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository repository;

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria){
        categoria.setId(null);
        repository.save(categoria);
        return categoria;
    }

    @PutMapping
    public Categoria atualizarCategoria(@RequestBody Categoria categoria) {
        if(categoria.getId() == null){
            return null;
        }
        
        return repository.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping
    public ArrayList<Categoria> listarCategorias() {
        return (ArrayList<Categoria>) repository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria buscarCategoria(@PathVariable Integer id) {
        return repository.findById(id).get();
    }
}
