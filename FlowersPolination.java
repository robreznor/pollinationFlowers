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
        
        n=1000;
        t=300;
                ;
        boolean isOpen=false;
        for (int i = instance; i <= 41; i++) {
            if(i==60) i++;
            j=0;
            while(j<10){
                FPA fpa = new FPA(t,n,i, isOpen);
                isOpen=true;
                fpa.Algorithm();
                j++;
            }
                    
        }        
    }
}
