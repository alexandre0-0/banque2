package controller;
// version master modifier
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
        
        String sNoCommercial = request.getParameter("noCommercial");
        try {
            int noCommercial = Integer.parseInt(sNoCommercial);
            Commercial commercialActif = Commercial.getCommercial(noCommercial);
            
            request.setAttribute("commercialActif", commercialActif);
            request.setAttribute("listeDesCommerciaux", Commercial.getListeCommerciaux());
        } catch (SQLException ex) {
            request.setAttribute("msgErreur", "Erreur SQL : " + ex.getErrorCode()); 
        } catch (NumberFormatException ex) {
            request.setAttribute("msgErreur", "Aucun commercial actif");
        }
        
        request.getRequestDispatcher("WEB-INF/commercial.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/commercial.jsp").forward(request, response);
    }

}
