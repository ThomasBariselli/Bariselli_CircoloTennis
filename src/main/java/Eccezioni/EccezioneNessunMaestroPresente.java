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
public class EccezioneNessunMaestroPresente extends Exception 
{
   String motivoEccezione;
    public EccezioneNessunMaestroPresente(String message) 
    {
       motivoEccezione=message;
    }
    
    public String toString()
    {
        return motivoEccezione;
    } 
}
