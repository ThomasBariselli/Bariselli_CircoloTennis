/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import java.io.Serializable;

/**
 *
 * @author thoma
 */
public class Maestro implements Serializable
{
    private String nome;
    private String cognome;
    private int lezioniMaestro;

    public Maestro(String nome, String cognome) 
    {
        this.nome = nome;
        this.cognome = cognome;
        lezioniMaestro=0;
    }

    public int getLezioniMaestro() {
        return lezioniMaestro;
    }

    public void incrementaLezioniMaestro() {
        lezioniMaestro++;
    }
    
    public void decrementaLezioniMaestro() {
        lezioniMaestro--;
    }
    
    public Maestro(Maestro m) 
    {
        nome = m.getNome();
        cognome = m.getCognome();
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

    public String toString() 
    {
        return " maestro: " + "nome=" + nome + ", cognome=" + cognome ;
    }
    
    
}
