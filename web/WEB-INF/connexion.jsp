<%-- 
    Document   : connexion
    Created on : 19 oct. 2017, 14:20:59
    Author     : Lamine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <h1>Connexion à votre espace</h1>
        
        <form method="post">
            <label>
                Identifiant : 
                <input class="form-control" type="text" required />
            </label>
            <label>
                Mot de passe : 
                <input class="form-control" type="password" required />
            </label>
        </form>
    </body>
</html>
