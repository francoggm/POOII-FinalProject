package projeto.gabriel.projeto.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private final String db = "products";
    private final int port = 3306;
    private final String user = "user";
    private final String password = "password";

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
