/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pollinationFlowers;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 *
 * @author rob
 */
public class WriteFile {
    
    public void WriteFile(String instance, String size, String sol, String archivo, boolean isOpen, int seed,float p,float lambda,float s,float pbin){
        int bestKnowParse;
        float accuracy;
        int[] bestKnow={514,429,512,516,494,512,560,430,492,641,265,253,302,226,242,211,213,293,288,279,0,138,146,145,131,161};
    
        FileWriter fichero = null;
        PrintWriter pw = null;
        bestKnowParse=bestKnow[Integer.parseInt(instance)-40];
        accuracy=((float)Integer.parseInt(sol)-(float)bestKnowParse)/(float)bestKnowParse*100;
        DecimalFormat format = new DecimalFormat(".00");
        try
        {
           
            fichero = new FileWriter(".//src//pollinationFlowers//output//scp.txt", isOpen);
            pw = new PrintWriter(fichero);
                if(!isOpen){
                pw.print("| Instance\t");
                pw.print("| Size\t\t\t");
                pw.print("| Best know\t");
                pw.print("| Best fpa\t");
                pw.print("| Approach\t");
                pw.print("| Seed\t\t");
                pw.print("| p\t\t");
                pw.print("| lambda\t");
                pw.print("| s\t\t");
                pw.println("| pbin\t");
                pw.println("================================================================================================================");    
            }
            
            pw.print("| "+instance+"\t\t");
            pw.print("| "+size+"\t");
            pw.print("| "+bestKnow[Integer.parseInt(instance)-40]+"\t\t");
            pw.print("| "+sol+"\t\t");
            pw.print("| "+format.format(accuracy)+"%\t");
            pw.print("| "+seed);
            pw.print("| "+p+"\t");
            pw.print("| "+lambda+"\t\t");
            pw.print("| "+s+"\t");
            pw.println("| "+pbin+"\t");
           

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
 
