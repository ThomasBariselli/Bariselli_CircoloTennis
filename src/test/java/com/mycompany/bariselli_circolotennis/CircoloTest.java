/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import Eccezioni.EccezioneCodiceNonPresente;
import Eccezioni.EccezioneDataNonValida;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class CircoloTest {
    
    
    /**
     * Test of getnPrenotazioniPresenti method, of class Circolo.
     */
    @Test
    public void testGetnPrenotazioniPresenti() throws EccezioneDataNonValida, EccezioneCodiceNonPresente 
    {
        Circolo c1=new Circolo();
        
        int atteso=0;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("N prenotazioni presenti con 0 prenotazioni presenti ",atteso, attuale);
        Maestro maestro=new Maestro("","");
        Prenotazione p=new Prenotazione(0, "Thomas", "Bariselli", 23, 12, 2021, 8, 0, maestro);
        c1.aggiungiPrenotazione(p);
        atteso=1;
        attuale=c1.getnPrenotazioniPresenti();
        assertEquals("N prenotazioni presenti con 1 prenotazione presente ",atteso, attuale);
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Thomas", "Bariselli", 24, 12, 2021, 8, 0, maestro);
        c1.aggiungiPrenotazione(p1);
        atteso=2;
        attuale=c1.getnPrenotazioniPresenti();
        assertEquals("N prenotazioni presenti con 2 prenotazioni presenti ",atteso, attuale);
        c1.rimuoviPrenotazione(0);
        atteso=1;
        attuale=c1.getnPrenotazioniPresenti();
        assertEquals("N prenotazioni presenti dopo eliminazione ",atteso, attuale);
    }

    /**
     * Test of getNUM_MAX_PRENOTAZIONI method, of class Circolo.
     */
    @Test
    public void testGetNUM_MAX_PRENOTAZIONI() 
    {
        Circolo c1=new Circolo();
        
        int atteso=2928;
        int attuale=c1.getNUM_MAX_PRENOTAZIONI();
        
        assertEquals("test get num max prernotazioni ",atteso,attuale);
    }

    /**
     * Test of getPrenotazione method, of class Circolo.
     */
    @Test
    public void testGetPrenotazione() {
    }

    /**
     * Test of getMaestro method, of class Circolo.
     */
    @Test
    public void testGetMaestro() {
    }

    /**
     * Test of aggiungiPrenotazione method, of class Circolo.
     */
    @Test
    public void testAggiungiPrenotazione() throws Exception {
    }

    /**
     * Test of rimuoviPrenotazione method, of class Circolo.
     */
    @Test
    public void testRimuoviPrenotazione() throws Exception {
    }

    /**
     * Test of getPrenotazioniCliente method, of class Circolo.
     */
    @Test
    public void testGetPrenotazioniCliente() throws Exception {
    }

    /**
     * Test of getnPrenotazioniordinateMaestro method, of class Circolo.
     */
    @Test
    public void testGetnPrenotazioniordinateMaestro() throws Exception {
    }

    /**
     * Test of getPrenotazioniMaestro method, of class Circolo.
     */
    @Test
    public void testGetPrenotazioniMaestro() throws Exception {
    }

    /**
     * Test of toString method, of class Circolo.
     */
    @Test
    public void testToString() {
    }

    /**
     * Test of esportaPrenotazioniCSV method, of class Circolo.
     */
    @Test
    public void testEsportaPrenotazioniCSV() throws Exception {
    }

    /**
     * Test of salvaCircolo method, of class Circolo.
     */
    @Test
    public void testSalvaCircolo() throws Exception {
    }

    /**
     * Test of caricaCircolo method, of class Circolo.
     */
    @Test
    public void testCaricaCircolo() throws Exception {
    }
    
}
