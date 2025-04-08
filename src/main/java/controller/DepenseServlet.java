package main.java.controller;

import java.io.*;
import java.util.*;
import java.sql.SQLException;
import java.sql.Date;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import main.java.model.Depense;
import main.java.model.Credit;

public class DepenseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        try {
            List<Credit> credits = Credit.getAllCredits();
            req.setAttribute("credits", credits);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/insertDepense.jsp");
            dispatcher.forward(req, res);
            
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des crédits", e);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int idCredit = Integer.parseInt(req.getParameter("idCredit"));
        float montant = Float.parseFloat(req.getParameter("montant"));
        Date dateDepense = Date.valueOf(req.getParameter("dateDepense"));
        
        try {
            boolean inserted = Depense.insertDepense(idCredit, montant, dateDepense);
            
            if (inserted) {
                res.sendRedirect(req.getContextPath() + "/dashboard");
            } else {
                req.setAttribute("erreur", "Solde insuffisant pour cette dépense");
                req.setAttribute("credits", Credit.getAllCredits());
                req.getRequestDispatcher("/WEB-INF/view/insertDepense.jsp").forward(req, res);
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'insertion de la dépense", e);
        }
    }
}
