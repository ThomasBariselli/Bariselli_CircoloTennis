/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import Eccezioni.EccezioneCodiceNonPresente;
import Eccezioni.EccezioneDataNonValida;
import Eccezioni.EccezioneNessunMaestroPresente;
import Eccezioni.EccezioneNonPresente;
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
    public void testGetPrenotazione() 
    {
        Circolo c1=new Circolo();
        
        Prenotazione atteso=null;
        Prenotazione attuale=c1.getPrenotazione(-1);
        assertEquals("get prenotazione con posizione negativa",atteso,attuale);
        
        attuale=c1.getPrenotazione(c1.getNUM_MAX_PRENOTAZIONI()+1);
        assertEquals("get prenotazione con posizione maggiore di n max prenotazioni",atteso,attuale);
        
        attuale=c1.getPrenotazione(4);
        assertEquals("get prenotazione con posizione vuota",atteso, attuale);
    }

    /**
     * Test of getMaestro method, of class Circolo.
     */
    @Test
    public void testGetMaestro() 
    {
        Circolo c1=new Circolo();
        
        Maestro atteso=null;
        Maestro attuale=c1.getMaestro(-1);
        assertEquals("get maestro con posizione negativa",atteso,attuale);
        
        attuale=c1.getMaestro(c1.getNUM_MAX_PRENOTAZIONI()+1);
        assertEquals("get maestro con posizione maggiore di n max prenotazioni",atteso,attuale);
        
        attuale=c1.getMaestro(4);
        assertEquals("get maestro con posizione vuota",atteso, attuale);
    
    }

    /**
     * Test of aggiungiPrenotazione method, of class Circolo.
     */
    @Test
    public void testAggiungiPrenotazione() throws EccezioneDataNonValida  
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Thomas", "Bariselli", 24, 12, 2021, 8, 0, maestro1);
        c1.aggiungiPrenotazione(p1);
        int atteso=1;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("Aggiunta di una prenotazione",atteso,attuale);
    }
    @Test(expected=EccezioneDataNonValida.class)
    public void testAggiungiPrenotazioneAntecedente() throws EccezioneDataNonValida  
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Thomas", "Bariselli", 24, 4, 2021, 8, 0, maestro1);
        c1.aggiungiPrenotazione(p1);
        int atteso=1;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("sollevazione eccezione data non valida perchè antecedente a quella odierna",atteso,attuale);
    }
    @Test(expected=EccezioneDataNonValida.class)
    public void testAggiungiPrenotazioneOraNonValida() throws EccezioneDataNonValida  
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Thomas", "Bariselli", 24, 7, 2021, 23, 0, maestro1);
        c1.aggiungiPrenotazione(p1);
        int atteso=1;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("sollevazione eccezione ora non valida perchè non in orario lavorativo",atteso,attuale);
    }
    @Test(expected=EccezioneDataNonValida.class)
    public void testAggiungiPrenotazioneOraOccupata() throws EccezioneDataNonValida  
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Thomas", "Bariselli", 24, 7, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2, "Thomas", "Bariselli", 24, 7, 2021, 8, 0, maestro1);
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        int atteso=1;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("sollevazione eccezione ora occupata ",atteso,attuale);
    }

    /**
     * Test of rimuoviPrenotazione method, of class Circolo.
     */
    @Test
    public void testRimuoviPrenotazione() throws Exception, EccezioneDataNonValida, EccezioneDataNonValida 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Thomas", "Bariselli", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2, "Thomas", "Bariselli", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3, "Thomas", "Bariselli", 26, 12, 2021, 8, 0, maestro1);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        c1.rimuoviPrenotazione(1);
        
        int atteso=2;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("eliminazione di una prenotazione ",atteso, attuale);
    }
    /**
     * 
     * @throws EccezioneDataNonValida
     * @throws EccezioneCodiceNonPresente 
     */
    @Test(expected=EccezioneCodiceNonPresente.class)
    public void testRimuoviPrenotazioneException() throws EccezioneCodiceNonPresente  
    {
        Circolo c1=new Circolo();
        
        c1.rimuoviPrenotazione(c1.getNUM_MAX_PRENOTAZIONI()+1);
        
        int atteso=0;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("eccezione >num max prenotazioni ",atteso, attuale);
    }
    
    @Test(expected=EccezioneCodiceNonPresente.class)
    public void testRimuoviPrenotazioneExceptionNegativo() throws EccezioneCodiceNonPresente  
    {
        Circolo c1=new Circolo();
        
        c1.rimuoviPrenotazione(-1);
        
        int atteso=0;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("eccezione posizione <0 ",atteso, attuale);
    }
    
    @Test(expected=EccezioneCodiceNonPresente.class)
    public void testRimuoviPrenotazioneExceptionVuota() throws EccezioneCodiceNonPresente  
    {
        Circolo c1=new Circolo();
        
        c1.rimuoviPrenotazione(5);
        
        int atteso=0;
        int attuale=c1.getnPrenotazioniPresenti();
        assertEquals("eccezione posizione <0 ",atteso, attuale);
    }

    /**
     * Test of getPrenotazioniCliente method, of class Circolo.
     */
    @Test
    public void testGetPrenotazioniCliente() throws Exception 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Angelo", "Arrigoni", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2, "Angelo", "Arrigoni", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3, "Gianni", "Ziliani", 26, 12, 2021, 8, 0, maestro1);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        
        Prenotazione[] atteso={p1,p2};
        Prenotazione[] attuale=c1.getPrenotazioniCliente("Angelo", "Arrigoni");
        
        assertArrayEquals("get prenotazione cliente dopo aggiunta di 3 prenotazioni",atteso, attuale);
        
    }
    
    @Test(expected=EccezioneNonPresente.class)
    public void testGetPrenotazioniClienteNonPresente() throws Exception 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Angelo", "Arrigoni", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2, "Angelo", "Arrigoni", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3, "Gianni", "Ziliani", 26, 12, 2021, 8, 0, maestro1);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        
        Prenotazione[] atteso={p1,p2};
        Prenotazione[] attuale=c1.getPrenotazioniCliente("nome", "cognome");
        
        assertArrayEquals("get prenotazione cliente dopo aggiunta di 3 prenotazioni",atteso, attuale);
        
    }

    /**
     * Test of getnPrenotazioniordinateMaestro method, of class Circolo.
     */
    @Test
    public void testGetnPrenotazioniordinateMaestro() throws Exception 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("Angelo","Arrigoni");
        Maestro maestro2=new Maestro("Gianni","Ziliani");
        Prenotazione p1=new Prenotazione(1,"nome","cognome", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2,"nome","cognome", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3,"nome","cognome", 26, 12, 2021, 8, 0, maestro2);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        
        Maestro[] atteso={maestro1,maestro2};
        Maestro[] attuale=c1.getnPrenotazioniordinateMaestro();
        
        assertArrayEquals("get  maestri ordinati ",atteso,attuale);
        
        Circolo c2=new Circolo();
        
        c2.aggiungiPrenotazione(p3);
        c2.aggiungiPrenotazione(p1);
        c2.aggiungiPrenotazione(p2);
        
        attuale=c2.getnPrenotazioniordinateMaestro();
        
        assertArrayEquals("get  maestri disordinati ",atteso,attuale);
        
    }
    
    @Test(expected=EccezioneNessunMaestroPresente.class)
    public void testGetnPrenotazioniordinateMaestroNessunPresente() throws Exception 
    {
        Circolo c1=new Circolo();
        
        
        Maestro[] atteso={};
        Maestro[] attuale=c1.getnPrenotazioniordinateMaestro();
        
        assertArrayEquals("get  maestri ordinati ",atteso,attuale);
        
        
    }

    /**
     * Test of getPrenotazioniMaestro method, of class Circolo.
     */
    @Test
    public void testGetPrenotazioniMaestro() throws Exception 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("Angelo","Arrigoni");
        Maestro maestro2=new Maestro("Gianni","Ziliani");
        Prenotazione p1=new Prenotazione(1,"nome","cognome", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2,"nome","cognome", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3,"nome","cognome", 26, 12, 2021, 8, 0, maestro2);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        
        Prenotazione[] atteso={p1,p2};
        Prenotazione[] attuale=c1.getPrenotazioniMaestro("Angelo","Arrigoni");
        
        assertArrayEquals("get prenotazione maestro dopo aggiunta di 3 prenotazioni",atteso, attuale);
    }
    @Test(expected=EccezioneNonPresente.class)
    public void testGetPrenotazioniMaestroNonPresente() throws Exception 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("Angelo","Arrigoni");
        Maestro maestro2=new Maestro("Gianni","Ziliani");
        Prenotazione p1=new Prenotazione(1,"nome","cognome", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2,"nome","cognome", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3,"nome","cognome", 26, 12, 2021, 8, 0, maestro2);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        
        Prenotazione[] atteso={p1,p2};
        Prenotazione[] attuale=c1.getPrenotazioniMaestro("nome","cognome");
        
        assertArrayEquals("get prenotazione maestro dopo aggiunta di 3 prenotazioni",atteso, attuale);
    }


    /**
     * Test of esportaPrenotazioniCSV method, of class Circolo.
     */
    @Test
    public void testEsportaPrenotazioniCSV() throws Exception 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Angelo", "Arrigoni", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2, "Angelo", "Arrigoni", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3, "Gianni", "Ziliani", 26, 12, 2021, 8, 0, maestro1);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        
        c1.salvaCircolo("circoloTest.txt");
    }

    /**
     * Test of salvaCircolo method, of class Circolo.
     */
    @Test
    public void testSalvaCircolo() throws Exception 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Angelo", "Arrigoni", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2, "Angelo", "Arrigoni", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3, "Gianni", "Ziliani", 26, 12, 2021, 8, 0, maestro1);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        
        c1.salvaCircolo("circoloTest2.bin");
    }

    /**
     * Test of caricaCircolo method, of class Circolo.
     */
    @Test
    public void testCaricaCircolo() throws Exception 
    {
        Circolo c1=new Circolo();
        
        Maestro maestro1=new Maestro("","");
        Prenotazione p1=new Prenotazione(1, "Angelo", "Arrigoni", 24, 12, 2021, 8, 0, maestro1);
        Prenotazione p2=new Prenotazione(2, "Angelo", "Arrigoni", 25, 12, 2021, 8, 0, maestro1);
        Prenotazione p3=new Prenotazione(3, "Gianni", "Ziliani", 26, 12, 2021, 8, 0, maestro1);
        
        c1.aggiungiPrenotazione(p1);
        c1.aggiungiPrenotazione(p2);
        c1.aggiungiPrenotazione(p3);
        
        c1.salvaCircolo("circoloTest2.bin");
        
        Circolo atteso,attuale;
        atteso=c1;
        attuale=c1.caricaCircolo("circoloTest2.bin");
        assertEquals("Carica i dati da file ",atteso, attuale);
    }
    
}
