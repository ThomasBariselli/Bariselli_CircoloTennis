/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;


import Eccezioni.FileException;
import java.io.*;

/**
 *
 * @author user
 */
public class TextFile 
{
    private char mode;
    private BufferedReader reader;
    private BufferedWriter writer;
    
    public TextFile(String fileName, char mode) throws IOException
    {
        this.mode='R';
        if(mode=='w' || mode=='W')
            this.mode='W';
        
        if(this.mode=='W')
            writer=new BufferedWriter(new FileWriter(fileName));
        else
            reader=new BufferedReader(new FileReader(fileName));
    }
    
    public TextFile(String fileName, char mode, boolean append) throws IOException
    {
        this.mode='R';
        if(mode=='w' || mode=='W')
            this.mode='W';
        
        if(this.mode=='W')
            writer=new BufferedWriter(new FileWriter(fileName,append));
        else
            reader=new BufferedReader(new FileReader(fileName));
    }
    
    public void toFile(String line) throws FileException, IOException
    {
        if(mode=='R')
            throw new FileException("Impossibile scrivere su file, file aperto in lettura.");
        
        writer.write(line);
        writer.newLine();
    }

    public String fromFile() throws FileException, IOException
    {
        String rigaLetta;
        
        if(mode=='W')
            throw new FileException("Impossibile leggere da file, file aperto in scrittura.");
        
        rigaLetta=reader.readLine();
        if(rigaLetta==null)
            throw new FileException("Fine del file.");
        
        return rigaLetta;
    }
    
    public void close() throws IOException
    {
        if(mode=='W')
            writer.close();
        else
            reader.close();
    }
}
