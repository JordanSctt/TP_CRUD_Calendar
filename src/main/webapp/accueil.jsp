<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Calendrier</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
        <h1>Liste des events</h1>
        <br/>

        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Titre event</th>
                <th scope="col">Date</th>
                <th scope="col">Description</th>
                <th scope="col">Action</th>

            </tr>
            </thead>

            <tbody>

            <c:forEach items="${requestScope.events}" var="event">
                <tr>
                    <td><c:out value="${ event.titre }" /></td>
                    <td><c:out value="${ event.date }" /></td>
                    <td><c:out value="${ event.description }" /></td>                    
                    <td>
                    <form action="event/delete" method="POST"><button class="btn btn-outline-danger" name="eventRemove" type="submit" value="${event.id}">Supprimer</button></form>
                    <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/event/update?event_id=<c:out value="${ event.id }"/>"/>Modifier<a/>
                    </td>
                    
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </br>
        <a type="button" class="btn btn-secondary center" href="addEvent.jsp">Ajouter event</a>

</body>
</html>