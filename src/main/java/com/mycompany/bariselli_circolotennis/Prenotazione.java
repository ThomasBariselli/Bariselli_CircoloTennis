/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 *La classe Prenotazione rappresenta la prenotazione di una lezione di tennis, i suoi attributi sono:
 * codice(codice identificativo univoco per ogni prenotazione assegnato automaticamente dal software)<br>
 * nome(nome della persona a cui è riferita la prenotazione)<br>
 * cognome(cognome della persona che a cui è riferita la prenotazione)<br>
 * dataOraLezione(data e ora a cui si effettuerà la lezione)<br>
 * maestro(maestro che terrà la lezione anch'esso identificato con una classe Maestro)<br>
 * @author Thomas Bariselli
 */

//Si chiede che il software memorizzi le prenotazioni per le ore di lezione. I dati da memorizzare per ogni prenotazione sono: codice progressivo numerico, cognome e nome del cliente che prenota la lezione, data e ora prevista per la lezione (ogni lezione dura un’ora), cognome e nome del maestro che terrà la lezione.
public class Prenotazione implements Serializable
{
    private int codice;
    private String nome;
    private String cognome;
    private LocalDateTime dataOraLezione;
    private Maestro maestro;
/**
 * Costruttore della classe Prenotazione. Consente di istanziare una nuova prenotazione
 * @param codice codice identificativo
 * @param nome nome della persona che ha prenotato
 * @param cognome cognome della persona che ha prenotato
 * @param giorno giorno in cui si effettuerà la lezione
 * @param mese  mese in cui si effettuerà la lezione
 * @param anno anno in cui si effettuerà la lezione
 * @param ora ora in cui si effettuerà la lezione
 * @param minuto minuto in cui si effettuerà la lezione(automaticamente messo a 0)
 * @param maestro maestro che effetturà la lezione
 */
    public Prenotazione(int codice, String nome, String cognome,int giorno,int mese,int anno,int ora,int minuto, Maestro maestro) 
    {
        this.codice = codice;
        this.nome = nome;
        this.cognome = cognome;
        dataOraLezione= LocalDateTime.of(anno,mese,giorno,ora,minuto);
        this.maestro = new Maestro(maestro);
    }
    /**
     * Costruttore di copia della classe Prenotazione. Consente di
    * istanziare una nuova prenotazione
    * @param p: prenotazione da cui verrà istanziata la nuova prenotazione.
    * La prenotazione istanziata sarà una copia della prenotazione p
     */
    public Prenotazione(Prenotazione p) 
    {
        this.codice = p.codice;
        this.nome = p.nome;
        this.cognome = p.cognome;
        this.dataOraLezione= LocalDateTime.from(p.getDataOraLezione());
        this.maestro = new Maestro(p.getMaestro());
    }
/**
 * Metodo che restituisce il codice identificativo della prenotazione
 * @return codice codice identificativo restituito
 */
    public int getCodice() 
    {
        return codice;
    }
/**
 * Metodo che permette di assegnare il codice identificativo della prenotazione
 * @param codice intero da assegnare al codice della prenotazione
 */
    public void setCodice(int codice) 
    {
        this.codice = codice;
    }
/**
 * Metodo che restituisce il nome a cui è riferita la prenotazione
 * @return nome nome della prenotazione restituito
 */
    public String getNome() 
    {
        return nome;
    }
/**
 * Metodo che permette di assegnare il nome a cui è riferita la prenotazione
 * @param nome stringa che viene assegnata all'attributo nome della prenotazione
 */
    public void setNome(String nome) 
    {
        this.nome = nome;
    }
/**
 * Metodo che restituisce il cognome a cui è riferita la prenotazione 
 * @return  cognome cognome della prenotazione restituito 
 */
    public String getCognome() 
    {
        return cognome;
    }
/**
 * Metodo che permette di assegnare il cognome a cui è riferita la prenotazione 
 * @param cognome striga che viene assegnata all'attributo cognome della prenotazione
 */
    public void setCognome(String cognome) 
    {
        this.cognome = cognome;
    }
/**
 * Metodo che restituisce La data e l'ora della lezione prenotata in formato LocalDatetime
 * @return dataoraLezione copia della data e ora della lezione prenotata che viene restituita dal metodo
 */
    public LocalDateTime getDataOraLezione() 
    {
        return LocalDateTime.from(dataOraLezione);
    }
/**
 * Metodo che permette di asssegnare il cognome a cui è riferita la prenotazione 
 * @param dataOraLezione data e ora della lezione che verranno assegnate all'attributo dataOraLezione della prenotazione
 */
    public void setDataOraLezione(LocalDateTime dataOraLezione) 
    {
        this.dataOraLezione = dataOraLezione;
    }
/**
 * Metodo che restituisce un oggetto di tipo Maestro "contenente"
 * nome e cognome del Maestro che terrà questa lezione
 * @return maestro copia dell'attributo maestro
 */
    public Maestro getMaestro() 
    {
        return new Maestro(maestro);
    }
/**
 * Metodo che permette di assegnare un oggetto di tipo Maestro "contenente" 
 * nome e cognome del Maestro che terrà questa lezione all'attributo maestro della prenotazione
 * @param m oggetto di tipo Maestro che verrà assegnato all'attributo maestro
 */
    public void setMaestro(Maestro m) 
    {
        this.maestro.setCognome(m.getCognome());
        this.maestro.setNome(m.getNome());
    }
/**
 * Metodo che restituisce una Stringa "contenente" tutti i valori degli attributi
 * @return s Stringa restituita
 */
    public String toString() 
    {
        return "Codice: " + getCodice() + "\nNome-->" + getNome() + "\nCognome-->" + getCognome() + "\nData lezione-->" + dataOraLezione.getDayOfMonth() + "/" + dataOraLezione.getMonthValue()+"/"+dataOraLezione.getYear()+"\nOra lezione-->"+dataOraLezione.getHour()+":00"+"\nNome Maestro-->"+getMaestro().getNome()+"\nCognome Maestro-->"+getMaestro().getCognome();
    }
    /**
     * Metodo che riestiruisce true se l'oggetto passato è uguale 
     * all'oggetto su cui è invocato
     * @param o oggetto passato come parametro
     * @return bool true se uguali, false se diversi
     */
    public boolean equals(Object o)
    {
        Prenotazione p;
        p=(Prenotazione)o;
        if(this.getCodice()==p.getCodice() && this.getNome().compareToIgnoreCase(p.getNome())==0 && this.getCognome().compareToIgnoreCase(p.getCognome())==0 && this.dataOraLezione.compareTo(p.getDataOraLezione())==0 && this.maestro.getNome().compareToIgnoreCase(p.getMaestro().getNome())==0 && this.getMaestro().getCognome().compareToIgnoreCase(p.getMaestro().getCognome())==0)
            return true;
        else
            return false;
    }
}


