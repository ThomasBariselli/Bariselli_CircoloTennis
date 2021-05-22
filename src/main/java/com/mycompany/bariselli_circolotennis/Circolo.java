/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;



import Eccezioni.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import utility.Ordinatore;
import utility.TextFile;

/**
 *
 * @author thoma
 */
public class Circolo implements Serializable
{
    private Prenotazione[] prenotazioni;
    private Maestro[] maestri;
    private int nMaestriPresenti;
    private static int NUM_MAX_PRENOTAZIONI=2928;//366 giorni*8 ore giornaliere
    private int nPrenotazioniPresenti; 
    
    public Circolo()
    {
        prenotazioni=new Prenotazione[NUM_MAX_PRENOTAZIONI];
        maestri=new Maestro[NUM_MAX_PRENOTAZIONI];
        nPrenotazioniPresenti=0;
        nMaestriPresenti=0;
    }
    
    public Circolo (Circolo c)
    {
        prenotazioni=new Prenotazione[NUM_MAX_PRENOTAZIONI];
        maestri=new Maestro[NUM_MAX_PRENOTAZIONI];
        int cont=0;
        
        
        for (int i=0;i<c.getnPrenotazioniPresenti();i++)
        {
            for(int j=0;j<c.getnPrenotazioniPresenti();j++)
            {
                if(maestri[j].getNome().compareToIgnoreCase(c.getMaestro(i).getNome())==0 && maestri[j].getCognome().compareToIgnoreCase(c.getMaestro(i).getCognome())==0)
                    cont++;
            }
            prenotazioni[i]=c.getPrenotazione(i);
            nPrenotazioniPresenti++; 
            if(cont==0)
            {
                maestri[i]=new Maestro(c.getMaestro(i));
                nMaestriPresenti++;
            }
                
               
        } 
    }
    public void setCodicePrenotazione(int n,int codice)
    {
        prenotazioni[n].setCodice(codice);
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
    
    public Maestro getMaestro(int n)
    {
        try
        {
            return new Maestro(maestri[n]);
        }
        catch(NullPointerException e1)
        {
            return null;
        }
    }
    
    public void aggiungiPrenotazione(Prenotazione p) throws EccezioneDataNonValida
    {    
        int cont=0;
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
          if(prenotazioni[i].getDataOraLezione().compareTo(p.getDataOraLezione())==0)
            throw new EccezioneDataNonValida("Data occupata,reinserire la prenotazione con una data valida");
          else if(p.getDataOraLezione().getHour()<8 || p.getDataOraLezione().getHour()>17 || (p.getDataOraLezione().getHour()>11 && p.getDataOraLezione().getHour()<14))
            throw new EccezioneDataNonValida("Ora non valida,reinserire la prenotazione con una data valida");
          
          
        }
        if(LocalDateTime.now().compareTo(p.getDataOraLezione())>0 )
            throw new EccezioneDataNonValida("Data gia' passata,reinserire la prenotazione con una data valida");
        else
        {
            for(int i=0;i<getnPrenotazioniPresenti();i++)
            {
                if(maestri[i]!=null)
                {
                    if(maestri[i].getNome().compareToIgnoreCase(p.getMaestro().getNome())==0 && maestri[i].getCognome().compareToIgnoreCase(p.getMaestro().getCognome())==0)
                        cont++;
                }
            }
            if(cont==0)
            {
                maestri[nMaestriPresenti]=new Maestro(p.getMaestro());
                nMaestriPresenti++;
            }
              
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
    
    public Maestro[] getnPrenotazioniordinateMaestro() throws EccezioneNessunMaestroPresente
    {
        int c=0;
        for(int i=0;i<getnPrenotazioniPresenti();i++)
            if(maestri[i]!=null)
                maestri[i].azzeraLezioniMaestro();
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            if(maestri[i]!=null)
                c++;
            else
                break;
        }
        for(int i=0;i<c;i++)
        {
            for(int j=0;j<getnPrenotazioniPresenti();j++)
            {
                if((maestri[i].getNome().compareToIgnoreCase(prenotazioni[j].getMaestro().getNome()))==0 && (maestri[i].getCognome().compareToIgnoreCase(prenotazioni[j].getMaestro().getCognome()))==0)
                    maestri[i].incrementaLezioniMaestro();
            }
                
        }
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            if(maestri[i]!=null)
            {
                if(maestri[i].getLezioniMaestro()==0)
                {
                    maestri[i]=null;
                    aggiornaPosizione(i);
                } 
            }
            
        }
        Maestro[] maestriContatoOrdinato=new Maestro[c];
        maestriContatoOrdinato=Ordinatore.selectionSortMaestriAlfabetico(maestri,c);
        
        if(c>0)
           return maestriContatoOrdinato;
        else
            throw new EccezioneNessunMaestroPresente("Nessun Maestro presente");
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
    
    public String toString()
    {
        String s="";
        
        if(getnPrenotazioniPresenti()==0)
            s="Nessuna prenotazione presente";
        else
        {
            Prenotazione p;
            for(int i=0;i<getnPrenotazioniPresenti();i++)
            {
                if(getPrenotazione(i)!=null)
                {
                    p=getPrenotazione(i);
                    s+="\n"+p.toString()+"\n"; 
                } 
            }
        }
        return s;
        
    }
    
    public void esportaPrenotazioniCSV(String nomeFile) throws IOException, FileException
    {
        Prenotazione p;
        TextFile f1=new TextFile(nomeFile,'W');
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            p=getPrenotazione(i);
            if(p!=null)
            {
                f1.toFile(i+";"+p.getCodice()+";"+p.getNome()+";"+p.getCognome()+";"+p.getDataOraLezione().getDayOfMonth()+";"+p.getDataOraLezione().getMonthValue()+";"+p.getDataOraLezione().getYear()+";"+p.getDataOraLezione().getHour()+";"+p.getMaestro().getNome()+";"+p.getMaestro().getCognome()+";");
            }
        }
        f1.close();
    }
    
    public void salvaCircolo(String nomeFile) throws IOException
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

