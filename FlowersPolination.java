package pollinationFlowers;

/**
 *
 * @author roberto
 */
public class FlowersPolination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int instance=41;
        int j,t,n;
        
        n=100;
        t=1000;
        
        boolean isOpen=false;
        for (int i = instance; i <= 41; i++) {
        if(i==60) i++;
        j=0;
            
        FPA fpa = new FPA(t,n,i, isOpen);
        fpa.Algorithm();
        j++;
            
                    
        }        
    }
}
