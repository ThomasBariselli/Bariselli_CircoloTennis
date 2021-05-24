package Eccezioni;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author user
 */
public class EccezionePosizioneNonValida extends Exception
{
    private int posizione;
    public EccezionePosizioneNonValida(int n) 
    {
        posizione=n;
    }


    public int getPosizione() 
    {
        return posizione;
    }
    public String toString()
    {
        return "La posizione "+getPosizione()+" non e' valida,reinserirla";
    }
    
}
