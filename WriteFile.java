/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pollinationFlowers;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author rob
 */
public class WriteFile {
    
    public void WriteFile(String Instance, String size, String sol, String archivo, boolean isOpen, int seed){

        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(".//src//pollinationFlowers//output//scp.txt", isOpen);
            pw = new PrintWriter(fichero);
                if(!isOpen){
                pw.print("| Instance\t");
                pw.print("| Size\t\t\t");
                pw.print("| Best\t");
                pw.println("| Seed\t");
                pw.println("===================================================");    
            }
            
            pw.print("| "+Instance+"\t\t");
            pw.print("| "+size+"\t");
            pw.print("| "+sol+"\t");
            pw.println("| "+seed+"\t");
           

        }catch(Exception e){
            e.printStackTrace();
        }finally{
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
}  
}
 
