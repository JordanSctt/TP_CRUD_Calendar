<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Modifier evenement</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <h2>Modifier un evenement :</h2>
            <form action="${pageContext.request.contextPath}/event/update" method="POST">
            <input type="hidden" name="event_id" class="form-control" value="${event.id}">
              <div class="form-group">
                <label>Titre :</label>
                    <input type="text" name="titre_parameter" placeholder="${requestScope.event.titre}" class="form-control">
                <label>Date :</label>
                    <input type="date" name="date_parameter" placeholder="${requestScope.event.date}" class="form-control">
                <label>Description :</label>
                    <input type="text" name="description_parameter" placeholder="${requestScope.event.description}" class="form-control">
              </div>
              <button type="submit" class="btn btn-primary">Valider</button>
            </form>
            
</body>
</html>