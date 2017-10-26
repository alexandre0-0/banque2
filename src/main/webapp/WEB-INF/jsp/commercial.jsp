<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<p:header title="Commercial"/>

        <div class="row">
            <div class="col-md-8">
                <h1 class="text-center text-primary">
                    Bienvenue sur votre espace, 
                    <span class="text-center text-danger">${commercialActif.getNom()}</span>
                </h1>
            </div>
                <div class="col-md-4" style="padding-top: 23px; float: right;">
                <form method="post">
                    <button class="btn btn-primary" name="deconnexion" value="deconnexion" type="submit" >
                        Se déconnecter
                    </button>
                </form>
                
            </div>
        </div>
        
        <div class="row">
            <h2 class="text-center text-primary">Liste des commerciaux</h2>
        </div>       
        
        
        <table class="table table-striped table-hover">
            <thead>
                <tr class="info">    
                    <th>N° du commercial</th>
                    <th>Nom</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listeDesCommerciaux}" var="commercial">
                    <tr>
                        <th class="col-md-6">${commercial.getNoCommercial()} </th>
                        <th class="col-md-6">${commercial.getNom()}</th>
                    </tr>
                </c:forEach>
            </tbody>        
        </table>
        
        <br />
        <h2 class="text-center text-primary">Vos clients</h2>
        <table class="table table-striped table-hover">
            <thead>
                <tr class="info">    
                    <th class="col-md-4 col-xs-4">N° du client</th>
                    <th class="col-md-4 col-xs-4">Nom</th>
                    <th class="col-md-4 col-xs-4">Email</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${commercialActif.getListeClients()}" var="client">
                    <tr onclick="window.location='comptes?noClient=${client.getNoClient()}&noCommercial=${commercialActif.getNoCommercial()}';" style="cursor: pointer">
                        <th>${client.getNoClient()} </th>
                        <th>${client.getNom()}</th>
                        <th>${client.getEmail()}</th>
                        <th>
                            <a class="btn btn-primary" 
                               style="text-decoration: none; color:white;" 
                               href="creerCompte?noClient=${client.getNoClient()}">
                                Créer un nouveau compte
                            </a>
                        </th>
                    </tr>
                </c:forEach>
            </tbody>        
        </table>
                
        <c:if test="${msgSucces != null}">
            <br />
            <span class="alert alert-success">${msgSucces}</span>
        </c:if>
       
        <c:if test="${msgErreur != null}">
            <br />
            <span class="alert alert-danger">${msgErreur}</span>
        </c:if>
        
    </body>
</html>
