/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import Eccezioni.EccezioneDataNonValida;
import Eccezioni.PrenotazioneOccupataException;
import java.time.LocalDateTime;

/**
 *
 * @author thoma
 */
public class Circolo 
{
    private Prenotazione[] prenotazioni;
    private static int NUM_MAX_PRENOTAZIONI=2928;//366 giorni*8 ore giornaliere
    private int nPrenotazioniPresenti; 
    
    public Circolo()
    {
        prenotazioni=new Prenotazione[NUM_MAX_PRENOTAZIONI];
        nPrenotazioniPresenti=0;
    }
    
    public Circolo (Circolo c)
    {
        prenotazioni=new Prenotazione[NUM_MAX_PRENOTAZIONI];
        for (int i=0;i<c.getnPrenotazioniPresenti();i++)
        {
                prenotazioni[i]=c.getPrenotazione(i);
                nPrenotazioniPresenti++;    
        } 
    }

    public int getnPrenotazioniPresenti() 
    {
        return nPrenotazioniPresenti;
    }
    
    public static int getNUM_MAX_PRENOTAZIONI() 
    {
        return NUM_MAX_PRENOTAZIONI;
    }
    
    public Prenotazione getPrenotazione(int n)
    {
        try
        {
            return new Prenotazione(prenotazioni[n]);
        }
        catch(NullPointerException e1)
        {
            return null;
        }
    }
    
    public void aggiungiPrenotazione(Prenotazione p) throws EccezioneDataNonValida
    {       
        if(prenotazioni[nPrenotazioniPresenti].getDataOraLezione().compareTo(p.getDataOraLezione())==0)
            throw new EccezioneDataNonValida("Data occupata,reinserire la prenotazione con una data valida");
        else if(LocalDateTime.now().compareTo(p.getDataOraLezione())>0 )
            throw new EccezioneDataNonValida("Data gia' passata,reinserire la prenotazione con una data valida");
        else
        {
            prenotazioni[nPrenotazioniPresenti]=new Prenotazione(p);
            nPrenotazioniPresenti++;
        }
            
    }
    
    /*public Circolo getPrenotazione(int posizione)
    {
        try
        {
            return new Libro(volumi[posizione]);  
        }
        catch(NullPointerException posizioneVuota)
        {
            return null;
        }
    }*/
    
 
    
}
