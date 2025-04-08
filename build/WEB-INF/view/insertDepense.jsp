<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List , main.java.model.Credit, main.java.model.Depense" %>

<html>
<head>
    <title>Nouvelle Depense</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">

</head>
<body>
    <div class="header">
    <h1>Enregistrer une nouvelle depense</h1>
    </div>
    <% if (request.getAttribute("erreur") != null) { %>
        <p style="color: red;"><%= request.getAttribute("erreur") %></p>
    <% } %>
    <div class="container">
        <div class="form-container card">
            <form action="FormDepense" method="post">
                <div class="form-group">
                    <label for="idCredit">Credit:</label>
                    <select id="idCredit" name="idCredit" required>
                        <option value="">-- Selectionnez un credit --</option>
                        <% 
                            List<Credit> credits = (List<Credit>) request.getAttribute("credits");
                            for (Credit credit : credits) { 
                        %>
                        <option value="<%= credit.getId() %>">
                            <%= credit.getLibelle() %> 
                        </option>
                        <% } %>
                    </select><br>
                    
                    <label for="montant">Montant:</label>
                    <input type="number" id="montant" name="montant" step="0.01" min="0.01" required><br>
                    
                    <label for="dateDepense">Date:</label>
                    <input type="date" id="dateDepense" name="dateDepense" required><br>
                </div>
                <div class="form-group">
                    <button type="submit">Enregistrer</button>
                </div>
            </form>
        </div>
    </div>
    <div class="actions">
        <a href="<%= request.getContextPath() %>/dashboard" class="btn">Retour au tableau de bord</a> <br>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn">Retour à l'index </a> <br>
        <a href="${pageContext.request.contextPath}/FormCredit" class="btn">Ajouter un credit</a>
    </div>
</body>
</html>
