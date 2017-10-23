<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<p:header title="Commercial"/>
        <h1 class="text-center text-primary">Connexion à votre espace</h1>
        
        <form class="col-md-6 col-md-offset-3" method="post">
            <div class="form-group">
                <label for="email">Email (ex : lampion@mondass.com) </label>
                <input id="email" name="email" class="form-control" type="email" placeholder="Saisissez votre email" required />
            </div>
            
            <div class="form-group">
                <label for="mdp">Mot de passe : (ex :anatole) </label>
                <input id="mdp" name="mdp" class="form-control" type="password" placeholder="Saisissez votre mot de passe" required />
            </div>
            
            <div class="form-check">
                <input class="form-check-input" type="checkbox" />
                <label class="form-check-label">Se souvenir de moi</label>
            </div>
            
            <button class="btn btn-primary" type="submit" style="margin-top: 10px;">Se connecter</button>
            
            
        </form>
        <c:if test="${msgErreur != null}">
            <br />
            <br />
            <span class="alert alert-danger">${msgErreur}</span>
        </c:if>
    </body>
</html>
