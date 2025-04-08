<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>Tableau de Bord</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
    </style>
</head>
<body>
    <h1>Tableau de Bord des Credits et Dépenses</h1>
    
    <table>
        <tr>
            <th>Libelle Credit</th>
            <th>Montant Credit</th>
            <th>Total Depense</th>
            <th>Reste</th>
        </tr>
        <% 
        List<Map<String, Object>> data = (List<Map<String, Object>>) request.getAttribute("dashboardData");
        for (Map<String, Object> row : data) { 
        %>
        <tr>
            <td><%= row.get("libelle") %></td>
            <td><%= String.format("%.2f", row.get("montant")) %> </td>
            <td><%= String.format("%.2f", row.get("total_depense")) %> </td>
            <td><%= String.format("%.2f", row.get("reste")) %> </td>
        </tr>
        <% } %>
    </table>
    
    <br>
    
    <a href="${pageContext.request.contextPath}/FormDepense" class="btn">Ajouter une depense</a>
    <a href="${pageContext.request.contextPath}/FormCredit" class="btn">Ajouter un credit</a>
</body>
</html>