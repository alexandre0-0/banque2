/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Compte;
import model.Database;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Lamine
 */
public class CompteTest {
    
    public CompteTest() {
    }

    /**
     * Test of getNoCompte method, of class Compte.
     */
//    @Test
    public void testGetNoCompte() {
        System.out.println("getNoCompte");
        Compte instance = null;
        int expResult = 0;
        int result = instance.getNoCompte();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNoCompte method, of class Compte.
     */
//    @Test
    public void testSetNoCompte() {
        System.out.println("setNoCompte");
        int noCompte = 0;
        Compte instance = null;
        instance.setNoCompte(noCompte);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNoClient method, of class Compte.
     */
//    @Test
    public void testGetNoClient() {
        System.out.println("getNoClient");
        Compte instance = null;
        int expResult = 0;
        int result = instance.getNoClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNoClient method, of class Compte.
     */
//    @Test
    public void testSetNoClient() {
        System.out.println("setNoClient");
        int noClient = 0;
        Compte instance = null;
        instance.setNoClient(noClient);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSolde method, of class Compte.
     */
//    @Test
    public void testGetSolde() {
        System.out.println("getSolde");
        Compte instance = null;
        double expResult = 0.0;
        double result = instance.getSolde();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSolde method, of class Compte.
     */
//    @Test
    public void testSetSolde() {
        System.out.println("setSolde");
        double solde = 0.0;
        Compte instance = null;
        instance.setSolde(solde);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testUpdate() throws SQLException, InstantiationException {
        Compte instance = new Compte(1, 1000.0, 1);
        instance.setNoClient(1);
        instance.setSolde(1000.0);
        instance.update();
        //Vérifier en lisant la BD
        Compte result = Compte.getById(1); //N° du compte
        Compte expected = new Compte(1, 1000.0, 1);
        
        assertEquals(expected, result);
        
    }
    
    @Test
    public void testGetByIdOk() throws SQLException, InstantiationException {
        System.out.println("getById ok");
        Compte expected = new Compte(1, 1000.0, 1);
        Compte result = Compte.getById(1);
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetByIdFail() throws SQLException, InstantiationException {
        System.out.println("getById pas trouvé");
        Compte result = Compte.getById(87);
        assertNull(result);
    }
    
    @Test
    public void testGetComptes() throws SQLException {
        List<Compte> comptesClient1 = Compte.getComptes(1);
        
        Compte compteNo1 = Compte.getById(1);
        Compte compteNo2 = Compte.getById(2);
        List<Compte> comptesExpected = new ArrayList<>();
        comptesExpected.add(compteNo1);
        comptesExpected.add(compteNo2);
        
        assertEquals(comptesExpected, comptesClient1);
    }
    
    @Before
    public void setUp() throws Exception {
        Connection connexion = Database.getConnection();
        CallableStatement cs = connexion.prepareCall("CALL banque_reset()");
        cs.execute();
        connexion.close();
    }
    
}