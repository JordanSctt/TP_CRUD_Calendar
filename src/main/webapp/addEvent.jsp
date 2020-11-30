<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajout evenement</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <h2>Ajouter un evenement :</h2>
            <form action="event/add" method="POST">
              <div class="form-group">
                <label>Titre :</label>
                    <input type="text" name="titre_parameter" class="form-control">
                <label>Date :</label>
                    <input type="date" name="date_parameter" class="form-control">
                <label>Description :</label>
                    <input type="text" name="description_parameter" class="form-control">
              </div>
              <button type="submit" class="btn btn-primary">Valider</button>
            </form>
            
</body>
</html>