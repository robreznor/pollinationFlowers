package pollinationFlowers;

import static java.lang.Math.exp;
import java.util.Random;

/**
 *
 * @author roberto
 */
public class FPA {
    int t, maxGeneration,n, gBest, varLength, pos, instance;
    int[] cost, best, currentBest, bestOfBest;
    int[][] var,sol,next;
    float p ,x ,s;
    GenerateFlowers flowers;
    Random rand, bin;
    WriteFile wr;
    String size, archivo;
    boolean isOpen;
    public FPA(int maxGeneration, int n, int instance, boolean isOpen){   
         
        VarCons varcons= new VarCons();
        this.archivo="scp"+instance+".txt";
        var=varcons.generate(archivo);
        varLength=var[0].length;
        this.maxGeneration=maxGeneration;
        this.n = n;
        this.instance= instance;
        sol=new int[n][varLength];
        next=new int[n][varLength];
        bestOfBest=new int[varLength];
        flowers=new GenerateFlowers();      
        sol=flowers.Generate(n,varLength);
        p=(float)0.8;
        rand = new Random();
        bin = new Random();
        s=100;
        wr=new WriteFile();
        size=Integer.toString(var.length-1)+" x "+Integer.toString(varLength);
        this.isOpen=isOpen;
    }
    /**
     * este metodo hace la pega
     */
    public void Algorithm(){
        
        sol=Repair();
        best=  Best(sol);                     //Mejor solución inicial
        gBest= Fitness(best);
        while(t < maxGeneration){           //Iteraciones que se ejecuta el algoritmo
            for(int i = 0; i < n; i++){
                for (int j = 0; j < sol[i].length; j++) {
                    if (p < rand.nextFloat()) {
                        x=sol[i][j]+ Levy()*(gBest-sol[i][j]);
                    }else{
                        x=sol[i][j] + rand.nextFloat()*(Math.abs(sol[i][rand.nextInt(varLength)]-sol[i][rand.nextInt(varLength)])); //Transporte mediante una dist. Uniforme 
                    }
                    //System.out.println(""+1/(1+(exp(-x))));
                    if(1/(1+(exp(-x)))>=0.84) next[i][j]=1;
                    else next [i][j]=0;  
                }
            }
            
            System.arraycopy(next, 0, sol, 0, next.length);
            sol=Repair();
            //System.out.println(""+Fitness(Best(sol)));
            currentBest= Best(sol);
            if(Fitness(currentBest)<Fitness(best)){
                System.arraycopy(currentBest, 0, best, 0, currentBest.length);
            }
            if(Fitness(best)< gBest){
                gBest= Fitness(best);              
                System.arraycopy(best, 0, bestOfBest, 0, best.length);
            }
            t++;
        }
        
        wr.WriteFile(Integer.toString(instance),size,Integer.toString(Fitness(bestOfBest)),archivo, isOpen);
        System.out.println(""+Fitness(bestOfBest));
    }
    
    private float Levy(){ //Calcula la distrib. de Levy
        float levy = (float)(1.5*(Math.sqrt(Math.PI)/2)*Math.sin(Math.PI*(1.5/2))*(1/Math.pow(s,2.5))); 
        return levy;
    }
  
    private int[][] Repair(){
        boolean ok;
        for (int i = 0; i < sol.length; i++) {
            for(int j = 1; j < var.length; j++) {
               ok=false;
               for(int k=0; k < varLength; k++){
                   if(var[j][k]*sol[i][k]>=1){
                       ok=true;
                       break;
                   }
               } 
               if(!ok){
                     for (int k = 1; k < varLength; k++) {
                       if(var[j][k]>0){
                           sol[i][k] = 1;
                           break;
                       } 
                       
                   }
               }
            }    
        }
        return sol;
    }  
    
    private int Fitness(int sol[]){
            int fitness=0;
            for(int i=0;i<varLength;i++){
                fitness=fitness+sol[i]*var[0][i];
            }
            return fitness;
    }
    
    private int[] Best(int sol[][]){
        int bestPos=0, fitness, bestFitness=999999999; 
        for (int i = 0; i < sol.length; i++) {
            fitness=this.Fitness(sol[i]);
            if(fitness<bestFitness){
                bestFitness=fitness;
                bestPos=i;
            }       
        }   
        return sol[bestPos];
    }    
}
