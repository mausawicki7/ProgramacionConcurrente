/*
 * Mauricio Sawicki
 */
package Sawicki_FAI2256.Ejercicio1;

/**
 *
 * @author mausa
 */
public class Test {
    public static void main(String[] args) {
        Mirador mirador = new Mirador();
        int cantVisitantes = 6;

        Encargado e = new Encargado(mirador);
        Thread enc = new Thread(e, "Encargado");
        enc.start();

        for (int i = 1; i <= cantVisitantes; i++) {
            Visitante v = new Visitante(mirador);
            Thread vis = new Thread(v, "Visitante " + i);
            vis.start();
        }
    }
}
