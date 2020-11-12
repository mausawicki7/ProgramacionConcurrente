/*
 * Mauricio Sawicki
 */
package TP6.SalaMuseo;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int jubilados = 30;

        GestorDeSala sala = new GestorDeSala(50, 20);

        for (int i = 1; i <= 50; i++) {
            Persona p = new Persona(sala, "Persona " + i);
            Thread t1 = new Thread(p, "Persona " + i);
            t1.start();
        }

        MedidorTemperatura h = new MedidorTemperatura(sala);
        Thread h1 = new Thread(h);
        h1.start();

        for (int j = 1; j <= jubilados; j++) {
            Jubilado jb = new Jubilado(sala, "Jubilado " + j);
            Thread t2 = new Thread(jb, "Jubilado " + j);
            t2.start();
        }
    }

}
