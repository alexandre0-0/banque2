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
import javax.servlet.http.HttpSession;
import model.Commercial;

/**
 *
 * @author Lamine
 */
@WebServlet(name = "ConnexionServlet", urlPatterns = {"/connexion"})
public class ConnexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.setAttribute("msgErreur", null);
        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sEmail = request.getParameter("email");
        String sMdp = request.getParameter("mdp");
        boolean connexionReussie = false;
        Commercial commercialActif = null;
        
        try {
            commercialActif = Commercial.getByEmailEtMdp(sEmail, sMdp);
            if(commercialActif != null) {
                HttpSession session = request.getSession();
                session.setAttribute("commercialActif", commercialActif);
                connexionReussie = true;
            } else {
                request.setAttribute("msgErreur", "Email ou mot de passe invalide");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgErreur", "Erreur SQL : " + ex.getErrorCode());
        }
        
        if(connexionReussie) {
            response.sendRedirect("commercial?noCommercial=" + commercialActif.getNoCommercial());
        } else {
            request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
        }
    }

}
