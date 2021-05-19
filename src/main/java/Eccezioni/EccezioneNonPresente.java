/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eccezioni;

/**
 *
 * @author user
 */
public class EccezioneNonPresente extends Exception
{

    private String nome;
    private String cognome;
    private String utente;

    public EccezioneNonPresente(String nome,String cognome,String utente)
    {
        this.nome=nome;
        this.cognome=cognome;
        this.utente=utente;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getUtente() {
        return utente;
    }
    
    public String toString()
    {
        String s="";
        s+="Il "+getUtente()+" "+getNome()+" "+getCognome()+" non ha prenotato nessuna lezione";
        return s;
    }
    
}
