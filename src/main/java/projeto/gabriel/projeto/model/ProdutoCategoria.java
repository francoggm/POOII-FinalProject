package projeto.gabriel.projeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(ProdutoCategoriaId.class)
public class ProdutoCategoria {
    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Id
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
