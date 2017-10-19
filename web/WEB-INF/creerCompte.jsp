<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Créer un compte</title>
    </head>
    <body class="container">
    <c:set var="noClient" value="1" />

    <h1>Création d'un compte pour le client ${noClientEnvoye}</h1>
    <br />
    <form method="post">
        <input type="hidden" value="${noClientEnvoye}" name="noClient" />
        <label>
            Montant initial :
            <input class="form-control" type="number" name="montant" />
        </label>
        <br />
        <br />
        <button class="btn btn-primary" type="submit">Créer le compte</button>
    </form>

    <c:if test="${msgSucces != null}">
        <span class="text-success">${msgSucces}</span>
    </c:if>
    <c:if test="${msgErreur != null}">
        <span class="text-danger">${msgErreur}</span>
    </c:if>
</body>
</html>
