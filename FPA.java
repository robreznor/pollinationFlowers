package pollinationFlowers;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author roberto
 */
public class FPA {
    int t, maxGeneration, n;
    int bestx1Pos, bestx2Pos;
    int[] sol;
    int[][] restricciones;;
    float p, gBest[],gBBest[],BBest,s, next_x1[],next_x2[],x1[],x2[];
    GenerateFlowers flowers;
    Random rand;
    String path;
    
    public FPA(int maxGeneration, int n){   
//asignación de variables
        VarCons varcons= new VarCons();
        String path="E:\\uvalpo\\2do Semestre 2016\\fia\\tarea 2\\input\\scp41.txt";
        int[][] var=varcons.generate(path);
        this.maxGeneration=maxGeneration;
        this.n = n;
        this.restricciones = new int[][]{{1,1,0,0,0,0,0}, {1,1,1,1,0,0,1}, {0,1,1,1,0,0,0}, {0,1,1,1,1,0,1}, {0,0,0,1,1,1,1}, {0,0,0,0,1,1,0}, {0,1,0,1,1,0,1}};
        sol=new int[7];
        //flowers=new GenerateFlowers();      //se genera la población
        //x1=flowers.Generate(n,8);
        //x2=flowers.Generate(n,(float)5.33);
        p=(float)0.8;
        rand = new Random();
        s=100;
        gBBest= new float[2];
        //next_x1=new float[x1.length];
        //next_x2=new float[x2.length];               
    }
    /**
     * este metodo hace la pega
     */
    public void Algorithm(){
        
  //      gBest= GBest();                     //Mejor solución inicial
        this.min(restricciones);
        for(int i=0;i<restricciones.length;i++){
            System.out.println("cumple: "+this.Cover(restricciones[i],restricciones.length));
            
        }
        
        while(t < maxGeneration){           //Iteraciones que se ejecuta el algoritmo
            for(int i=0;i<n;i++){
                
                if(rand.nextFloat()<p){ 
 //                   next_x1[i]=x1[i]+ Levy()*(gBest[0]-x1[i]);      //La planta es del tipo biotico por lo tanto el transporte se realiza mediante una dist. de Levy                
                }
                else{
//                   next_x2[i]=x2[i]+ rand.nextFloat()*(Math.abs(x2[(int)(rand.nextDouble()*n-1)]-x2[(int)(rand.nextDouble()*n-1)])); //Transporte mediante una dist. Uniforme 

                }
            }
 //           gBest= GBest();
     
   /*         if(GBestValue()>BBest){  //Si el nuevo valor tiene mejor fitness se selecciona como mejor solución 
                gBBest = gBest;
                BBest=GBestValue();
            } 
     */       
            x1=next_x1;  
            x2=next_x2;
            t++;           
        }
        Sol();
    }
    
    private float Levy(){ //Calcula la distrib. de Levy
        float levy = (float)(1.5*(Math.sqrt(Math.PI)/2)*Math.sin(Math.PI*(1.5/2))*(1/Math.pow(s,2.5))); 
        return levy;
    }

  /*  public float[] GBest(){                     //Calcula la mejor solución de una iteración.
        float best=0,sol;
        float[] bests= new float[2];
        for(int i=0;i<x1.length;i++){
                sol=x1[i]*40000+x2[ i]*45000;
                if(sol>best && Restriccion(i,i)){
                    best = sol;
                    bests[0]=x1[i];
                    bests[1]=x2[i];
                }       
        }
        return bests;
    }
    
    public float GBestValue(){                  //Evalua la función objetivo. 
        return gBest[0]*40000+gBest[1]*45000;
    }
    */
    
    public void min(int[][] restricciones){
        int min;
        for(int i=0;i<restricciones.length;i++){
            min=10000;
            for(int j=0;j<restricciones[i].length;j++){
                if(restricciones[i][j]>=1){
                    if(restricciones[i][j]<min){
                        min=restricciones[i][j];
                        sol[j]=1;
                    }
                }     
            }                
        }
        
        for(int i=0;i<sol.length;i++){
            System.out.println(""+sol[i]);
        }
    }
    
    public int Cover(int[] restriccion,int length){
        int acumulado=1;
        if(length-1<1) return acumulado;
        else{
            acumulado=acumulado*restriccion[length-1];
            length--;
            return acumulado;
        }
            
        
    }
    
    public void ShowPop(){              
        for(int i=0;i<x1.length;i++){           //Muesta la población
            System.out.print("x1: "+x1[i]+" ");
        }
        System.out.println();
          for(int i=0;i<x1.length;i++){
            System.out.print("x2: "+x2[i]+" ");
        }
        System.out.println();
         
    }

    public boolean Restriccion(int i,int j){    //Verifica que se cumplan las restricciones
        if(20*x1[i]+30*x2[j]<=300 && 30*x1[i]+50*x2[j]<=800 && 10*x1[i]+15*x2[j]<=80) return true;
        else return false;
   
    }
    
    public void Sol(){                          //Imprime la solución del problema
        DecimalFormat decimales = new DecimalFormat("0.000");
        System.out.println("["+gBBest[0]+"]["+gBBest[1]+"] = "+ decimales.format(gBBest[0]*40000+45000*gBBest[1]));
        
    }
}