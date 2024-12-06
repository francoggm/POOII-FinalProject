package projeto.gabriel.projeto.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import projeto.gabriel.projeto.model.Produto;
import projeto.gabriel.projeto.model.ProdutoCompra;
import projeto.gabriel.projeto.model.Usuario;
import projeto.gabriel.projeto.payloads.CompraDTO;
import projeto.gabriel.projeto.payloads.ProdutoCompraDTO;
import projeto.gabriel.projeto.repository.CompraRepository;
import projeto.gabriel.projeto.repository.ProdutoCompraRepository;
import projeto.gabriel.projeto.repository.ProdutoRepository;
import projeto.gabriel.projeto.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/compra")
public class CompraController {

  private final CompraRepository compraRepository;
  private final ProdutoRepository produtoRepository;
  private final ProdutoCompraRepository produtoCompraRepository;
  private final UsuarioRepository usuarioRepository;

    @PostMapping
    public Compra criarCompra(@RequestBody CompraDTO compraDTO) {
      final Usuario usuario = usuarioRepository.findById(compraDTO.getUsuarioId()).get();
      if (usuario == null) {
        return null;
      }

      final LocalDate now = LocalDate.now();
      final Compra compra = new Compra(null, now, compraDTO.getParcelas(), compraDTO.getTipoPagamento(), usuario, new ArrayList<>());

      final List<ProdutoCompra> produtoCompras = new ArrayList<>();
      for (ProdutoCompraDTO produtoDTO : compraDTO.getProdutos()) {
        final Produto produto = produtoRepository.findById(produtoDTO.getProdutoId()).get();
        if (produto == null) {
          return null;
        }

        final BigDecimal preco = produto.getValor().multiply(BigDecimal.valueOf(produtoDTO.getQuantidade()));
        final ProdutoCompra produtoCompra = new ProdutoCompra(null, preco, produtoDTO.getQuantidade(), false, produto, null);
        produtoCompras.add(produtoCompra);
      }

      compraRepository.save(compra);
      for (ProdutoCompra produtoCompra : produtoCompras) {
        produtoCompra.setCompra(compra);
        produtoCompraRepository.save(produtoCompra);

        compra.getProdutoCompraList().add(produtoCompra);
      }

      usuario.getCompraList().add(compra);
      usuarioRepository.save(usuario);

      return compraRepository.findById(compra.getId()).get();
    }

    @DeleteMapping("/{id}")
    public void deletarCompra(@PathVariable Integer id) {
      compraRepository.deleteById(id);
    }

    @GetMapping
    public ArrayList<Compra> listarCompras() {
      return (ArrayList<Compra>) compraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Compra buscarCompra(@PathVariable Integer id) {
      return compraRepository.findById(id).orElse(null);
    }
}
