package mausa.formulas;

/**
 *
 * @author RYZEN
 */
public class Formulas {
    public static double sumatoria(Double[] valores){
        //sumatoria simple
        double retorno = 0;
        for(int i=0; i<valores.length; i++){
            retorno+=valores[i];
        }
        return retorno;
    }
    
    public static double sumatoriaCuadrado(Double[] valores){
        double retorno = 0;
        double actual;
        for(int i=0; i<valores.length; i++){
            actual = valores[i];
            retorno+=actual*actual;
        }
        return retorno;
    }
    
    public static double sumatoriaVariables(Double[] valoresA, Double[] valoresB){
        double retorno = 0;
        if(valoresA.length!=valoresB.length){
            retorno = -1; //no tienen la misma longitud
        }else{
          for(int i=0; i<valoresA.length; i++){
            retorno+=valoresA[i]*valoresB[i];
          }  
        }  
        return retorno;
    }
    
    public static double media(Double[] valores){
        double retorno = sumatoria(valores);
        return retorno / valores.length;
    }
    
    public static void main(String[] args){
        Double[] valoresX = {0.86,1.06,2.03,0.67,0.40,0.76,1.12,1.01,0.90,0.57,0.78,0.74,0.13,1.26,1.08,0.42,0.69,0.82};
        Double[] valoresY = {13.6,16.6,23.5,10.2,5.4,9.0,16.3,13.0,14.4,10.0,10.2,9.5,1.5,18.5,12.6,4.9,10.6,14.9};
        System.out.println("SUMATORIA X = "+sumatoria(valoresX));
        System.out.println("SUMATORIA Y = "+sumatoria(valoresY));
        System.out.println("SUMATORIA X^2 = "+sumatoriaCuadrado(valoresX));
        System.out.println("SUMATORIA Y^2 = "+sumatoriaCuadrado(valoresY));
        System.out.println("SUMATORIA XY = "+sumatoriaVariables(valoresX, valoresY));
        System.out.println("MEDIA X = "+media(valoresX));
        System.out.println("MEDIA Y = "+media(valoresY));
    }
}
