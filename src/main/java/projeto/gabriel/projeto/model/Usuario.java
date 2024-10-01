package projeto.gabriel.projeto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;

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
    public String getEmail() {
      return email;
    }
    public void setEmail(String email) {
      this.email = email;
    }
    public String getSenha() {
      return senha;
    }
    public void setSenha(String senha) {
      this.senha = senha;
    }

    public void create(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String insertUser = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?)";

        try {
            PreparedStatement ps = dbConn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.nome);             
            ps.setString(2, this.email);               
            ps.setString(3, this.senha);                           
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
        String updateUser = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try {
            PreparedStatement  ps = dbConn.prepareStatement(updateUser);
            ps.setString(1, this.nome);
            ps.setString(2, this.email);
            ps.setString(3, this.senha);
            ps.setInt(4, this.id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        String deleteUser = "DELETE FROM usuario WHERE id = ?";

        try {
            PreparedStatement  ps = dbConn.prepareStatement(deleteUser);
            ps.setInt(1, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Usuario get(int id) {
        Usuario usuario = new Usuario();
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
       
        String selectUser = "SELECT * FROM usuario WHERE id = ?";
        try {
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(selectUser);

            if(rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public static ArrayList<Usuario> getAll(){
        Conexao conn = new Conexao();
        Connection dbConn = conn.getConexao();
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String selectAllUsers = "SELECT * FROM usuario";
        try {
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(selectAllUsers);

            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
