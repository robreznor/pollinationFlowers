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
        // TODO code application logic here
        int instance=40;
        boolean isOpen=false;
        for (int i = instance; i <= 65; i++) {
        if(i==60) i++;
        FPA fpa = new FPA(30,100,i, isOpen);
        isOpen=true;
        fpa.Algorithm();           
        }        
    }
}
