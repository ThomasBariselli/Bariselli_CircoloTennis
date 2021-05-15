/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

/**
 *
 * @author Thomas
 */
public class Main 
{
    public static void main(String[] args) 
    {
        Maestro m1=new Maestro("Sespo","Mario");
        Prenotazione p1=new Prenotazione(0, "nome", "cognome", 15, 6, 2021, 13, 0, m1);
        System.out.println(p1.toString());
    }
}
