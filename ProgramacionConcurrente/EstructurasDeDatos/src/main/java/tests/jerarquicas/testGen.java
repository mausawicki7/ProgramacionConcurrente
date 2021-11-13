/*
 * Mauricio Sawicki
 */
package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 *
 * @author mausa
 */
public class testGen {

    public static void main(String args[]) {
        testGen();
    }

    public static void testGen() {
        boolean res;


        ArbolGen arbolito = new ArbolGen();
        
        arbolito.insertar(91, 0);
        arbolito.insertar(52, 91);
        arbolito.insertar(92, 91);
        arbolito.insertar(65, 91);
        
        arbolito.insertar(71, 52);
        arbolito.insertar(93, 52);
        
        arbolito.insertar(26, 92);
        arbolito.insertar(23, 92);
        arbolito.insertar(31, 92);
        
        arbolito.insertar(88, 23);
        
        arbolito.insertar(14, 65);
        
        
        System.out.println(arbolito.toString());
        System.out.println(arbolito.insertarNieto(92,23,89));
        System.out.println(arbolito.toString());

    }

}
