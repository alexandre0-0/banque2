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
public class Client {
    
    private int noClient;
    private String nom;
    private String email;

    public Client(int noClient, String nom, String email) {
        this.noClient = noClient;
        this.nom = nom;
        this.email = email;
    }

    public int getNoClient() {
        return noClient;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.noClient;
        hash = 89 * hash + Objects.hashCode(this.nom);
        hash = 89 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
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
        return true;
    }
    
    public static Client getClient(int noClient) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM client WHERE no_client=" + noClient;
        ResultSet rs = stmt.executeQuery(sql);
        Client client = null;
        if (rs.next()) {
            client = new Client(rs.getInt("no_client"), rs.getString("nom"), 
                    rs.getString("email"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return client;
    }
    
}
