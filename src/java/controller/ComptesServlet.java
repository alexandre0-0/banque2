package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "ComptesServlet", urlPatterns = {"/comptes"})
public class ComptesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String sNoClient = request.getParameter("noClient");
        String sNoCommercial = request.getParameter("noCommercial");
        
        try {
            int numeroClient = Integer.parseInt(sNoClient);
            List<Compte> comptesClient = Compte.getComptes(numeroClient);
            request.setAttribute("comptesClient", comptesClient);
            request.setAttribute("numeroClient", numeroClient);
            
            int numeroCommercial = Integer.parseInt(sNoCommercial);
            request.setAttribute("numeroCommercial", numeroCommercial);
        } catch (NumberFormatException e) {
            request.setAttribute("msgErreur", "Entrer un nombre (ex : 5.2)");
        } catch (SQLException e) {
            request.setAttribute("msgErreur", "Client n° " + sNoClient +"inexistant");
        }
        
        request.getRequestDispatcher("WEB-INF/comptes.jsp").forward(request, response);
    }

}
