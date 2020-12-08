/*
 * SCHOOL: UNIVERSIDAD AUTÓNOMA DE BAJA CALIFORNIA SUR
 * CAREER, SEMESTER AND TURN: INGENIERÍA EN DESARROLLO DE SOFTWARE 9no SEMESTRE - T.M.
 * CLASS: REDES NEURONALES
 * CODE BY:
 * PAULA IVONNE SOQUI HIRALES.
 * */
package perceptron;

import java.util.ArrayList;
import java.util.Random;

public class main {
	
	static int NUM_INSTANCES=2;
	
	public static double [] x =new double[NUM_INSTANCES];// INPUTS
	public static double [][]weight1 = {{0.1, 0.3},
									   {0.4, 0.7}};
	public static double [][]weight2 = {{0.1, 0.3},
			   							{0.4, 0.7}};
	public static double [] bias1 = new double[NUM_INSTANCES];//sesgo, bias, es la misma
	public static double [] bias2 = new double[NUM_INSTANCES];//sesgo, bias, es la misma
	
	public static double [] h1 = new double[NUM_INSTANCES];//capa intermedia
	public static double [] outputs = new double[NUM_INSTANCES];//esta salida
	public static double [] delta = new double[NUM_INSTANCES];
	public static double [] dh = new double[NUM_INSTANCES];
	//public static double [] expOutput=new double[NUM_INSTANCES];
	public static double [] expOutput= {1,0,1,0};
	static double learningRate = 0.3;
	static double output;//salida
    static double error;//error
    static double sumResult;//resultado de la suma
    
	
	/*
	//
	//RESULTADOS ESPERADOS
	
	*/
	static int repeats =500;
//THIS COMMENTS ARE WHAT I WAS USING FOR REFERENCE OF VARIABLES AND FUNCTION, PLS IGNORE	
/*
 * w= weights[][]
 * x= inputs[]
 * b= sesgo/bias[]
 * y= salida q puede ser valor fraccionario entre 0 y 1[]
 * y=(xa[i]*weight[i]+b) --> y= 1/(1+ Math.exp(xa[i]*weight[i]+b));
 * expectedOutp=??
 * actualizar pesos====> w[]=w[] + ??
 * errorTotal= Math.abs(expectedOutp - y);
 * gradiente= delta
 * yd= correctos //expected output
 * */
//THIS COMMENTS ARE WHAT I WAS USING FOR REFERENCE OF VARIABLES AND FUNCTION, PLS IGNORE  
	
	
public static void main (String[]args) {
		//x array filled with random values
        for(int i=0;i<NUM_INSTANCES;++i){
            x[i] = randomNumber(1, 0);
        }
        
        int ciclo=0;
        
        do {
        	ciclo++;
        	//expOutput(weight1, weight2);
        	//clasification(weight1, weight2);
        	training(weight1, weight2, expOutput);
        }while(ciclo!=repeats);
}

	
	
	public static double[] expOutput(double weight1[][], double weight2[][]) {
	System.out.println("SALIDA ESPERADA");
	double sumResult = 0;
	for(int j=0; j<h1.length; j++) {
		sumResult = bias1[j]; 
		for(int i=0; i<weight1.length; i++) {
			//SUMA
			sumResult = sumResult+ weight1[i][j] * x[j];
			
			//ALMACENA LAS SALIDAS (y)
			h1[i]=1/(1+ Math.exp(-sumResult));
			//System.out.println("SALIDAS: "+ outputs[i]);
		}
	}
	
	
	
	for(int j=0; j<expOutput.length; j++) {
		sumResult = bias2[j];
		for(int i=0; i<h1.length ; i++) {
			//SUMA
			sumResult = sumResult+ weight2[j][i] * h1[j];
			//System.out.println("suma: "+sumResult);
			//ALMACENA LAS SALIDAS (y)
			expOutput[i]=1/(1+ Math.exp(-sumResult));
			System.out.println(expOutput[i]);
		}
	}
	System.out.println("\n");
	return outputs;//(matriz y)
}

	//clasification function
	public static double[] clasification(double weight1[][], double weight2[][]) {
		System.out.println("CLASIFICACIÓN");
		double sumResult = 0;
		for(int j=0; j<h1.length; j++) {
			sumResult = bias1[j];
			for(int i=0; i<weight1.length; i++) {
				//SUMA
				sumResult = sumResult+ weight1[i][j] * x[j];
				
				//ALMACENA LAS SALIDAS (y)
				h1[i]=1/(1+ Math.exp(-sumResult));
				//System.out.println("SALIDAS: "+ outputs[i]);
			}
		}
		
		System.out.println("SALIDAS");
		for(int j=0; j<outputs.length; j++) {
			sumResult = bias2[j];
			for(int i=0; i<h1.length ; i++) {
				//SUMA
				sumResult = sumResult+ weight2[j][i] * h1[j];
				//System.out.println("suma: "+sumResult);
				//ALMACENA LAS SALIDAS (y)
				outputs[i]=1/(1+ Math.exp(-sumResult));
				System.out.println(outputs[i]);
			}
		}
		System.out.println("\n");
		return outputs;//(matriz y)
	}

	
	//Training function
	public static void training(double weight1[][], double weight2[][], double[] expOutput) {
		double[] clasif= new double[NUM_INSTANCES];
		clasif=clasification(weight1, weight2);//y
		double error=0, SEerror=0;
		double derivada;
		
//Error
		for (int x=0; x < clasif.length; x++) {
			  
			  error= expOutput[x] - clasif[x];
			 // derivada=clasif[x]*(1-clasif[x]);//derivada
			  derivada = derivada(clasif[x]);
			  System.out.println("Error:"+error+" ");
			
			  //Actualización 
			  delta[x]= error*derivada;
			  SEerror= SEerror + (error*error);
			  System.out.println("Delta:"+delta[x]+" ");
			  System.out.println("First weight");
		}
//error update	
		for (int i = 0; i < clasif.length; i++) {
			error=0;
			for(int j=0; j<h1.length;j++) {
				error= delta[j]*weight2[i][j];	
				dh[j]= error*derivada(h1[i]);
			}
		}
//BIAS
		for(int i = 0; i<bias1.length; i++){
			bias1[i] = bias1[i] + learningRate * x[i];
			bias2[i] = bias2[i] + learningRate * delta[i];
        }
		
//weight 2 update
		for(int i = 0; i < clasif.length; i++){
            for(int j = 0; j < h1.length; j++){
                weight2[i][j]= weight2[i][j] + learningRate * h1[j] * delta[j];
                System.out.println("PESO 2: "+ weight2[i][j]);
            }
        }

//weight 1 update
        for(int i = 0; i < h1.length; i++){
            for(int j = 0; j < clasif.length; j++){
                weight1[i][j] =weight1[i][j] + learningRate * clasif[j] * h1[j];
                System.out.println("PESO 1: "+ weight1[i][j]);
            }
        }
		
		
	}
	
	//derivada
	public static double derivada(double x) {
		double derivadaRes =x*(1-x);//derivada
		return derivadaRes;
	}
	
	//random generator
	public static double randomNumber(double min, double max){
        double d = min+Math.random()*(max-min);
        return d;
    }
}
