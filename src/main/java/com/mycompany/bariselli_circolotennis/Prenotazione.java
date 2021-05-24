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
 * @author Thomas
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
     * Costruttore di copia della classe Prenotazione, consente di istanziare una nuova prenotazione
     * @param p 
     */
    public Prenotazione(Prenotazione p) 
    {
        this.codice = p.codice;
        this.nome = p.nome;
        this.cognome = p.cognome;
        this.dataOraLezione= LocalDateTime.from(p.getDataOraLezione());
        this.maestro = new Maestro(p.getMaestro());
    }

    public int getCodice() 
    {
        return codice;
    }

    public void setCodice(int codice) 
    {
        this.codice = codice;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getCognome() 
    {
        return cognome;
    }

    public void setCognome(String cognome) 
    {
        this.cognome = cognome;
    }

    public LocalDateTime getDataOraLezione() 
    {
        return LocalDateTime.from(dataOraLezione);
    }

    public void setDataOraLezione(LocalDateTime dataOraLezione) 
    {
        this.dataOraLezione = dataOraLezione;
    }

    public Maestro getMaestro() 
    {
        return new Maestro(maestro);
    }

    public void setMaestro(Maestro m) 
    {
        this.maestro.setCognome(m.getCognome());
        this.maestro.setNome(m.getNome());
    }

    public String toString() 
    {
        return "Codice: " + getCodice() + "\nNome-->" + getNome() + "\nCognome-->" + getCognome() + "\nData lezione-->" + dataOraLezione.getDayOfMonth() + "/" + dataOraLezione.getMonthValue()+"/"+dataOraLezione.getYear()+"\nOra lezione-->"+dataOraLezione.getHour()+":00";
    }
    
    public boolean equals(Object o)
    {
        Prenotazione p;
        p=(Prenotazione)o;
        if(this.getCodice()==p.getCodice() && this.getNome().compareToIgnoreCase(p.getNome())==0 && this.getCognome().compareToIgnoreCase(p.getCognome())==0 && this.dataOraLezione.compareTo(p.getDataOraLezione())==0 && this.maestro.getNome().compareToIgnoreCase(p.maestro.getNome())==0 && this.maestro.getNome().compareToIgnoreCase(p.maestro.getCognome())==0)
            return true;
        else
            return false;
    }
}


