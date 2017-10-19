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
public class Commercial {

    private int noCommercial;
    private String nom;

    public Commercial(int noCommercial, String nom) {
        this.noCommercial = noCommercial;
        this.nom = nom;
    }

    public int getNoCommercial() {
        return noCommercial;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.noCommercial;
        hash = 37 * hash + Objects.hashCode(this.nom);
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
        final Commercial other = (Commercial) obj;
        if (this.noCommercial != other.noCommercial) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    public ArrayList<Client> getListeClients() throws SQLException {
        ArrayList<Client> listeClients = new ArrayList<Client>();
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM portefeuille WHERE no_commercial = " + noCommercial + 
                " AND (no_client, date_attribution) IN "
                + "(SELECT no_client, MAX(date_attribution) FROM portefeuille "
                + "GROUP BY no_client)";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listeClients.add(Client.getClient(rs.getInt("no_client")));
        }
        rs.close();
        stmt.close();
        connection.close();
        return listeClients;
    }
    
    
    public static ArrayList<Commercial> getListeCommerciaux() throws SQLException {
        ArrayList<Commercial> listeCommerciaux = new ArrayList<Commercial>();
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM commercial";
        ResultSet rs = stmt.executeQuery(sql);
        //listeCommerciaux.add(Commercial.getCommercial(rs.getInt("no_commercial")));
        while (rs.next()) {
            listeCommerciaux.add(new Commercial(rs.getInt("no_commercial"), rs.getString("nom")));
        }
        rs.close();
        stmt.close();
        connection.close();
        return listeCommerciaux;
    }
    
    public static Commercial getCommercial(int noDuCommercial) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM commercial WHERE no_commercial=" + noDuCommercial;
        ResultSet rs = stmt.executeQuery(sql);
        Commercial commercial = null;
        if (rs.next()) {
            commercial = new Commercial(rs.getInt("no_commercial"), rs.getString("nom"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return commercial; 
    }

}
