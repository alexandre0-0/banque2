<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="En-tête des pages"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" description="titre de la page" %>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <meta name="viewport"
              content="width=device-width, initial-scale=1">
    </head>
    <body>
        <nav id="menu">
            <c:if test="${sessionScope['commercialActif'] != null}">
                <c:set var="user" value="${sessionScope['commercialActif']}"/>
                <a href=".">Accueil</a> -
                <a href="commercial">Commercial</a> -
            </c:if>

            <c:if test="${sessionScope['clientActif'] != null}">
                <c:set var="user" value="${sessionScope['clientActif']}"/>
                <a href=".">Accueil</a> - 
                <a href="comptes?noClient=${user.noClient}">Comptes</a> - 
                <a href="virement?noClient=${user.noClient}">Faire un virement</a>
            </c:if>


            <span style="float: right;">
                ${applicationScope["nbUtilisateurs"]} utilisateurs -
                ${applicationScope["nbIdentifies"]} identifiés
                <c:if test="${user != null}">
                    <form style="display: inline;" action="deconnexion" method="post">
                        <button type="submit">Déconnecter ${user.email}</button>
                    </form>
                </c:if>
            </span>
        </nav>
        <hr/>