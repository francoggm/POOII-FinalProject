package projeto.gabriel.projeto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Double peso;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoCompra> itemCompraList = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<ProdutoCategoria> produtoCategoriaList = new ArrayList<>();
}
