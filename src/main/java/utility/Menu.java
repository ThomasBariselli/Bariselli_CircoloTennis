/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Menu 
{
    private int numeroVoci;
    private String[] elencoVoci;
    
    public Menu(String[] elencoVoci)
    {
        numeroVoci=elencoVoci.length;
        this.elencoVoci=new String[numeroVoci];
        for(int i=0;i<numeroVoci;i++)
            this.elencoVoci[i]=elencoVoci[i];
    }
    
    public void visualizzaMenu()
    {
        System.out.println("MENU:");
        for(int i=0;i<numeroVoci;i++)
        {
            System.out.println(i+"-->"+elencoVoci[i]);
        }
    }
    
    public int sceltaMenu()
    {
        Scanner tastiera=new Scanner(System.in);
        String inputUtente;
        int sceltaUtente=0;
        boolean inputOk=true;
        
        do
        {
            inputOk=true;
            visualizzaMenu();
            System.out.println("Scelta-->");
            inputUtente=tastiera.nextLine();

            for(int i=0;i<inputUtente.length();i++)
            {
                if(inputUtente.charAt(i)>='0' && inputUtente.charAt(i)<='9')
                    i++;
                else
                {
                    inputOk=false;
                    break;
                }

            }

            if(inputOk)
            {
                sceltaUtente=Integer.parseInt(inputUtente);
                if(sceltaUtente<0 || sceltaUtente>numeroVoci-1)
                    inputOk=false;
            }

            if(!inputOk)
            {
                System.out.println("il valore inserito non è corretto. Premi invio per reinserire la scelta");
                tastiera.nextLine();
            }
            
        }while(!inputOk);
        
        return sceltaUtente;
    }
}
