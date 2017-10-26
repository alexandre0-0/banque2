/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lamine
 */


public class Client extends User {
    
    private int noClient;
    private String commentaire;

    public Client(int noClient, String nom, String email, String commentaire, String mdp) {
        this.noClient = noClient;
        this.nom = nom;
        this.email = email;
        this.commentaire = commentaire;
        this.mdp = mdp;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    public int getNoClient() {
        return noClient;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.noClient;
        hash = 89 * hash + Objects.hashCode(this.nom);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.commentaire);
        hash = 89 * hash + Objects.hashCode(this.mdp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (this.noClient != other.noClient) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        if (!Objects.equals(this.mdp, other.mdp)) {
            return false;
        }
        return true;
    }

    
    
        public static ArrayList<Client> getListeClient() throws SQLException {
        ArrayList<Client> listeClient = new ArrayList<Client>();
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM client";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listeClient.add(new Client(
                    rs.getInt("no_client"), 
                    rs.getString("nom"), 
                    rs.getString("email"), 
                    rs.getString("commentaire"), 
                    rs.getString("mdp")));
        }
        rs.close();
        stmt.close();
        connection.close();
        return listeClient;
    }
    
    public static Client getClient(int noClient) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM client WHERE no_client=" + noClient;
        ResultSet rs = stmt.executeQuery(sql);
        Client client = null;
        if (rs.next()) {
            client = new Client(rs.getInt("no_client"), rs.getString("nom"), 
                    rs.getString("email"), rs.getString("commentaire"), rs.getString("mdp"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return client;
    }
    
    public static Client getByEmailEtMdp(String email, String mdp) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM client";
        ResultSet rs = stmt.executeQuery(sql);
        Client client = null;
        while(rs.next()) {
            if(rs.getString("email").equals(email) && rs.getString("mdp").equals(mdp)) {
                client = new Client(
                        rs.getInt("no_client"),
                        rs.getString("nom"), 
                        rs.getString("email"), 
                        rs.getString("commentaire"),
                        rs.getString("mdp"));
            }
        }
        rs.close();
        stmt.close();
        connection.close();
        return client;
    }
}
