<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestion des Crédits</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">

</head>
<body>
    <div class="header">
        <h1>${credit != null ? 'Modifier' : 'Ajouter'} un Crédit</h1>
    </div>
    <div class="container">
        <div class="form-container card">
            <form method="post" action="FormCredit">
                <input type="hidden" name="id" value="${credit.id}">
                <div class="form-group">
                    <label for="libelle">Libellé:</label>
                    <input type="text" id="libelle" name="libelle" value="${credit.libelle}" required><br>
                    
                    <label for="montant">Montant:</label>
                    <input type="number" id="montant" name="montant" step="0.01" value="${credit.montant}" required><br>
                </div>
                <div class="form-group">
                    <button type="submit">${credit != null ? 'Mettre à jour' : 'Ajouter'}</button>
                </div>
            </form>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/index.jsp" class="btn">Retour à l'index </a>
                <a href="${pageContext.request.contextPath}/FormDepense" class="btn">Ajouter une depense</a>
            </div>
        </div>
    </div>
</body>
</html>