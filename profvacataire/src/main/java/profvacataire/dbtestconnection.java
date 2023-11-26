package profvacataire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbtestconnection {
    private String jdbcUrl;
    private String username;
    private String password;
    private Connection connection;

    public dbtestconnection(final String jdbcUrl, final String username, final String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.connection = null;
    }

    public void connect() {
        try {
            // Charger le pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // �tablir la connexion
            this.connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connexion � la base de donn�es r�ussie.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erreur lors de la connexion � la base de donn�es: " + e.getMessage());
        }
    }

    public void testConnection() {
        if (this.connection != null) {
            try {
                // Ex�cuter une requ�te de test simple
                this.connection.createStatement().executeQuery("SELECT * FROM admin ");
                System.out.println("Test de la connexion r�ussi.");
            } catch (SQLException e) {
                System.err.println("Erreur lors du test de la connexion: " + e.getMessage());
            }
        } else {
            System.out.println("La connexion n'est pas �tablie.");
        }
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                // Fermer la connexion
                this.connection.close();
                System.out.println("Connexion � la base de donn�es ferm�e.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion: " + e.getMessage());
            }
        } else {
            System.out.println("La connexion n'est pas �tablie.");
        }
    }

    public static void main(String[] args) {
        // Utilisation de la classe
        String jdbcUrl = "jdbc:mysql://localhost:3306/profvacatairebd"; 
        String username = "root";  // Nom d'utilisateur par d�faut pour MySQL dans XAMPP
        String password = "";      // Mot de passe par d�faut est souvent vide dans XAMPP

        dbtestconnection dbConnection = new dbtestconnection(jdbcUrl, username, password);
        dbConnection.connect();
        dbConnection.testConnection();
        dbConnection.closeConnection();
    }
}
