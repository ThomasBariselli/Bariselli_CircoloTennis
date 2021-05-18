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
public class EccezioneClienteNonPresente extends Exception
{

    private String nome;
    private String cognome;

    public EccezioneClienteNonPresente(String nome,String cognome)
    {
        this.nome=nome;
        this.cognome=cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String toString()
    {
        String s="";
        s+="Il cliente "+getNome()+" "+getCognome()+" non ha prenotato nessuna lezione";
        return s;
    }
    
}
