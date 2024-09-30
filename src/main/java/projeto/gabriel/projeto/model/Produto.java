package projeto.gabriel.projeto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String descricao;
    private double peso;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void create(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String insertProduct = "INSERT INTO produto (nome, preco, descricao, peso) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = dbConn.prepareStatement(insertProduct);
            ps.setString(1, this.nome);        
            ps.setDouble(2, this.preco);        
            ps.setString(3, this.descricao);        
            ps.setDouble(4, this.peso);        

            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(){

    }

    public void delete(){
        
    }
}
