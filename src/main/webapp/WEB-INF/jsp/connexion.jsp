<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<p:header title="Commercial"/>
<h1>c'est good</h1>


<form:form method="post" modelAttribute="user">
    <div >
        <label>
            Mail :
            <form:input  type="email" path="email"/>
            <form:errors path="email" cssClass="erreur"/>  
        </label>
    </div> 
    <div class="form-group">
        <label>
            Mot de passe :
            <form:input  type="password" path="mdp" />
            <form:errors path="mdp" cssClass="erreur"/> 
        </label>
    </div>


    <br/>
    <button type="submit">Connecter</button>
    <div>${message}</div>
    <input type="hidden" name="tartempion" value="lui"/>
</form:form>

</body>
</html>