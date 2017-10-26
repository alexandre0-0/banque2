<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
    <p:header title="${titre}"/>
    
    
    <h1>${titre}</h1>
    <form:form method="post" modelAttribute="compte">
      <%-- path met name="solde id="solde" et 
        value=la valeur de compte.solde--%>
      Montant :
      <form:input path="solde"/>
      <form:errors path="solde" cssClass="erreur"/>
      
    <%--  <div class="form-group">
          <label>
              Pseudo :
              <form:input class="form-control" type="email" path="pseudo" />
          </label>
      </div>
      --%>
      
      <br/>
      <button type="submit">${action}</button>
      <div>${message}</div>
      <input type="hidden" name="tartempion" value="lui"/>
    </form:form>
    </body>
</html>
