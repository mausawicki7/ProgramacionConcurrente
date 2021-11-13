/*
 * Mauricio Sawicki
 */
package tests.conjuntistas;
import conjuntistas.ArbolBB;
import lineales.dinamicas.Cola;

/**
 *
 * @author mausa
 */
public class testBin {
    
    public static void main (String [] args){
        ArbolBB arbol = new ArbolBB();
        Cola q = new Cola();
        
        q.poner('P');
        q.poner('J');
        q.poner('H'); 

        arbol.insertar('G');
        arbol.insertar('E');
        arbol.insertar('P');
        arbol.insertar('A');
        arbol.insertar('F');
        arbol.insertar('J');
        arbol.insertar('X');
        arbol.insertar('H');
        arbol.insertar('M');
        arbol.insertar('I');
        
        
        System.out.println(arbol.controlarCamino(q));
        
        //System.out.println(arbol.toString());
        //System.out.println("Respuesta: "+arbol.concatenarPosOrdenDesde('I', 3));        
        //System.out.println("Respuesta: "+arbol.concatenarPosOrdenDesde('D', 4));

        
        
        
    }
    
}
