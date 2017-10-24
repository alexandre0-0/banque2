package com.cefisi.banquespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;








@Controller
public class ClientController {

    @RequestMapping(value = "/client-{noClient}/comptes",
            // Répond à une URL comme /client-1/comptes
            method = RequestMethod.GET)
    //quand c'est une methode GET
    public String getComptesDuClient(
            // Ce nom est tout à fait libre
            ModelMap map,
            // Attributs de la reponse (equivaleunt du setAttribut)
            @PathVariable(value = "noClient") int noClient) {
        // associe la chaine noClient de l'URL au parametre entier noClient
        map.put("noClient", noClient);
        // Ajoute dans les attibuts de la reponse le noClient
        return "comptes";
        //Passe la main à la JSP WEB-INF/comptes.jsp
    }

}
