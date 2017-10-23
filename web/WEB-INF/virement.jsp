<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<p:header title="Commercial"/>
    <c:set var="noClient" value="" />

    <h1>Effectuer un virement - Client n°${numeroDuClient}</h1>    
    <br />
    
    <form method="post">
        <!--<input type="hidden" value="${noClient}" name="noClient" />-->
        
        <!-- Listes déroulantes des comptes à débiter et créditer -->
        <div class="form-group">
            <label for="debiteur">Compte à débiter :</label>
            <select class="form-control" id="debiteur" name="debiteur">
                <c:forEach items="${listeComptes}" var="compte">
                    <option value="${compte.getNoCompte()}">Compte n° ${compte.getNoCompte()} - Solde = ${compte.getSolde()}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="crediteur">Compte à créditer :</label>
            <select class="form-control" id="crediteur" name="crediteur">
                <c:forEach items="${listeTousLesComptes}" var="compte">
                    <option value="${compte.getNoCompte()}">Compte n° ${compte.getNoCompte()} - Solde = ${compte.getSolde()}</option>
                </c:forEach>
            </select>
        </div>
        <!-- FIN - Listes déroulantes des comptes à débiter et créditer -->
        
        <label>
            Montant du virement :
            <input class="form-control" type="number" name="montantVirement" />
        </label>
        <br />
        <br />
        <button class="btn btn-primary" type="submit">Effectuer le virement</button>
    </form>

    <c:if test="${msgSucces != null}">
        <br />
        <br />
        <span class="alert alert-success">${msgSucces}</span>
    </c:if>
    <c:if test="${msgErreur != null}">
        <br />
        <br />
        <span class="alert alert-danger">${msgErreur}</span>
    </c:if>
</body>
</html>
