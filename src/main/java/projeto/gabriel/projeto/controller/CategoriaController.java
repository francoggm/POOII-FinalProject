package projeto.gabriel.projeto.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import projeto.gabriel.projeto.model.Categoria;

public class CategoriaController {

    @PostMapping("/categoria")
    public Categoria criarCategoria(Categoria c){
        c.create();
        return c;
    }

    @PutMapping("/categoria")
    public Categoria atualizarCategoria(Categoria c) {
        c.update();
        return c;
    }

    @DeleteMapping("/categoria")
    public void deletarCategoria(Categoria c){
        c.delete();
    }

    @GetMapping("/categoria")
    public ArrayList<Categoria> listarCategorias() {
        return Categoria.getAll();
    }

    @GetMapping("/categoria/{id}")
    public Categoria buscarCategoria(@PathVariable int id) {
        return Categoria.get(id);
    }
}
