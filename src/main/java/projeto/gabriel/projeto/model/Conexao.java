package projeto.gabriel.projeto.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private String db = "store";
    private int port = 3306;
    private String user = "root";
    private String password = "root";

    public Connection getConexao() {
        String uri = String.format("jdbc:mysql://localhost:%d/%s", this.port, this.db);
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(uri, this.user, this.password);
        } catch (Exception e) {
            System.out.printf("Failed connecting to database: %s", e.getStackTrace().toString());
        }

        return conn;
    }
}
