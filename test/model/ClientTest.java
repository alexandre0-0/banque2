/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lamine
 */
public class ClientTest {
    
    @Test
    public void testConstructeur() {
        Client instance = new Client(2, "Tintin", "tintin@herge.be");
        assertEquals(2, instance.getNoClient());
        assertEquals("Tintin", instance.getNom());
        assertEquals("tintin@herge.be", instance.getEmail());
    }
    
    @Test
    public void testGetClient() throws SQLException {
        Client instance = Client.getClient(2);
        assertEquals("Tintin", instance.getNom());
        assertEquals("tintin@herge.be", instance.getEmail());
    }
    
}
