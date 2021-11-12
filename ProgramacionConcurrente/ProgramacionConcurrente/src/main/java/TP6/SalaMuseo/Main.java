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

        int jubilados = 15;

        GestorDeSala sala = new GestorDeSala(50);

        MedidorTemperatura h = new MedidorTemperatura(sala);
        Thread h1 = new Thread(h);
        h1.start();

        for (int j = 1; j <= jubilados; j++) {
            Jubilado jb = new Jubilado(sala, "Jubilado " + j);
            Thread hiloJubilados = new Thread(jb, "Jubilado " + j);
            hiloJubilados.start();
        }
        
        for (int i = 1; i <= 45; i++) {
            Persona p = new Persona(sala, "Persona " + i);
            Thread hiloPersonas = new Thread(p, "Persona " + i);
            hiloPersonas.start();
        }

    }

}
