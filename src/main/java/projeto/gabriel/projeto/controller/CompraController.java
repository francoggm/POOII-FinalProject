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
import projeto.gabriel.projeto.model.Compra;
import projeto.gabriel.projeto.repository.CompraRepository;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/compra")
public class CompraController {

  private final CompraRepository repository;

    @PostMapping
    public Compra criarCompra(@RequestBody Compra compra) {
      compra.setId(null);
      return repository.save(compra);
    }

    @PutMapping
    public Compra atualizarCompra(@RequestBody Compra compra) {
      if(compra.getId() == null){
        return null;
      }
      
      return repository.save(compra);
    }

    @DeleteMapping("/{id}")
    public void deletarCompra(@PathVariable Integer id) {
      repository.deleteById(id);
    }

    @GetMapping
    public ArrayList<Compra> listarCompras() {
      return (ArrayList<Compra>) repository.findAll();
    }

    @GetMapping("/{id}")
    public Compra buscarCompra(@PathVariable Integer id) {
      return repository.findById(id).orElse(null);
    }
}
