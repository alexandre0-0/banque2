package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michel
 */
public class Compte {

    public static List<Compte> getComptes(int noClient) throws SQLException {
        List<Compte> comptesDuClient = new ArrayList<>();
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT no_compte FROM compte WHERE no_client=" + noClient;
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            comptesDuClient.add(Compte.getById(rs.getInt("no_compte")));
        }
        rs.close();
        stmt.close();
        connection.close();
        return comptesDuClient;
    }
    
    public static List<Compte> getTousLesComptes() throws SQLException {
        List<Compte> comptesDuClient = new ArrayList<>();
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT no_compte FROM compte";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            comptesDuClient.add(Compte.getById(rs.getInt("no_compte")));
        }
        rs.close();
        stmt.close();
        connection.close();
        return comptesDuClient;
    }

    private int noCompte, noClient;
    private double solde;
    
    //A effacer
    private String pseudo;
    
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    //A effacer

   

    public Compte() {
    }

    /**
     * Crée en mémoire un compte.
     * Si noCompte == 0, le compte n'est pas en base de données.
     * @param noCompte
     * @param solde
     * @param noClient 
     */
    public Compte(int noCompte, double solde, int noClient) {
        this.noCompte = noCompte;
        this.noClient = noClient;
        this.solde = solde;
    }

    public int getNoCompte() {
        return noCompte;
    }

    /**
     * Requiert noCompte > 0;
     *
     * @param noCompte
     */
    public void setNoCompte(int noCompte) {
        assert noCompte > 0;
        this.noCompte = noCompte;
    }

    public int getNoClient() {
        return noClient;
    }

    /**
     * Requiert noClient > 0
     */
    public void setNoClient(int noClient) {
        assert noClient > 0;
        this.noClient = noClient;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.noCompte;
        hash = 97 * hash + this.noClient;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.solde) ^ (Double.doubleToLongBits(this.solde) >>> 32));
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
        final Compte other = (Compte) obj;
        if (this.noCompte != other.noCompte) {
            return false;
        }
        if (this.noClient != other.noClient) {
            return false;
        }
        if (Double.doubleToLongBits(this.solde) != Double.doubleToLongBits(other.solde)) {
            return false;
        }
        return true;
    }

    public void update() throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "UPDATE compte SET solde=?, no_client=? WHERE no_compte=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, solde); // On commence à 1, pas 0
        stmt.setInt(2, noClient);
        stmt.setInt(3, noCompte);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    /**
     * Compte de nÂ° unNoCompte, ou null s'il n'est pas trouvÃ© dans la base de
     * donnÃ©es
     *
     * @param unNoCompte
     * @return
     */
    public static Compte getById(int unNoCompte) throws SQLException {
        Compte result = null;
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM compte WHERE no_compte=" + unNoCompte;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            result = new Compte(rs.getInt("no_compte"),
                    rs.getDouble("solde"),
                    rs.getInt("no_client"));
        }
        rs.close();
        stmt.close();
        connection.close();
        assert result == null || result.noCompte == unNoCompte;
        return result;
    }

    /**
     * Normalement, requiert de ne pas mettre en dessous du découvert autorisé,
     * et de ne pas faire dépasser le plafond de l'autre.
     * </br>Requierait Compte.getById(noAutreCompte) != null
     *
     * @param noAutreCompte
     * @param montant
     */
    public void virerSur(int noAutreCompte, double montant) throws SQLException {
        Connection connection = Database.getConnection();
        // Débuter une transaction
        connection.setAutoCommit(false);
        // 1er ordre
        String sql = "UPDATE compte SET solde = solde - ? WHERE no_compte = ?";
        PreparedStatement retrait = connection.prepareStatement(sql);
        retrait.setDouble(1, montant);
        retrait.setInt(2, noCompte);
        int nbRetraits = retrait.executeUpdate();
        if (nbRetraits == 0) {
            // Annuler la transaction
            connection.rollback();
            throw new SQLException("Compte " + noCompte + " inexistant");
        }

        // 2eme ordre
        sql = "UPDATE compte SET solde = solde + ? WHERE no_compte = ?";
        PreparedStatement depot = connection.prepareStatement(sql);
        depot.setDouble(1, montant);
        depot.setInt(2, noAutreCompte);
        int nbDepots = depot.executeUpdate();
        if (nbDepots == 0) {
            // Annuler la transaction
            connection.rollback();
            throw new SQLException("Compte " + noAutreCompte + " inexistant");
        } else {
            solde -= montant;
        }

        // Valider la transaction
        connection.commit();
        retrait.close();
        depot.close();
        connection.close();
    }

    public void inserer() throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO compte(solde, no_client) VALUES(?, ?)";
        PreparedStatement reqInserer = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        reqInserer.setDouble(1, solde);
        reqInserer.setInt(2, noClient);
        reqInserer.executeUpdate();
        
        //Récupération de la dernière clé
        ResultSet keys = reqInserer.getGeneratedKeys();
        if(keys.next()) {
            noCompte = keys.getInt(1);
        }
        
        reqInserer.close();
        connection.close();
    }
    
    public void insererAvecTransaction() throws SQLException {
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(false);
        
            String sql = "INSERT INTO compte(solde, no_client) VALUES(?, ?)";
            PreparedStatement reqInserer = connection.prepareStatement(sql);
            reqInserer.setDouble(1, solde);
            reqInserer.setInt(2, noClient);
            reqInserer.executeUpdate();
            reqInserer.close();

            sql = "SELECT MAX(no_compte) AS no_compte FROM compte";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            assert rs.next();
            noCompte = rs.getInt("no_compte");

            connection.commit();
            stmt.close();
        } catch (SQLException e) {
            connection.rollback();
            //Renvoyer l'exception car il y a échec
            throw e;
        } finally {
            connection.close();
        }
        
    }

}
