/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import Eccezioni.EccezioneNonPresente;
import Eccezioni.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
          if(prenotazioni[i].getDataOraLezione().compareTo(p.getDataOraLezione())==0)
            throw new EccezioneDataNonValida("Data occupata,reinserire la prenotazione con una data valida");
          
        }
        if(LocalDateTime.now().compareTo(p.getDataOraLezione())>0 )
            throw new EccezioneDataNonValida("Data gia' passata,reinserire la prenotazione con una data valida");
        else
        {
            prenotazioni[nPrenotazioniPresenti]=new Prenotazione(p);
            nPrenotazioniPresenti++;
        }
            
    }
    
    public void rimuoviPrenotazione(int codice) throws EccezioneCodiceNonPresente
    {
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            if(prenotazioni[i].getCodice()==codice) 
            {
                prenotazioni[i]=null;
                aggiornaPosizione(i);
                nPrenotazioniPresenti--;
                return;
            }
        } 
        throw new EccezioneCodiceNonPresente(codice);
    }
    private void aggiornaPosizione (int posizione)
    {
	for (int i=posizione;i<getnPrenotazioniPresenti();i++)
            prenotazioni[i]=prenotazioni[i+1];
    }
    
    public Prenotazione[] getPrenotazioniCliente(String nome,String cognome) throws EccezioneNonPresente
    {
        int c=0;
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            if((prenotazioni[i].getNome().compareToIgnoreCase(nome))==0 && (prenotazioni[i].getCognome().compareToIgnoreCase(cognome))==0)
                c++;
        }
        Prenotazione[] prenotazioniCliente=new Prenotazione[c];
        int i=0;
        for(int j=0;j<getnPrenotazioniPresenti();j++)
        {
            if((prenotazioni[j].getNome().compareToIgnoreCase(nome))==0 && (prenotazioni[j].getCognome().compareToIgnoreCase(cognome))==0)
            {
                prenotazioniCliente[i]=new Prenotazione(prenotazioni[j]);
                i++;
                
            }
        }
          
        if(c>0)
           return prenotazioniCliente;
        else
            throw new EccezioneNonPresente(nome,cognome,"cliente");
    }
    
    public Prenotazione[] getPrenotazioniMaestro(String nome,String cognome) throws EccezioneNonPresente
    {
        int c=0;
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            if((prenotazioni[i].getMaestro().getNome().compareToIgnoreCase(nome))==0 && (prenotazioni[i].getMaestro().getCognome().compareToIgnoreCase(cognome))==0)
                c++;
        }
        Prenotazione[] prenotazioniCliente=new Prenotazione[c];
        int i=0;
        for(int j=0;j<c;j++)
        {
                if((prenotazioni[j].getMaestro().getNome().compareToIgnoreCase(nome))==0 && (prenotazioni[j].getMaestro().getCognome().compareToIgnoreCase(cognome))==0)
                {
                    prenotazioniCliente[i]=new Prenotazione(prenotazioni[j]);
                    i++;
                }
        }
        if(c>0)
           return prenotazioniCliente;
        else
            throw new EccezioneNonPresente(nome,cognome,"maestro");
    }
    
    public void salvacircolo(String nomeFile) throws IOException
  {   
      FileOutputStream f1=new FileOutputStream(nomeFile);
      ObjectOutputStream writer=new ObjectOutputStream(f1);
      writer.writeObject(this);
      writer.flush();
      writer.close();   
  }
  
  public Circolo caricaCircolo(String nomeFile) throws IOException, FileException
  {
      Circolo c;
      FileInputStream f1=new FileInputStream(nomeFile);
      ObjectInputStream reader=new ObjectInputStream(f1);
      
       try 
       {
           c=(Circolo)reader.readObject();
           reader.close();
           return c;
       } 
       catch (ClassNotFoundException ex) 
       {
           reader.close();
           throw new FileException("Errore di lettura");
       }   
  }
    
    
}

