package  main.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://172.80.237.53/db_s2_ETU003369"; // Ajout du paramètre de fuseau horaire
        Properties properties = new Properties();
        properties.setProperty("user", "ETU003369");
        properties.setProperty("password", "XxXV7Dqt");
    
        Connection connection = null;
    
        try {
            // Charger le pilote JDBC explicitement
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
            throw e;
        } catch (ClassNotFoundException e) {
            System.err.println("Pilote JDBC non trouvé : " + e.getMessage());
            throw new SQLException("Pilote JDBC non trouvé", e);
        }
    
        return connection;
    }
    
}