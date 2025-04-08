<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion RH</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <div class="header">
        <h1>Previsions</h1>
    </div>
    
    <div class="container">
        <div class="card">
            <h2>Menu Principal</h2>
            <div class="nav-menu">
                <a href="dashboard" class="btn">Voir dashboard des prevision</a>
            </div>
            
            <div class="quick-actions">
                <h3>Actions Rapides</h3>
                <div class="nav-menu">
                    <a href="FormCredit" class="btn btn-success">Ajouter un credit</a>
                    <a href="FormDepense" class="btn btn-success">Ajouter une depense</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>