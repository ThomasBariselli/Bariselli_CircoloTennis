/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author Thomas
 */
//Si chiede che il software memorizzi le prenotazioni per le ore di lezione. I dati da memorizzare per ogni prenotazione sono: codice progressivo numerico, cognome e nome del cliente che prenota la lezione, data e ora prevista per la lezione (ogni lezione dura un’ora), cognome e nome del maestro che terrà la lezione.
public class Prenotazione 
{
    private int codice;
    private String nome;
    private String cognome;
    private LocalDateTime dataOraLezione;
    private Maestro maestro;

    public Prenotazione(int codice, String nome, String cognome,int giorno,int mese,int minuto,int anno,int ora, Maestro maestro) 
    {
        this.codice = 0;
        this.nome = nome;
        this.cognome = cognome;
        dataOraLezione= LocalDateTime.of(anno,mese,giorno,ora,minuto);
        this.maestro = new Maestro(maestro);
    }
    
    public Prenotazione(Prenotazione p) 
    {
        this.codice = p.codice;
        this.nome = p.nome;
        this.cognome = p.cognome;
        this.dataOraLezione = LocalDateTime.from(dataOraLezione);
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
        return dataOraLezione;
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
        return "Prenotazione{" + "codice=" + codice + ", nome=" + nome + ", cognome=" + cognome + ", dataOraLezione=" + dataOraLezione + ", maestro=" + maestro + '}';
    }
    
    
}


