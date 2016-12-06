package pollinationFlowers;

import javax.swing.JPanel;

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
        t=100;
        
        boolean isOpen=false;
        for (int i = instance; i <= 65; i++) {
            if(i==60) i++;
            j=0;
            while(j<1){
                FPA fpa = new FPA(t,n,i, isOpen);
                isOpen=true;
                fpa.Algorithm();
                j++;
            }
                    
        }        
    }
}
