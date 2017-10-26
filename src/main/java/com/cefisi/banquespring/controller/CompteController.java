/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.banquespring.controller;

import java.sql.SQLException;
import java.util.List;
import javax.validation.Valid;
import model.Compte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author alexa
 */
@Controller
public class CompteController {

    @RequestMapping(value = "/client-{noClient}/creationcompte", method = RequestMethod.GET)
    public String getCreationCompte(
            ModelMap map, // les données à transmettre a la vue (request.setAttribute
            @PathVariable(value = "noClient") int noClient
    ) throws SQLException {
        // le compte sera accessible dans le modelAttribute du form
        map.put("compte", new Compte());
        map.put("action", "Créer");
        map.put("titre", "Créer un compte pour le client " + noClient);
        return "formCompte";
    }

    @RequestMapping(value = "/client-{noClient}/creationcompte", method = RequestMethod.POST)
    public String postCreationCompte(
            @Valid
            @ModelAttribute("compte") Compte compte,
            BindingResult result, ModelMap map, @PathVariable(value = "noClient") int noClient
    ) throws SQLException {
        String suite = "formCompte";
        if (!result.hasErrors()) {
            compte.inserer();
            suite = "redirect:/client-" + noClient + "/comptes";
        } else {
            map.put("action", "Créer");
            map.put("titre", "Créer un compte pour le client " + noClient);
        }
        return suite;

    }
}
