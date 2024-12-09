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
import projeto.gabriel.projeto.services.CategoriaService;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria){
        return service.criarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        return service.atualizarCategoria(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Integer id){
        service.deletarCategoria(id);
    }

    @GetMapping
    public ArrayList<Categoria> listarCategorias() {
        return service.listarCategorias();
    }

    @GetMapping("/{id}")
    public Categoria buscarCategoria(@PathVariable Integer id) {
        return service.buscarCategoria(id);
    }
}
