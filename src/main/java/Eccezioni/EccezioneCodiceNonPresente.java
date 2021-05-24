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
public class EccezioneCodiceNonPresente extends Exception
{
    private int codice;

    public EccezioneCodiceNonPresente(int codice)
    {
        this.codice=codice;
    }

    public int getCodice() 
    {
        return codice;
    }
    
    public String toString()
    {
        String s="";
        s+="La prenotazione di codice identificativo "+getCodice()+" non è presente o è vuota";
        return s;
    }
    
}
