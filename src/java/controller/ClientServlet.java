/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Client;

/**
 *
 * @author alexa
 */
@WebServlet(name = "ClientServlet", urlPatterns = {"/client"})
public class ClientServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Object sClient = request.getSession().getAttribute("clientActif");
        Client clientActif = (Client) sClient;
        request.setAttribute("clientActif", clientActif);
        try {
            request.setAttribute("listeDesClients", Client.getListeClient());
        } catch (SQLException ex) {
            request.setAttribute("msgErreur", "Erreur SQL : " + ex.getErrorCode()); 
        }
        
        if(userEstAutorise(request)) {
            request.getRequestDispatcher("WEB-INF/client.jsp").forward(request, response);
        } else if (!userEstAutorise(request) && clientActif != null) {
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
        Object sSessionClient = request.getSession().getAttribute("clientActif");
        Client sessionClient = (Client) sSessionClient;        
        String sNoClient = request.getParameter("noClient");
        int noClient = -1;
        
        try {
            noClient = Integer.parseInt(sNoClient);
        } catch (NumberFormatException e) {
            request.setAttribute("msgErreur", "Client non connecté");
        }
        return noClient != -1 &&
               sessionClient != null &&
               noClient == sessionClient.getNoClient();
    }

}
