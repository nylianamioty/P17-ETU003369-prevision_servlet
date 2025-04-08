package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import main.java.db.DatabaseConnection;

public class Depense{
    private int id;
    private int idCredit;
    private float montant;
    private Date dateDepense;

    public Depense(int id, int idCredit, float montant, Date dateDepense) {
        this.id = id;
        this.idCredit = idCredit;
        this.montant = montant;
        this.dateDepense = dateDepense;
    } 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
    }

     public static boolean insertDepense(int idCredit, float montant, Date dateDepense) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String sqlReste = "SELECT c.montant - COALESCE(SUM(d.montant), 0) as reste " +
                             "FROM prevision_credit c LEFT JOIN prevision_depense d ON c.id = d.id_credit " +
                             "WHERE c.id = ? GROUP BY c.id";
            
            float reste = 0;
            try (PreparedStatement stmt = conn.prepareStatement(sqlReste)) {
                stmt.setInt(1, idCredit);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        reste = rs.getFloat("reste");
                    }
                }
            }

            if (reste < montant) {
                conn.rollback();
                return false;
            }

            // Insérer la dépense
            String sqlInsert = "INSERT INTO prevision_depense(id_credit, montant, date_depense) VALUES(?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                stmt.setInt(1, idCredit);
                stmt.setFloat(2, montant);
                stmt.setDate(3, dateDepense);
                stmt.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } 
                catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
