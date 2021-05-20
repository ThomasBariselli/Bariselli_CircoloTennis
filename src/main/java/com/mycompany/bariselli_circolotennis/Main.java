/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import Eccezioni.EccezioneNonPresente;
import Eccezioni.EccezioneCodiceNonPresente;
import Eccezioni.EccezioneDataNonValida;
import Eccezioni.FileException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Menu;
import utility.Ordinatore;

/**
 *
 * @author Thomas
 */
public class Main 
{
    public static void main(String[] args) 
    {
        String[] vociMenu=new String[8];
        int sceltaUtente=-1;
        Scanner tastiera=new Scanner(System.in);
        Circolo c1=new Circolo();
        int esitoOperazione;
        String caricamentoDaFileOK;
        String nomeFileTesto="prenotazioniCircolo.txt";
        String nomeFileBinario="circolo.bin";
        

        vociMenu[0]="Esci";
        vociMenu[1]="Aggiungi prenotazione";
        vociMenu[2]="Rimuovi prenotazione";
        vociMenu[3]="Visualizzare i dati relativi a tutte le lezioni prenotate da un cliente";
        vociMenu[4]="Visualizzare tutte le lezioni prenotate per un determinato maestro";
        vociMenu[5]="Visualizzare  il numero di lezioni tenute da ogni maestro in ordine alfabetico di cliente ";
        vociMenu[6]="Esporta libri su file CSV";
        vociMenu[7]="Salva dati";
    
          
        
        try 
        {
            c1=c1.caricaCircolo(nomeFileBinario);
            System.out.println("Dati caricati correttamente");
        } 
        catch (IOException ex) 
        {
            System.out.println("Impossibile accedere al file in lettura. I dati non sono stati caricati");
        } 
        catch (FileException ex) 
        {
            System.out.println(ex.toString());
        }
        
        Menu menu= new Menu(vociMenu);
        
        do
        {
            try
            {
                sceltaUtente=menu.sceltaMenu();
                switch(sceltaUtente)
                {
                    case 0:
                    {
                        System.out.println("L'applicazione verrà terminata");
                        break;
                    }
                    case 1:
                    {
                        Prenotazione p;
                        Maestro m;
                        String nome,cognome,nomeMaestro,cognomeMaestro;
                        int giorno,mese,anno,ora;
                        
                        System.out.println("\nInserisci nome:");
                        nome=tastiera.nextLine();
                        System.out.println("Inserisci cognome:");
                        cognome=tastiera.nextLine();
                        System.out.println("Inserisci giorno:");
                        giorno=tastiera.nextInt();
                        System.out.println("Inserisci mese:");
                        mese=tastiera.nextInt();
                        System.out.println("Inserisci anno:");
                        anno=tastiera.nextInt();
                        System.out.println("Inserisci ora:");
                        ora=tastiera.nextInt();
                        System.out.println("Nome maestro:");
                        nomeMaestro=tastiera.nextLine();
                        System.out.println("Cognome maestro:");
                        cognomeMaestro=tastiera.nextLine();
                        m=new Maestro(nomeMaestro,cognomeMaestro);
                        p=new Prenotazione(ora, nome, cognome, giorno, mese, anno, ora, anno, m);
                        try 
                        {
                            c1.aggiungiPrenotazione(p);
                            System.out.println("Inserimento avvenuto correttamente");
                        }
                        catch (EccezioneDataNonValida e1) 
                        {
                            System.out.println(e1.toString());;
                        }
                        System.out.println("Premi un pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Ripiano [0..4]-->");
                        ripiano=tastiera.nextInt();
                        System.out.println("Posizione [0..14]-->");
                        posizione=tastiera.nextInt();
                        try
                        {
                            libro=s1.getLibro(ripiano, posizione);
                            if (libro==null)
                                System.out.println("Nessun libro presente in questa posizione");
                            else
                            {
                                System.out.println(libro.toString());
                            }
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }

                        System.out.println("Premi un pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Ripiano [0..4]-->");
                        ripiano=tastiera.nextInt();
                        System.out.println("Posizione [0..14]-->");
                        posizione=tastiera.nextInt();

                        try
                        {
                            s1.rimuoviLibro(ripiano, posizione);
                            System.out.println("Ok rimozione effettuata correttamente");
                        }
                        catch(EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        } 
                        catch (EccezionePosizioneVuota e2) 
                        {
                               System.out.println(e2.toString());
                        }
                        System.out.println("Premi un pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 4:
                    {
                        String[] elencoTitoli;
                        String autore;
                        System.out.println("Autore-->");
                        autore=tastiera.nextLine();
                        try
                        {
                            elencoTitoli=s1.elencoTitoliAutore(autore);
                            if (elencoTitoli==null)
                            System.out.println("Nessun libro presente dell'autore "+autore);
                            else
                            {
                                for (int i=0;i<elencoTitoli.length;i++)
                                    System.out.println(elencoTitoli[i]);
                            }
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        System.out.println("Premi un pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 5:
                    {
                        System.out.println(s1.toString());
                        break;
                    }
                    case 6:
                    {
                        try
                        {
                            System.out.println(s1.elencoAlfabeticoLibri());
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        break;
                    }
                    case 7:
                    {
                        Libro[] elencoLibri;
                        try
                        {
                            elencoLibri=s1.elencoLibriOrdinatiPrezzo();
                            for (int i=0;i<elencoLibri.length;i++)
                                System.out.println(elencoLibri[i].toString()+ " € "+elencoLibri[i].prezzo());
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        break;

                    }
                    case 8:
                    {
                        Libro[] elencoLibri;
                        try
                        {
                            elencoLibri=s1.elencoLibriAlfabeticoAutoreTitolo();
                            for (int i=0;i<elencoLibri.length;i++)
                                System.out.println(elencoLibri[i].toString()+ " € "+elencoLibri[i].prezzo()); 
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        break;
                    }
                    case 9:
                    {
                        try
                        {
                            s1.esportaLibriCSV(nomeFileTesto);
                            System.out.println("Libri esportati correttamente");
                        }
                        catch(IOException e1)
                        {
                            System.out.println("Impossibile accedere al file");
                        }
                        catch(FileException e2)
                        {
                            System.out.println(e2.toString());
                        }
                        catch(EccezionePosizioneNonValida e3)
                        {
                            System.out.println(e3.toString());
                        }
                        break;
                    }
                    case 10:
                    {
                        try 
                        {
                            s1.salvaScaffale(nomeFileBinario);
                            System.out.println("Dati salvati correttamente");
                        } catch (IOException ex) 
                        {
                            System.out.println("Impossibile accedere al file in scrittura");
                        }

                     }

                }
                
            }
            catch (InputMismatchException | NumberFormatException e1 )
            {
                tastiera.nextLine();
                System.out.println("Input non corretto");
            }
            
            
            
        }while (sceltaUtente!=0);
    }
}
