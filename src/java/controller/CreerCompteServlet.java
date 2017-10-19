package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Compte;

/**
 *
 * @author Lamine
 */
@WebServlet(name = "CreerCompteServlet", urlPatterns = {"/creerCompte"})
public class CreerCompteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sNoClient = request.getParameter("noClient");
        try {
            int numeroClient = Integer.parseInt(sNoClient);
            request.setAttribute("noClientEnvoye", numeroClient);
        } catch (NumberFormatException e) {
            request.setAttribute("msgErreur", "Client inexistant");
        }
        
        request.getRequestDispatcher("WEB-INF/creerCompte.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean estOk = false;
        String sMontant = request.getParameter("montant");
        String sNoClient = request.getParameter("noClient");
        
        try {
            double montant = Double.parseDouble(sMontant);
            int numeroClient = Integer.parseInt(sNoClient);
            if(montant >= 200) {
                Compte compte = new Compte(0, montant, numeroClient);
                compte.inserer();
                request.setAttribute("msgSucces", "Compte créé pour le client 1 sous le n° " + compte.getNoCompte());
                estOk = true;
            } else {
                request.setAttribute("msgErreur", "Entrer un montant suffisant (>=200) ");
            }
        } catch (NumberFormatException exc) {
            request.setAttribute("msgErreur", "Entrer un nombre (ex : 5.2)");
        } catch (SQLException ex) {
            request.setAttribute("msgErreur", "Client n° "  +"inexistant");
        }
        
        if(estOk) {
            response.sendRedirect("comptes?noClient=" + sNoClient);
        } else {
            request.getRequestDispatcher("WEB-INF/creerCompte.jsp").forward(request, response);
        }
        
    }

}
