package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Commercial;
import model.Compte;

/**
 *
 * @author Lamine
 */
@WebServlet(name = "CommercialServlet", urlPatterns = {"/commercial"})
public class CommercialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Object sCommercial = request.getSession().getAttribute("commercialActif");
        Commercial commercialActif = (Commercial) sCommercial;
        request.setAttribute("commercialActif", commercialActif);
        try {
            request.setAttribute("listeDesCommerciaux", Commercial.getListeCommerciaux());
        } catch (SQLException ex) {
            request.setAttribute("msgErreur", "Erreur SQL : " + ex.getErrorCode()); 
        }
        
        if(userEstAutorise(request)) {
            request.getRequestDispatcher("WEB-INF/commercial.jsp").forward(request, response);
        } else if (!userEstAutorise(request) && commercialActif != null) {
            request.getRequestDispatcher("WEB-INF/pasAutorise.jsp").forward(request, response);
        } else {
            response.sendRedirect("connexion");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("deconnexion".equals(request.getParameter("deconnexion"))) {
            session.invalidate();
            response.sendRedirect("connexion");
        }
    }
    
    protected boolean userEstAutorise(HttpServletRequest request) {
        Object sSessionCommercial = request.getSession().getAttribute("commercialActif");
        Commercial sessionCommercial = (Commercial) sSessionCommercial;        
        String sNoCommercial = request.getParameter("noCommercial");
        int noCommercial = -1;
        
        try {
            noCommercial = Integer.parseInt(sNoCommercial);
        } catch (NumberFormatException e) {
            request.setAttribute("msgErreur", "Commercial non connecté");
        }
        return noCommercial != -1 &&
               sessionCommercial != null &&
               noCommercial == sessionCommercial.getNoCommercial();
    }

}
