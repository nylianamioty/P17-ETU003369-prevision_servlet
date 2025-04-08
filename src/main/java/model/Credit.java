package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.java.db.DatabaseConnection;

public class Credit{
    private int id;
    private String libelle;
    private float montant;

    public Credit(int id,String libelle,float montant){
        this.id=id;
        this.libelle=libelle;
        this.montant=montant;
    }
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
     public float getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public static void insertCredit(String libelle,float montant) throws SQLException{
        try(Connection conn=DatabaseConnection.getConnection()){
            String sql="INSERT INTO prevision_credit(libelle,montant) VALUES(?,?)";
            try(PreparedStatement stt =conn.prepareStatement(sql)){
                stt.setString(1,libelle);
                stt.setFloat(2,montant);
                stt.executeUpdate();
            }
        }
    }
    public static Credit getCreditById(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM prevision_credit WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Credit(
                            rs.getInt("id"),
                            rs.getString("libelle"),
                            rs.getFloat("montant")
                        );
                    }
                    return null;
                }
            }
        }
    }
    public static List<Credit> getAllCredits() throws SQLException {
        List<Credit> credits = new ArrayList<>();
        String sql = "SELECT * FROM prevision_credit ORDER BY libelle";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                credits.add(new Credit(
                    rs.getInt("id"),
                    rs.getString("libelle"),
                    rs.getFloat("montant")
                ));
            }
        }
        return credits;
    }
}