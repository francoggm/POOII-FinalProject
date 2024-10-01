package projeto.gabriel.projeto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            PreparedStatement ps = dbConn.prepareStatement(insertProduct, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.nome);        
            ps.setDouble(2, this.preco);        
            ps.setString(3, this.descricao);        
            ps.setDouble(4, this.peso);        
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(id);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String updateProduct = "UPDATE produto SET nome = ?, preco = ?, descricao = ?, peso = ? WHERE id = ?";

        try {
            PreparedStatement  ps = dbConn.prepareStatement(updateProduct);
            ps.setString(1, this.nome);
            ps.setDouble(2, this.preco);
            ps.setString(3, this.descricao);
            ps.setDouble(4, this.peso);
            ps.setInt(5, this.id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String deleteProduct = "DELETE FROM produto WHERE id = ?";

        try {
            PreparedStatement  ps = dbConn.prepareStatement(deleteProduct);
            ps.setInt(1, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String selectProduct = "SELECT * FROM produto WHERE id = ?";
        try {
            PreparedStatement ps = dbConn.prepareStatement(selectProduct);
            ps.setInt(1, this.id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                this.nome = rs.getString("nome");
                this.preco = rs.getDouble("preco");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Produto get(int id) {
        Produto produto = new Produto();
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
       
        String selectProduct = "SELECT * FROM produto WHERE id = ?";
        try {
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(selectProduct);

            if(rs.next()) {
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPeso(rs.getDouble("peso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public static ArrayList<Produto> getAll(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        ArrayList<Produto> produtos = new ArrayList<>();

        String selectAllProducts = "SELECT * FROM produto";
        try {
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(selectAllProducts);

            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPeso(rs.getDouble("peso"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
