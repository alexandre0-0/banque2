<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body class="container">
        <h1 class="text-center text-primary">Connexion à votre espace</h1>
        
        <form class="col-md-6 col-md-offset-3" method="post">
            <div class="form-group">
                <label for="email">Email : </label>
                <input id="email" name="email" class="form-control" type="email" placeholder="Saisissez votre email" required />
            </div>
            
            <div class="form-group">
                <label for="mdp">Mot de passe : </label>
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
