package com.cefisi.banquespring.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnexionController {

    @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public String getConnexion(ModelMap map)
            throws Exception {
        User user = new User();
        map.put("user", user);
        return "connexion";
    }

    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String postConnexion(HttpSession session,
            User user,
            BindingResult result,
            ModelMap map) throws Exception {
        boolean estTrouve = false;
        String page = "connexion"; // si user inconnu
        if (!result.hasErrors()) {
            Commercial commercialActif = Commercial.getByEmailEtMdp(user.getEmail(), user.getMdp());
            if (commercialActif != null) {
                estTrouve = true;
                session.setAttribute("commercialActif", commercialActif);
                page = "redirect:/commercial-" + commercialActif.getNoCommercial();
            } else {
                Client clientActif = Client.getByEmailEtMdp(user.getEmail(), user.getMdp());
                if (clientActif != null) {
                    estTrouve = true;
                    session.setAttribute("clientActif", clientActif);
                    page = "redirect:/client-" + clientActif.getNoClient();
                }
            }
        }
        if (!estTrouve) {
            map.put("user", user);
        }
        return page;

    }

    @RequestMapping(value = "/deconnexion", method = RequestMethod.POST)
    public String deconnecter(
            HttpSession session,
            User user,
            ModelMap map
    ) {
        session.invalidate();
        user = new User();
        map.put("user", user);
        return "connexion";
    }

}
