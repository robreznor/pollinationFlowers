/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pollinationFlowers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 *
 * @author rob
 */
public class WriteFile {
    
    public void WriteFile(String instance,String name, String size, String sol, String archivo, boolean isOpen, int seed,float p,float lambda,float s,float pbin){
        int bestKnowParse=0;
        float accuracy;
        int[] bestKnowScp={514,429,512,516,494,512,560,430,492,641,265,253,302,226,242,211,213,293,288,279,0,138,146,145,131};
        int[] bestKnowScpa={253,252,232,234,236};
        int[] bestKnowScpb={69,76,80,79,72};
        int[] bestKnowScpc={227,219,243,219,215};
        int[] bestKnowScpd={60,66,72,62,61};
        int[] bestKnowScpnre={29,30,27,28,28};
        int[] bestKnowScpnrf={14,15,14,14,13};
        int[] bestKnowScpnrg={176,154,166,168,168};
        int[] bestKnowScpnrh={63,63,59,58,55};
        FileWriter fichero = null;
        PrintWriter pw = null;
        switch (name) {
            case "scp": bestKnowParse=bestKnowScp[Integer.parseInt(instance)-40];
                        break;
            case "scpa": bestKnowParse=bestKnowScpa[Integer.parseInt(instance)-1];
                         break;
            case "scpb": bestKnowParse=bestKnowScpb[Integer.parseInt(instance)-1];
                         break;
            case "scpc": bestKnowParse=bestKnowScpc[Integer.parseInt(instance)-1];
                         break;
            case "scpd": bestKnowParse=bestKnowScpd[Integer.parseInt(instance)-1];
                         break;
            case "scpnre": bestKnowParse=bestKnowScpnre[Integer.parseInt(instance)-1];
                         break;
            case "scpnrf": bestKnowParse=bestKnowScpnrf[Integer.parseInt(instance)-1];
                         break;
            case "scpnrg": bestKnowParse=bestKnowScpnrg[Integer.parseInt(instance)-1];
                         break;
            case "scpnrh": bestKnowParse=bestKnowScpnrh[Integer.parseInt(instance)-1];
                         break;
        }
        accuracy=((float)Integer.parseInt(sol)-(float)bestKnowParse)/(float)bestKnowParse*100;
        DecimalFormat format = new DecimalFormat(".0");
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
            
            pw.print("| "+name+instance+" \t");
            pw.print("| "+size+"\t");
            pw.print("| "+bestKnowParse+"\t\t");
            pw.print("| "+sol+"\t\t");
            pw.print("| "+format.format(accuracy)+"%\t\t");
            pw.print("| "+seed+"  \t");
            pw.print("| "+p+"\t");
            pw.print("| "+lambda+"\t\t");
            pw.print("| "+s+"\t");
            pw.println("| "+pbin+"\t");
           

        }catch(IOException | NumberFormatException e){
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
 
