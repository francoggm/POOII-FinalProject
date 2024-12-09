package projeto.gabriel.projeto.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import projeto.gabriel.projeto.model.Compra;
import projeto.gabriel.projeto.payloads.CompraDTO;
import projeto.gabriel.projeto.services.CompraService;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/compra")
public class CompraController {

  private final CompraService service;

    @PostMapping
    public Compra criarCompra(@RequestBody CompraDTO compraDTO) {
      return service.criarCompra(compraDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarCompra(@PathVariable Integer id) {
      service.deletarCompra(id);
    }

    @GetMapping
    public ArrayList<Compra> listarCompras() {
      return service.listarCompras();
    }

    @GetMapping("/{id}")
    public Compra buscarCompra(@PathVariable Integer id) {
      return service.buscarCompra(id);
    }
}
