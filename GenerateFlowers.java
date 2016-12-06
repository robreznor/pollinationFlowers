package pollinationFlowers;
import java.util.Random;
/**
 *
 * @author roberto
 */
public class GenerateFlowers {
    
    public int[][] Generate(int n, int m,int seed){ //Genera la población
        int[][] flowers = new int[n][m];
        Random rand=new Random(seed);   
        for (int i = 0; i < n; i++) {
             for(int j=0;j< m;j++){      
                    
                    flowers[i][j]=(int)(rand.nextInt(2));
            }
            
        } 
        return flowers;
    }
    
}

