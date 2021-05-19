/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import Eccezioni.EccezioneNonPresente;
import Eccezioni.EccezioneCodiceNonPresente;
import Eccezioni.EccezioneDataNonValida;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class Main 
{
    public static void main(String[] args) 
    {
        Maestro m1=new Maestro("Sespo","Mario");
        Circolo c1=new Circolo();
        Prenotazione p1=new Prenotazione(0, "nome", "cognome", 15, 6, 2021, 13, 0, m1);
        Prenotazione p2=new Prenotazione(1,"Angelo","Spannung",15,6,2021,14,0,m1);
        try
        {
            c1.aggiungiPrenotazione(p1);
            c1.aggiungiPrenotazione(p2);
        }
        catch(EccezioneDataNonValida e1)
        {
            System.out.println(e1.toString());
        }
        try
        {
            System.out.println("\nPrenotazioni cliente presenti prima dell'eliminazione:\n");
            Prenotazione[] prenotazioniCliente=c1.getPrenotazioniCliente("nome","cognome");
            for(int i=0;i<prenotazioniCliente.length;i++)
                System.out.println(prenotazioniCliente[i].toString()+"\n");
            System.out.println("\nPrenotazioni maestro presenti prima dell'eliminazione:\n");
            Prenotazione[] prenotazioniCliente1=c1.getPrenotazioniMaestro("Sespo","Mario");
            for(int i=0;i<prenotazioniCliente1.length;i++)
                System.out.println(prenotazioniCliente1[i].toString()+"\n");
        }
        catch(EccezioneNonPresente e1)
        {
            System.out.println(e1.toString());
        }
        try 
        {
            c1.rimuoviPrenotazione(0);
        } 
        catch (EccezioneCodiceNonPresente ex) 
        {
            System.out.println(ex.toString());
        }
        try
        {
            System.out.println("\nPrenotazioni cliente presenti dopo l'eliminazione:\n");
            Prenotazione[] prenotazioniCliente=c1.getPrenotazioniCliente("nome","cognome");
            for(int i=0;i<prenotazioniCliente.length;i++)
                System.out.println(prenotazioniCliente[i].toString()+"\n");
        }
        catch(EccezioneNonPresente e1)
        {
            System.out.println(e1.toString());
        }
        try
        {
            System.out.println("\nPrenotazioni maestro presenti dopo l'eliminazione:\n");
            Prenotazione[] prenotazioniCliente1=c1.getPrenotazioniMaestro("Sespo","Mario");
            for(int i=0;i<prenotazioniCliente1.length;i++)
                System.out.println(prenotazioniCliente1[i].toString()+"\n");
        }
        catch(EccezioneNonPresente e1)
        {
            System.out.println(e1.toString());
        }
            
    }
}
