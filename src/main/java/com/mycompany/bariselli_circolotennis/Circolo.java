/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

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
    
    public int aggiungiPrenotazione(Prenotazione p)
    {
        /*if (posizione<0 || posizione>=getNumMaxVolumi())
            return -1;                  //posizione non valida
        */
        try
        {
            if (volumi[posizione]!=null)
                return -2;                  //posizione occupata
            prenotazioni[nPrenotazioniPresenti]=new Prenotazione(p);
            return posizione;
        }
        catch (ArrayIndexOutOfBoundsException posizioneNonValida)
        {
            return -1;
        } 
    }
    
 
    
}
