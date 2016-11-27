/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pollinationFlowers;
import java.util.Random;
/**
 *
 * @author roberto
 */
public class GenerateFlowers {
    
    public float[] Generate(int n,float lim){ //Genera la población
        float[] flowers = new float[n];
        for(int i=0;i<n;i++){      
                Random rand=new Random();
                flowers[i]=rand.nextFloat()*lim;  
        }
        return flowers;
    }
    
}
