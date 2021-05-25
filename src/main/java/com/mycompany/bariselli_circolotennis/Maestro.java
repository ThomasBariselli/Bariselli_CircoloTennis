/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import java.io.Serializable;

/**
 *La Classe Maestro rappresenta un Maestro che terrà la lezione nella prenotazione in cui è attributo,i suoi attributi sono:
 * nome(nome del maestro)
 * cognome(cognome del maestro)
 * lezioniMaestro(intero che identifica il numero di lezioni di un determinato
 * maestro(attributo utilizzato solo in getnPrenotazioniMaestroOrdinate))
 * @author Thomas Bariselli
 */
public class Maestro implements Serializable
{
    private String nome;
    private String cognome;
    private int lezioniMaestro;
/**
 * Costruttore della classe Maestro. Consente di istanziare un nuovo maestro
 * @param nome nome che verrà assegnato all'attributo nome del maestro
 * @param cognome cognome che verrà assegnato all'attributo cognome del maestro 
 */
    public Maestro(String nome, String cognome) 
    {
        this.nome = nome;
        this.cognome = cognome;
        lezioniMaestro=0;
    }
/**
 * Metodo che restituisce il numero di lezioni tenute dal determinato maestro
 * @return lezioniMaestro numero lezioni maestro
 */
    public int getLezioniMaestro() {
        return lezioniMaestro;
    }
/**
 * Metodo che incrementa automaticamente l'attributo lezioniMaestro
 */
    public void incrementaLezioniMaestro() {
        lezioniMaestro++;
    }
    /**
     * Metodo che decrementa automaticamente l'attributo lezioniMaestro
     */
    public void decrementaLezioniMaestro() {
        lezioniMaestro--;
    }
    /**
     * Metodo che azzera automaticamente l'attributo lezioniMaestro
     */
    public void azzeraLezioniMaestro() {
        lezioniMaestro=0;
    }
    /**
     * Costruttore di copia della classe Maestro. Consente di
 * istanziare un Maestro libro
 * @param m: maestro da cui verrà istanziato il nuovo maestro.
 * Il maestro istanziato sarà una copia del maestro m
     * @param m 
     */
    public Maestro(Maestro m) 
    {
        nome = m.getNome();
        cognome = m.getCognome();
    }
/**
 * Metodo che restituisce il nome del maestro
 * @return nome nome del maestro restituito
 */
    public String getNome() 
    {
        return nome;
    }
/**
 * Metodo che permette di assegnare una stringa all'attributo nome del maestro
 * @param nome stringa che viene assegnata all'attributo
 */
    public void setNome(String nome) 
    {
        this.nome = nome;
    }
/**
 * Metodo che restituisce il cognome del maestro
 * @return cognome cognome del maestro restituito 
 */
    public String getCognome() 
    {
        return cognome;
    }
/**
 * Metodo che permette di assegnare una stringa all'attributo cognome del maestro
 * @param cognome stringa che verrà assegnata all'attributo cognome di maestro
 */
    public void setCognome(String cognome) 
    {
        this.cognome = cognome;
    }
/**
 * Metodo che restituisce una Stringa "contenente" tutti i valori degli attributi
 * @return s stringa restituita
 */
    public String toString() 
    {
        return " maestro: " + "nome=" + nome + ", cognome=" + cognome ;
    }
    /**
     * Metodo che riestiruisce true se l'oggetto passato è uguale 
     * all'oggetto su cui è invocato
     * @param o oggetto passato come parametro
     * @return bool true se uguali, false se diversi
     */
    public boolean equals(Object o)
    {
        Maestro m;
        m=(Maestro)o;
        if(this.getNome().compareToIgnoreCase(m.getNome())==0 && this.getCognome().compareToIgnoreCase(m.getCognome())==0 )
            return true;
        else
            return false;
    }
}
