package com.cefisi.banquespring.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Commercial;
import model.Compte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommercialController {

    @RequestMapping(value = "/commercial-{noCommercial}",
            method = RequestMethod.GET)
    public String getComptesDuCommercial(
            ModelMap map,
            @PathVariable(value = "noCommercial") int noCommercial) throws SQLException {
        map.put("noCommercial", noCommercial);
        List<Commercial> listeDesCommerciaux = Commercial.getListeCommerciaux();
        map.put("listeDesCommerciaux", listeDesCommerciaux);
        
        
        

        
        return "commercial";
    }

}