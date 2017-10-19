/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "VirementServlet", urlPatterns = {"/virement"})
public class VirementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sNoClient = request.getParameter("noClient");
        try {
            int noClient = Integer.parseInt(sNoClient);
            List<Compte> listeComptes = Compte.getComptes(noClient);
            List<Compte> listeTousLesComptes = Compte.getTousLesComptes();
            request.setAttribute("listeComptes", listeComptes);
            request.setAttribute("numeroDuClient", noClient);
            request.setAttribute("listeTousLesComptes", listeTousLesComptes);
        } catch (NumberFormatException e) {
            request.setAttribute("msgErreur", "Le client est inexistant dans notre base de données");
        } catch (SQLException e) {
            request.setAttribute("msgErreur", "Erreur SQL avec la méthode statique Compte.getComptes()");
        }
        
        request.getRequestDispatcher("WEB-INF/virement.jsp").forward(request, response);
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
        
        boolean virementRealise = false;
        String sCompteDebiteur = request.getParameter("debiteur");
        String sCompteCrediteur = request.getParameter("crediteur");
        String sMontantVirement = request.getParameter("montantVirement");
        String sNoClient = request.getParameter("noClient");
        
        try {
            int noCompteDebiteur = Integer.parseInt(sCompteDebiteur);
            int noCompteCrediteur = Integer.parseInt(sCompteCrediteur);
            double montantVirement = Double.parseDouble(sMontantVirement);
            Compte compteDebiteur = Compte.getById(noCompteDebiteur);
            compteDebiteur.virerSur(noCompteCrediteur, montantVirement);
            virementRealise = true;
            request.setAttribute("msgSucces", "Virement réalisé avec succès !");
        } catch (NumberFormatException e) {
            request.setAttribute("msgErreur", "Veuillez entrer un montant valide (ex : 250.30)");
            int noClient = Integer.parseInt(sNoClient);
            //response.sendRedirect("virement?noClient=" + noClient);
        } catch (SQLException e) {
            request.setAttribute("msgErreur", "Erreur SQL : " + e.getErrorCode());
        }
        
        if(virementRealise) {
            response.sendRedirect("comptes?noClient=" + sNoClient);
        } else {
            request.getRequestDispatcher("WEB-INF/virement.jsp").forward(request, response);
        }
    }

}
