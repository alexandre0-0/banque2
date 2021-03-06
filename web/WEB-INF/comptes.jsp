<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des comptes</title>
    </head>
    <body class="container">
        <h1 class="text-center text-info">Comptes du client ${noClient}</h1>
        
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <td>N° Compte</td>
                    <td>Solde</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${comptesClient}" var="compte">
                    <tr>
                        <td>${compte.getNoCompte()}</td>
                        <td>${compte.getSolde()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <br />
        <div class="row">
            <c:if test="${numeroCommercial != null && numeroClient != null}">
                <div class="col-md-12">
                    <a class="btn btn-block btn-primary" 
                       style="text-decoration: none; color:white;" 
                       href="creerCompte?noClient=${numeroClient}">
                            Créer un nouveau compte
                    </a>
                </div>
            </c:if>
            
            <c:if test="${numeroClient != null && numeroCommercial == null}">
                <div class="col-md-12">
                    <a class="btn btn-block btn-warning" 
                       style="text-decoration: none; color:white;" 
                       href="virement?noClient=${numeroClient}">
                            Effectuer un virement
                    </a>
                </div>
            </c:if>
        </div>
        
        
        <!--
        <form action="creerCompte" method="post">
            <input type="hidden" name="noClient" value="${client.noClient}"/>
            <input type="text" name="valeur" />
            <button type="submit">Créer un compte</button>
        </form>
        -->
    </body>
</html>
