package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Rafael
 */
public class Conexao {

    public Connection getConnection() throws SQLException {
        Connection conexao = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Spotifei",
                "postgres", "fei");
        return conexao;
    }
}
