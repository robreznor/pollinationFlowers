/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowerspolination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author rob
 */
public class VarCons {
    int varTotal, constTotal, varCount=0, constCount=0,constNum = 0, constArrayNum=1;
    String  line = null;
    public int[][] generate(String path){
        try{
           // open input stream test.txt for reading purpose.
           BufferedReader br = new BufferedReader(new FileReader(path));
           line=br.readLine();
           varTotal=Integer.parseInt(line.split(" ")[2]);
           constTotal=Integer.parseInt(line.split(" ")[1]);
           int[][] output= new int[constTotal+1][varTotal];
     
           while ((line = br.readLine()) != null) {
               for(int i=1;i<line.split(" ").length;i++){
                   if(varCount<varTotal){
                       output[0][varCount]=Integer.parseInt(line.split(" ")[i]);
                       varCount++;
                   }else{
                       if(constCount==0){ 
                           constCount=Integer.parseInt(line.split(" ")[1]);
                       }else{              
                           output[constArrayNum][(Integer.parseInt(line.split(" ")[i])-1)]=1;
                           constCount--;
                           if(constCount==0)    constArrayNum++;
                       }     
                   }
               }
           }
           return output;
        }catch(Exception e){
           e.printStackTrace();
        }
        return null;
    }
    
}
 