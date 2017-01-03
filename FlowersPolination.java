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
        
        n=500;
        t=200;
        
        boolean isOpen=false;
        for (int i = instance; i <= 41; i++) {
            if(i==60) i++;
            j=0;
            while(j<1){
                FPA fpa = new FPA(t,n,i,"scp", isOpen);
                isOpen=true;
                fpa.Algorithm();
                j++;
            }        
        }        
//        for (int i = 1; i <= 5; i++) {
//            j=0;
//            while(j<1){
//                FPA fpa = new FPA(t,n,i,"scpa", isOpen);
//                fpa.Algorithm();
//                j++;
//            }        
//        }
//        for (int i = 1; i <= 5; i++) {
//            j=0;
//            while(j<1){
//                FPA fpa = new FPA(t,n,i,"scpb", isOpen);
//                fpa.Algorithm();
//                j++;
//            }       
//        }
//        for (int i = 1; i <= 5; i++) {
//            j=0;
//            while(j<1){
//                FPA fpa = new FPA(t,n,i,"scpc", isOpen);
//                fpa.Algorithm();
//                j++;
//            }       
//        } 
//        for (int i = 1; i <= 5; i++) {
//            j=0;
//            while(j<1){
//                FPA fpa = new FPA(t,n,i,"scpd", isOpen);
//                fpa.Algorithm();
//                j++;
//            }       
//        } 
        for (int i = 1; i <= 5; i++) {
            j=0;
            while(j<1){
                FPA fpa = new FPA(t,n,i,"scpnre", isOpen);
                isOpen=true;
                fpa.Algorithm();
                j++;
            }       
        } 
        for (int i = 1; i <= 5; i++) {
            j=0;
            while(j<1){
                FPA fpa = new FPA(t,n,i,"scpnrf", isOpen);
                fpa.Algorithm();
                j++;
            }       
        } 
        for (int i = 1; i <= 5; i++) {
            j=0;
            while(j<1){
                FPA fpa = new FPA(t,n,i,"scpnrg", isOpen);
                fpa.Algorithm();
                j++;
            }       
        } 
        for (int i = 1; i <= 1; i++) {
            j=0;
            while(j<1){
                FPA fpa = new FPA(t,n,i,"scpnrh", isOpen);
                fpa.Algorithm();
                j++;
            }       
        } 
    }
}
