package main.java.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import main.java.model.Credit;

public class FormCreditServlet extends HttpServlet{
   protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/insertCredit.jsp");
        dispatcher.forward(req, res);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String libelle = req.getParameter("libelle");
        String montantStr = req.getParameter("montant");
        
        try {
            float montant = Float.parseFloat(montantStr);
            Credit.insertCredit(libelle, montant);
            
            // Redirection après succès
            res.sendRedirect(req.getContextPath() + "/FormCredit");
            
        } catch (NumberFormatException e) {
            req.setAttribute("erreur", "Le montant doit être un nombre valide");
            req.getRequestDispatcher("/WEB-INF/view/insertCredit.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'insertion du crédit", e);
        }
    }
}
