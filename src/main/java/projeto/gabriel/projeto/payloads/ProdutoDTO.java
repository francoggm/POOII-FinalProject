package projeto.gabriel.projeto.payloads;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.gabriel.projeto.model.Produto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
  private String nome;
  private BigDecimal valor;
  private String descricao;
  private Double peso;

  @JsonProperty("categorias")
  private List<Integer> categoriasIds = new ArrayList<>();

  public Produto toProduto() {
    return new Produto(null, nome, valor, descricao, peso, new HashSet<>());
  }
}
