/*
 * Mauricio Sawicki
 */
package TP6.CentroDeImpresion;

/**
 *
 * @author mausa
 */
public class Main {
       public static void main(String[] args) {
       int cantA = 5, cantB = 7;
           
        CentroDeImpresion centro = new CentroDeImpresion(cantA, cantB);
 
        for (int j = 1; j <= 8; j++) {
            Usuario user = new Usuario(j, centro);
            Thread hiloUsuarios = new Thread(user, "Usuario " +j);
            hiloUsuarios.start();
        }
        
    }
}
