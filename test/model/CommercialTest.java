/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lamine
 */
public class CommercialTest {
    
    @Test
    public void testConstructeur() {
        Commercial instance = new Commercial(1, "Lampion");
        assertEquals(1, instance.getNoCommercial());
        assertEquals("Lampion", instance.getNom());
    }
    
    @Test
    public void testGetCommercial() throws SQLException {
        Commercial instance = Commercial.getCommercial(1);
        assertEquals(1, instance.getNoCommercial());
        assertEquals("Lampion", instance.getNom());
    }
    
    @Test
    public void testGetListeClients() throws SQLException {
        ArrayList<Client> clients = new ArrayList<Client>();
        Client client2 = Client.getClient(2);
        Client client3 = Client.getClient(3);
        clients.add(client2);
        clients.add(client3);
        
        Commercial commercial1 = Commercial.getCommercial(1);
        
        assertEquals(clients, commercial1.getListeClients());
    }
    
    @Test
    public void testGetListeCommerciaux() throws SQLException {
        ArrayList<Commercial> listeCommerciaux = new ArrayList<>();
        Commercial commercial1 = Commercial.getCommercial(1);
        Commercial commercial2 = Commercial.getCommercial(2);
        Commercial commercial3 = Commercial.getCommercial(3);
        listeCommerciaux.add(commercial1);
        listeCommerciaux.add(commercial2);
        listeCommerciaux.add(commercial3);
        
        ArrayList<Commercial> listeDesCommerciaux = Commercial.getListeCommerciaux();
        
        assertEquals(listeCommerciaux, listeDesCommerciaux);
    }
}
