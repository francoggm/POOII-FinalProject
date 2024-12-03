package projeto.gabriel.projeto.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProdutoCategoriaId implements Serializable {
    private Produto produto;
    private Categoria categoria;
}
