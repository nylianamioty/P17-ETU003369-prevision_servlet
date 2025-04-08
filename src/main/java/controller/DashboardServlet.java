package main.java.controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import main.java.model.Credit;
import main.java.db.*;

public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        try {
            List<Map<String, Object>> dashboardData = getDashboardData();
            req.setAttribute("dashboardData", dashboardData);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
            dispatcher.forward(req, res);
            
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des données", e);
        }
    }

    private List<Map<String, Object>> getDashboardData() throws SQLException {
        List<Map<String, Object>> data = new ArrayList<>();
        String sql = "SELECT c.id, c.libelle, c.montant, " +
                     "COALESCE(SUM(d.montant), 0) AS total_depense, " +
                     "(c.montant - COALESCE(SUM(d.montant), 0)) AS reste " +
                     "FROM prevision_credit c " +
                     "LEFT JOIN prevision_depense d ON c.id = d.id_credit " +
                     "GROUP BY c.id, c.libelle, c.montant " +
                     "ORDER BY c.libelle";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("libelle", rs.getString("libelle"));
                row.put("montant", rs.getFloat("montant"));
                row.put("total_depense", rs.getFloat("total_depense"));
                row.put("reste", rs.getFloat("reste"));
                data.add(row);
            }
        }
        return data;
    }
}
