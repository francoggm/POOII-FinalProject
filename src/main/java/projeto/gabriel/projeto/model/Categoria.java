package projeto.gabriel.projeto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Categoria {
    private int id;
    private String nome;
    private String descricao;

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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void create(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String insertCategory = "INSERT INTO categoria (nome, descricao) VALUES (?, ?)";

        try {
            PreparedStatement ps = dbConn.prepareStatement(insertCategory, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.nome);             
            ps.setString(2, this.descricao);               
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String updateCategory = "UPDATE categoria SET nome = ?, descricao = ? WHERE id = ?";

        try {
            PreparedStatement  ps = dbConn.prepareStatement(updateCategory);
            ps.setString(1, this.nome);
            ps.setString(2, this.descricao);
            ps.setInt(3, this.id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String deleteCategory = "DELETE FROM categoria WHERE id = ?";

        try {
            PreparedStatement  ps = dbConn.prepareStatement(deleteCategory);
            ps.setInt(1, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Categoria get(int id) {
        Categoria categoria = new Categoria();
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
       
        String selectCategory = "SELECT * FROM categoria WHERE id = ?";
        try {
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(selectCategory);

            if(rs.next()) {
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }

    public static ArrayList<Categoria> getAll(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        ArrayList<Categoria> categorias = new ArrayList<>();

        String selectAllCategories = "SELECT * FROM categoria";
        try {
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(selectAllCategories);

            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
