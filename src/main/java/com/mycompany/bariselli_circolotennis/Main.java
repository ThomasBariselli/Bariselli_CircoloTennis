/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariselli_circolotennis;

import Eccezioni.EccezioneNonPresente;
import Eccezioni.EccezioneCodiceNonPresente;
import Eccezioni.EccezioneDataNonValida;
import Eccezioni.EccezioneNessunMaestroPresente;
import Eccezioni.FileException;
import java.io.IOException;
import java.time.LocalDateTime;
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
    public static void main(String[] args) throws EccezioneCodiceNonPresente 
    {
        String[] vociMenu=new String[9];
        int sceltaUtente=-1;
        boolean sceltaSalva=true;
        Scanner tastiera=new Scanner(System.in);
        Circolo c1=new Circolo();
        Maestro[] maestriOrdinato;
        int esitoOperazione,codice=0;
        String caricamentoDaFileOK;
        String nomeFileTesto="prenotazioniCircolo.txt";
        String nomeFileBinario="circolo.bin";
        

        vociMenu[0]="Esci";
        vociMenu[1]="Aggiungi prenotazione";
        vociMenu[2]="Rimuovi prenotazione";
        vociMenu[3]="Visualizzare i dati relativi a tutte le lezioni prenotate nel circolo";
        vociMenu[4]="Visualizzare i dati relativi a tutte le lezioni prenotate da un cliente";
        vociMenu[5]="Visualizzare tutte le lezioni prenotate per un determinato maestro";
        vociMenu[6]="Visualizzare  il numero di lezioni tenute da ogni maestro in ordine alfabetico di maestro ";
        vociMenu[7]="Esporta libri su file CSV";
        vociMenu[8]="Salva dati";
    
          
        
        try 
        {
            c1=c1.caricaCircolo(nomeFileBinario);
         
            for(int i=0;i<c1.getnPrenotazioniPresenti();i++)
            {
                codice++;
                c1.setCodicePrenotazione(i,codice);
            }
            
            for(int i=0;i<c1.getnPrenotazioniPresenti();i++)
            {
                if(c1.getPrenotazione(i).getDataOraLezione().compareTo(LocalDateTime.now())<0)
                {
                    c1.rimuoviPrenotazione(i+1);//ciclo che elimina tutte le prenotazioni con date già passate
                    sceltaSalva=false;
                }
            }
            
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
                        sceltaSalva=false;
                        
                        try
                        {
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
                            System.out.println("Inserisci ora(8-11 14-17:");
                            ora=tastiera.nextInt();
                            tastiera.nextLine();
                            System.out.println("Nome maestro:");
                            nomeMaestro=tastiera.nextLine();
                            System.out.println("Cognome maestro:");
                            cognomeMaestro=tastiera.nextLine();
                            m=new Maestro(nomeMaestro,cognomeMaestro);
                            p=new Prenotazione(codice+1, nome, cognome, giorno, mese, anno, ora, 0, m);
                            c1.aggiungiPrenotazione(p);
                            codice++;
                            System.out.println("Inserimento avvenuto correttamente");
                        }
                        catch(java.time.DateTimeException e2)
                        {
                            System.out.println("Data non valida,reinserirla");
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
                        int codiceDaEliminare;
                        System.out.println("Codice prenotazione da eliminare-->");
                        codiceDaEliminare=tastiera.nextInt();
                        sceltaSalva=false;
                        
                        try
                        {
                            c1.rimuoviPrenotazione(codiceDaEliminare);
                            System.out.println("Rimozione effettuata");
                        }
                        catch(EccezioneCodiceNonPresente e1)
                        {
                            System.out.println(e1.toString());
                        }

                        System.out.println("Premi pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 3:
                    {
                        System.out.println(c1.toString());
                        System.out.println("Premi pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 4:
                    {
                        Prenotazione[] elencoPrenotazioniCliente;
                        String nome,cognome;
                        System.out.println("Nome-->");
                        nome=tastiera.nextLine();
                        System.out.println("Cognome-->");
                        cognome=tastiera.nextLine();
                        try
                        {
                            elencoPrenotazioniCliente=c1.getPrenotazioniCliente(nome,cognome);
                            for(int i=0;i<elencoPrenotazioniCliente.length;i++)
                                System.out.println("\n"+elencoPrenotazioniCliente[i]);
                        }
                        catch(EccezioneNonPresente e1)
                        {
                            System.out.println(e1.toString());
                        }
                        
                        System.out.println("Premi pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 5:
                    {
                        Prenotazione[] elencoPrenotazioniMaestro;
                        String nomeMaestro,cognomeMaestro;
                        System.out.println("Nome maestro-->");
                        nomeMaestro=tastiera.nextLine();
                        System.out.println("Cognome maestro-->");
                        cognomeMaestro=tastiera.nextLine();
                        try
                        {
                            elencoPrenotazioniMaestro=c1.getPrenotazioniMaestro(nomeMaestro,cognomeMaestro);
                            for(int i=0;i<elencoPrenotazioniMaestro.length;i++)
                                System.out.println("\n"+elencoPrenotazioniMaestro[i]);
                        }
                        catch(EccezioneNonPresente e1)
                        {
                            System.out.println(e1.toString());
                        }
                        
                        System.out.println("Premi pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 6:
                    {
                        try
                        {
                            maestriOrdinato=c1.getnPrenotazioniordinateMaestro();
                            for(int i=0;i<maestriOrdinato.length;i++)
                            {
                                if(maestriOrdinato[i]!=null)
                                {
                                    System.out.println(maestriOrdinato[i].getCognome()+" "+maestriOrdinato[i].getNome()+"-->"+maestriOrdinato[i].getLezioniMaestro()+"\n");
                                }
                            }
                            
                        }
                        catch(EccezioneNessunMaestroPresente e1)
                        {
                            System.out.println(e1.toString());
                        }
                        System.out.println("Premi pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 7:
                    {
                        try
                        {
                            c1.esportaPrenotazioniCSV(nomeFileTesto);
                            System.out.println("Prenotazioni esportate correttamente");
                        }
                        catch(IOException e1)
                        {
                            System.out.println("Impossibile accedere al file");
                        }
                        catch(FileException e2)
                        {
                            System.out.println(e2.toString());
                        }
                        System.out.println("Premi pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 8:
                    {
                        try 
                        {
                            c1.salvaCircolo(nomeFileBinario);
                            sceltaSalva=true;
                            System.out.println("Dati salvati correttamente");
                        } catch (IOException ex) 
                        {
                            System.out.println("Impossibile accedere al file in scrittura");
                        }
                        System.out.println("Premi pulsante per continuare");
                        tastiera.nextLine();
                        break;

                    }
                }
                
            }
            catch (InputMismatchException | NumberFormatException e1 )
            {
                tastiera.nextLine();
                System.out.println("Input non corretto");
            }
            
            
            
        }while (sceltaUtente!=0);
        if(sceltaSalva==false)
        {
            char scelta=' ';
            System.out.println("\nSalvare le modifiche?(S=si altro=no)");//domanda di sicurezza per evitare di uscire per sbaglio senza salvare
            scelta=tastiera.nextLine().charAt(0);
            if(scelta=='s' || scelta=='S')
            try 
            {
                c1.salvaCircolo(nomeFileBinario);
                System.out.println("Dati salvati correttamente");
            } catch (IOException ex) {
                System.out.println("Impossibile accedere al file in scrittura");
            }
            System.out.println("Premi pulsante per continuare");
            tastiera.nextLine();
            
        }
        
    }
}
