/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;



import Eccezioni.EccezionePosizioneNonValida;
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
 *Classe che rappresenta un circolo di tennis , i suoi attributi sono:
 * prenotazioni(array di oggetti di tipo Prenotazione)
 * maestri(array di oggetti di tipo Maestro)
 * nMaestriPresenti(intero che rappresenta l numero di maestri presenti
 * nPrenotazioniPresenti(intero che rappresenta il numero di prenotazioni presenti)
 * NUM_MAXPRENOTAZIONI(numero di massimo di prenotazioni inseribili)
 * @author Thomas Bariselli
 */
public class Circolo implements Serializable
{
    private Prenotazione[] prenotazioni;
    private Maestro[] maestri;
    private int nMaestriPresenti;
    private static int NUM_MAX_PRENOTAZIONI=2928;//366 giorni*8 ore giornaliere
    private int nPrenotazioniPresenti; 
    /**
     * Costruttore della classe circolo,permette di istanziare un nuovo circolo
     * nel costruttore vengono inzializzati gli array prenotazione e maestri e 
     * i due attributi nPrenotazioniPresenti e nmaestriPresnti
     */
    public Circolo()
    {
        prenotazioni=new Prenotazione[NUM_MAX_PRENOTAZIONI];
        maestri=new Maestro[NUM_MAX_PRENOTAZIONI];
        nPrenotazioniPresenti=0;
        nMaestriPresenti=0;
    }
    /**
     * /**
     * Costruttore di copia della classe Circolo. Consente di
    * istanziare una nuova prenotazione
    * @param c circolo passato come parametro da cui verrà istanziato il nuovo circolo
    * Il circolo istanziato sarà una copia del circolo c
    */
    public Circolo (Circolo c) 
    {
        prenotazioni=new Prenotazione[NUM_MAX_PRENOTAZIONI];
        maestri=new Maestro[NUM_MAX_PRENOTAZIONI];
        int cont=0;
        
        
        for (int i=0;i<c.getnPrenotazioniPresenti();i++)
        {
            for(int j=0;j<c.getnPrenotazioniPresenti();j++)
            {
                if(maestri[j]!=null)
                {
                    if(maestri[j].getNome().compareToIgnoreCase(c.getMaestro(i).getNome())==0 && maestri[j].getCognome().compareToIgnoreCase(c.getMaestro(i).getCognome())==0)
                    cont++;
                }
                
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
    /**
     * Metodo che permette di assegnare un valore a codice tramite la posizione 
     * nell'array della prenotazione n e il codice da assegnare
     * @param n posizione della prenotazione nell'array prenotazioni
     * @param codice codice da assegnare
     */
    public void setCodicePrenotazione(int n,int codice)
    {
        prenotazioni[n].setCodice(codice);
    }
    /**
     * Metodo che restituisce il valore di nPrenoationiPresnti
     * @return nPrenotazioniPresenti numero di prenotazioni presenti nell'array prenotazioni
     */
    public int getnPrenotazioniPresenti() 
    {
        return nPrenotazioniPresenti;
    }
    /**
     * Metodo che restituisce il valore di NUM_MAX_PRENOTAZIONI
     * @return NUM_MAX_PRENOTAZIONI valore massimo di prenotazioni inseribili
     */
    public static int getNUM_MAX_PRENOTAZIONI() 
    {
        return NUM_MAX_PRENOTAZIONI;
    }
    /**
     * Metodo che restituisce una copia della prenotazione in posizione n nell'array prenotazioni
     * @param n posizione della prenotazione da restituire
     * @return prenotazione copia della prenotazione in posizione n
     */
    public Prenotazione getPrenotazione(int n) 
    {
        try
        {
            if(n<0 || n>=NUM_MAX_PRENOTAZIONI)
                throw new EccezionePosizioneNonValida(n);
            return new Prenotazione(prenotazioni[n]);
            
        }
        catch(NullPointerException | EccezionePosizioneNonValida e1)
        {
            return null;
        }
    }
    /**
     * Metodo che restituisce una copia del maestro in posizione n nell'array maestri
     * @param n posizione del maestro da restituire nell'array maestri
     * @return maestro copia del maestro da restituire
     */
    public Maestro getMaestro(int n)
    {
        try
        {
            if(n<0 || n>=NUM_MAX_PRENOTAZIONI)
                throw new EccezionePosizioneNonValida(n);
            return new Maestro(maestri[n]);
        }
        catch(NullPointerException | EccezionePosizioneNonValida e1)
        {
            return null;
        }
    }
    /**
     * Metodo che aggiunge una prenotazione p passata per parametro nella prima posizione libera dell'array prenotazioni
     * @param p prenotazione da aggiungere
     * @throws EccezioneDataNonValida eccezione che viene sollevata nel momento in cui 
     * la data in cui si cerca di aggiungere la nuova prenotazione è 
     * occupata,già passata oppure l'orario non è compreso nell'orario lavorativo(8-11;14-17)
     */
    public void aggiungiPrenotazione(Prenotazione p) throws EccezioneDataNonValida
    {    
        int cont=0;
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            if(prenotazioni[i].getDataOraLezione().compareTo(p.getDataOraLezione())==0)
                throw new EccezioneDataNonValida("Data occupata,reinserire la prenotazione con una data valida");
        }
        if(p.getDataOraLezione().getHour()<8 || p.getDataOraLezione().getHour()>17 || (p.getDataOraLezione().getHour()>11 && p.getDataOraLezione().getHour()<14))
                    throw new EccezioneDataNonValida("Ora non valida,reinserire la prenotazione con una data valida");
        if(LocalDateTime.now().compareTo(p.getDataOraLezione())>0 )
            throw new EccezioneDataNonValida("Data gia' passata,reinserire la prenotazione con una data valida");
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
    /**
     * Metodo che rimuove la prenotazione di codice uguale a codice passato per parametro
     * @param codice codice tramite il quale viene rimossa la prenotazione di codice uguale
     * @throws EccezioneCodiceNonPresente eccezione sollevata nel momento in cui il codice passato per parametro non corrisponda 
     * a nessun codice di nessuna prenotazione presente
     */
    public void rimuoviPrenotazione(int codice) throws EccezioneCodiceNonPresente
    {
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            if(prenotazioni[i]!=null)
            {
                if(prenotazioni[i].getCodice()==codice) 
                {
                    prenotazioni[i]=null;
                    aggiornaPosizione(i);
                    nPrenotazioniPresenti--;
                    return;
                }
            }
                    
            
        } 
        throw new EccezioneCodiceNonPresente(codice);
    }
    /**
     * Metodo privato invocato da rimuoviPrenotazione necessario per aggiornare la posizione degli oggetti 
     * in modo da non lasciare "spazi vuoti" nell'array
     * @param posizione posizione da cui è stata rimossa la prenotazione e che deve essere aggiornata
     */
    private void aggiornaPosizione (int posizione)
    {
	for (int i=posizione;i<getnPrenotazioniPresenti();i++)
            prenotazioni[i]=prenotazioni[i+1];
    }
    /**
     * Metodo che restituisce un array di Prenotazioni dello stesso cliente il cui nome e cognome sono passati come parametro
     * @param nome nome da confrontare per trovare le prenotazioni del cliente 
     * @param cognome cognome da confrontare per trovare le prenotazioni del cliente
     * @return prenotazioniCliente array di prenotazioni di uno stesso cliente
     * @throws EccezioneNonPresente eccezione che viene sollevata nel momento in cui non vi è 
     * nessuna prenotazione riferita al nome e cognome passati come parametro
     */
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
            }
            i++;
        }
          
        if(c>0)
           return prenotazioniCliente;
        else
            throw new EccezioneNonPresente(nome,cognome,"cliente");
    }
    /**
     * Metodo che restituisce l' array maestri ordinato e che conta le prenotazioni di un determinato maestro 
     * @return maestriContatoOrdinato copia dell' array maestri della classe circolo ordinato
     * @throws EccezioneNessunMaestroPresente ecccezione che viene sollevata nel caso 
     * non ci sia nessun maestro presente nell'array maestri
     */
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
    /**
     * Metodo che restituisce un array di Prenotazioni tenute dallo stesso maestro il cui nome e cognome sono passati come parametro
     * @param nome nome del maestro da confrontare per trovare le lezioni tenute dal maestro
     * @param cognome cognome del maestro da confrontare per trovare le lezioni tenute dal maestro
     * @return prenotazioniMaestro array di prenotazioni tenute dal maestro 
     * il cui nome e cognome è passato come parametro
     * @throws EccezioneNonPresente eccezione sollevata nel momento in cui non vi è nessuna prenotazione tenuta dal maestro
     * il cui nome e cognome sono passati come parametro
     */
    public Prenotazione[] getPrenotazioniMaestro(String nome,String cognome) throws EccezioneNonPresente
    {
        int c=0;
        for(int i=0;i<getnPrenotazioniPresenti();i++)
        {
            if((prenotazioni[i].getMaestro().getNome().compareToIgnoreCase(nome))==0 && (prenotazioni[i].getMaestro().getCognome().compareToIgnoreCase(cognome))==0)
                c++;
        }
        Prenotazione[] prenotazioniMaestro=new Prenotazione[c];
        int i=0;
        for(int j=0;j<c;j++)
        {
                if((prenotazioni[j].getMaestro().getNome().compareToIgnoreCase(nome))==0 && (prenotazioni[j].getMaestro().getCognome().compareToIgnoreCase(cognome))==0)
                {
                    prenotazioniMaestro[i]=new Prenotazione(prenotazioni[j]);
                }
                i++;
        }
        if(c>0)
           return prenotazioniMaestro;
        else
            throw new EccezioneNonPresente(nome,cognome,"maestro");
    }
    /**
     * Metodo che restituisce una Stringa "contenente" tutti i valori degli attributi
     * @return s Stringa restituita
     */
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
    /**
     * Metodo che esporta i dati di circolo in file csv di nome nomeFile passato per parametro
     * @param nomeFile stringa contenente il nome del file su cui esporatre i dati
     * @throws IOException eccezione sollevata per errori di input/output per esempio quando non viene trovato il file da cui leggere i dati
     * @throws FileException eccezione che viene sollevata nella classe TextFile quando si cerca di accedere ad un file in lettura
     * anche se quest'ultimo è stato aperto in scrittura o viceversa oppure quando è alla fine del file
     */
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
    /**
     * Metodo che permette la serializzazione dei dati del circolo su un file binario di nome nomeFile passato come parametro
     * @param nomeFile nome del file binario su cui vengono salvati i dati
     * @throws IOException  eccezione che si solleva per errori di input/output per esmpio quando non viene trovato il file in lettura dei dati
     */
    public void salvaCircolo(String nomeFile) throws IOException
    {   
      FileOutputStream f1=new FileOutputStream(nomeFile);
      ObjectOutputStream writer=new ObjectOutputStream(f1);
      writer.writeObject(this);
      writer.flush();
      writer.close();   
    }
  /**
   * Metodo che carica i dati dal file binario di nome nomeFile e li assegna ad un circolo istanziato nel main
   * @param nomeFile
   * @return
   * @throws IOException
   * @throws FileException 
   */
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
    /**
     * Metodo che riestiruisce true se l'oggetto passato è uguale 
     * all'oggetto su cui è invocato
     * @param o oggetto passato come parametro
     * @return bool true se uguali, false se diversi
     */
    public boolean equals(Object o)
    {
        Circolo c=(Circolo)o;
        Prenotazione p;
        
        for(int i=0;i<getNUM_MAX_PRENOTAZIONI();i++)
        {
            if(prenotazioni[i]==null)
            {
                if(prenotazioni[i]!=null)
                    return false;
            }
            else
            {
                p=prenotazioni[i];
                if(p==null || prenotazioni[i].equals(p)==false)
                    return false;
            }
        }
        return true;
    }
    
    
}

