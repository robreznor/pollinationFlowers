package pollinationFlowers;

import static java.lang.Math.exp;
import java.util.Random;

/**
 *
 * @author roberto
 */
public class FPA {
    int t, maxGeneration,n, gBest, varLength, pos;
    int[] cost, best, currentBest, bestOfBest;
    int[][] var,sol,next;
    float p ,x ,s;
    GenerateFlowers flowers;
    Random rand, bin;
    
    public FPA(int maxGeneration, int n){   

        VarCons varcons= new VarCons();
        var=varcons.generate();
        varLength=var[0].length;
        this.maxGeneration=maxGeneration;
        this.n = n;
        sol=new int[n][varLength];
        next=new int[n][varLength];
        bestOfBest=new int[varLength];
        flowers=new GenerateFlowers();      
        sol=flowers.Generate(n,varLength);
        p=(float)0.8;
        rand = new Random();
        bin = new Random();
        s=100;
        
               
    }
    /**
     * este metodo hace la pega
     */
    public void Algorithm(){
        sol=Repair(var, sol);
        best=  Best(sol);                     //Mejor solución inicial
        gBest= Fitness(best, var[0]);
        while(t < maxGeneration){           //Iteraciones que se ejecuta el algoritmo
            for(int i = 0; i < n; i++){
                for (int j = 0; j < sol[i].length; j++) {
                    if (p < rand.nextFloat()) {
                        x=sol[i][j]*var[0][j]+ Levy()*(gBest*var[0][j]-sol[i][j]*var[0][j]);
                    }else{
                        pos=rand.nextInt(varLength);
                        x=sol[i][j]*var[0][j] + rand.nextFloat()*(Math.abs(sol[i][pos]*var[0][pos]-sol[i][rand.nextInt(varLength)]*var[0][pos])); //Transporte mediante una dist. Uniforme 
                    }
                    //System.out.println(""+1/(1+(exp(-x))));
                     if(1/(1+(exp(-x)))<=0.5) next[i][j]=1;
                     else next [i][j]=0;  
                }    
            }
            sol=Repair(var, sol);
            currentBest= Best(sol);
            if(Fitness(currentBest, var[0])<Fitness(best,var[0])){
                best=currentBest;
            }
            if(Fitness(best, var[0])< gBest){
                gBest= Fitness(best, var[0]);
                bestOfBest = best;
            }
            sol=next;
            t++;
        }
            System.out.println(""+Fitness(bestOfBest,var[0]));
    }
    
    private float Levy(){ //Calcula la distrib. de Levy
        float levy = (float)(1.5*(Math.sqrt(Math.PI)/2)*Math.sin(Math.PI*(1.5/2))*(1/Math.pow(s,2.5))); 
        return levy;
    }
  
    private int[][] Repair(int [][] constraint, int[][] sol){
        boolean ok;
        for (int i = 0; i < sol.length; i++) {
            for(int j = 1; j < constraint.length; j++) {
               ok=false;
               for(int k=0; k < constraint[j].length; k++){
                   if(constraint[j][k]*sol[i][k]>=1){
                       ok=true;
                       break;
                   }
               } 
               if(!ok){
                     for (int k = 0; k < varLength; k++) {
                       if(constraint[j][k]>0){
                           sol[i][k] = 1;
                       } 
                       
                   }
               }
            }    
        }
        return sol;
    }  
    
    private int Fitness(int sol[], int cost[]){
            int fitness=0;
            for(int i=0;i<varLength;i++){
                fitness=fitness+sol[i]*cost[i];
            }
            return fitness;
    }
    
    private int[] Best(int sol[][]){
        int bestPos=0, fitness, bestFitness=999999999; 
        for (int i = 0; i < sol.length; i++) {
            fitness=this.Fitness(sol[i], var[0]);
            if(fitness<bestFitness){
                bestFitness=fitness;
                bestPos=i;
            }       
        }   
        return sol[bestPos];
    }    
}
