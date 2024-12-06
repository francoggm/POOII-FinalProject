package projeto.gabriel.projeto.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoCompraDTO {
  
  private Integer produtoId;
  private Integer quantidade;
}
