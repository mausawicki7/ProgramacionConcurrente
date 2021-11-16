/*
 * Mauricio Sawicki
 */
package SegundoParcial.PerrosConHambre;

/**
 *
 * @author mausa
 */
public class Test {

    public static void main(String[] args) {

        int cantPlatos = 5;
        int cantPerros = 6;

        Comedor unComedor = new Comedor(cantPlatos);
        Dueño dueño = new Dueño("Dueño", unComedor);
        Thread hiloDueño = new Thread(dueño);
        hiloDueño.start();

        for (int i = 1; i <= cantPerros; i++) {
            Perro p1 = new Perro(unComedor, "Perro "+i);
            Thread hiloPerros = new Thread(p1);
            hiloPerros.start();
        }
    }
}
