package kannon.aaron.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgreSQL implements ConnectionDB{
    @Override
    public void connectDB() {
        String driver = "postgresql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "aulajdbc";
        String user = "postgres";
        String password = "Tom@06031996";

        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);

        String connectionUrl = sb.toString();
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("SUCESSO ao se conectar ao banco PostgreSQL!");
        } catch (SQLException e) {
            System.out.println("FALHA ao se conectar ao banco PostgreSQL!");
            e.printStackTrace();
        }
    }
}
