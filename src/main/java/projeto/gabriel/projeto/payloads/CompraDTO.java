package projeto.gabriel.projeto.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {

  private Integer parcelas;
  private String tipoPagamento;
  private Integer usuarioId;

  private List<ProdutoCompraDTO> produtos;
}
