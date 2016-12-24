package pollinationFlowers;

import static java.lang.Math.exp;
import java.util.Random;

/**
 *
 * @author roberto
 */
public class FPA {
    
    int t, maxGeneration,n, gBest, varLength, pos, instance, seed, posl, poss, posp, posb, bestposl, bestposs, bestposp, bestposb;
    int[] cost, best, currentBest, bestOfBest, x_data, y_data;
    int[][] var,sol,next;
    float x;
    float[] p={(float)0.72,(float)0.73,(float)0.74,(float)0.75,(float)0.85};
    float[] s={150,100};
    float[] pbin={(float)0.6,(float)0.7,(float)0.75,(float)0.78,(float)0.8};
    float[]lambda={1,(float)1.5,(float)1.8};
    GenerateFlowers flowers;
    Random rand;
    WriteFile wr;
    String size, archivo;
    boolean isOpen;
    
    /**
     * Inicialización de variables.
     * @param maxGeneration cantidad de iteraciones que se ejecuta el algoritmo.
     * @param n cantidad de soluciones que se generan de manera aleatoria.
     * @param instance número de la instancia que se esta ejecutando.
     * @param isOpen señala si el archivo se encuentra en uso.
     * 
     */
    public FPA(int maxGeneration, int n, int instance, boolean isOpen){   
         
        VarCons varcons= new VarCons();
        this.archivo= "scp"+instance+".txt";
        var= varcons.generate(archivo);
        varLength= var[0].length;
        this.maxGeneration= maxGeneration;
        this.n = n;
        this.instance= instance;
        sol= new int[n][varLength];
        next= new int[n][varLength];
        bestOfBest= new int[varLength];
        flowers= new GenerateFlowers();
        rand = new Random();
        this.seed= rand.hashCode();
        //this.seed=1956725890;
        rand.setSeed(seed);
        sol= flowers.Generate(n,varLength,seed);
        wr= new WriteFile();
        size= Integer.toString(var.length-1)+" x "+Integer.toString(varLength);
        this.isOpen= isOpen;
        x_data= new int[maxGeneration];
        y_data= new int[maxGeneration];
        
    }
    
    /**
     * Algoritmo basado en el proceso de polinización de las flores. En cada iteración encuentra el valor óptimo entre las soluciónes y posteriormente
     * compara entre los óptimos de cada iteración para así llegar a la solución óptima.
     * 
     */
    public void Algorithm(){

        sol=Repair();
        best=  Best(sol);                     
        gBest= Fitness(best);
        System.arraycopy(best, 0, bestOfBest, 0, best.length);
       
        for(posp=0;posp<p.length;posp++){
           for(posl=0;posl<lambda.length;posl++){
               for(poss=0;poss<s.length;poss++){
                   for(posb=0;posb<pbin.length;posb++){
                    t=0;
                    while(t < maxGeneration){           
                        for(int i = 0; i < n; i++){
                            for (int j = 0; j < sol[i].length; j++) {
                                if (p[posp] < rand.nextFloat()) {
                                    x=sol[i][j]+ Levy()*(gBest-sol[i][j]);
                                }else{
                                    
                                        x=sol[i][j] + rand.nextFloat()*(Math.abs(sol[i][rand.nextInt(varLength)]-sol[i][rand.nextInt(varLength)])); //Transporte mediante una dist. Uniforme 
                                }
                                //System.out.println(""+1/(1+(exp(-x))));
                                if((1/(1+(exp(-x))))>=pbin[posb]) next[i][j]=1;
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
                            bestposl=posl;
                            bestposs=poss;
                            bestposp=posp;
                            bestposb=posb;
                        }
                        x_data[t]=t;
                        y_data[t]=gBest;
                        System.out.println(""+gBest);
                        t++;
                    }
                   }     
               }
           }
        }
        //new Draw(x_data,y_data, "Convergencia scp"+Integer.toString(instance));
        wr.WriteFile(Integer.toString(instance),size,Integer.toString(Fitness(bestOfBest)),archivo, isOpen,seed,p[bestposp],lambda[bestposl],s[bestposs], pbin[bestposb]);
        //System.out.println(""+gBest);
        isOpen=true;
    }
    
    /**
     * Calcula la distribución de levy
     * @return  Valor de la distribución de levy
     */
    
    private float Levy(){ 
        float levy = (float)(lambda[posl]*(Math.sqrt(Math.PI)/2)*Math.sin(Math.PI*(lambda[posl]/2))*(1/Math.pow(s[poss],2.5))); 
        return levy;
    }
    
    /**
     * Repara las soluciones que no cumplan con todas las restricciones.
     * @return El vector de soluciones reparado.
     */
    
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
    
    /**
     * Calcula el valor de la solución evaluada en la función objetivo.
     * @param sol Solución a evaluar.
     * @return Valor de la solución.
     */
    
    private int Fitness(int sol[]){
            int fitness=0;
            for(int i=0;i<varLength;i++){
                fitness=fitness+sol[i]*var[0][i];
            }
            return fitness;
    }
    
    /**
     * Calcula la mejor solución entre las soluciones.
     * @param sol Conjunto de soluciones.
     * @return El vector con la mejor solución.
     */
    
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
