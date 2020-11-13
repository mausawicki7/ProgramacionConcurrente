/*
 * Mauricio Sawicki
 */
package TP6.SalaMuseo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int jubilados = 5;

        GestorDeSala sala = new GestorDeSala(10);

        MedidorTemperatura h = new MedidorTemperatura(sala);
        Thread h1 = new Thread(h);
        h1.start();

        for (int j = 1; j <= jubilados; j++) {
            Jubilado jb = new Jubilado(sala, "Jubilado " + j);
            Thread hiloJubilados = new Thread(jb, "Jubilado " + j);
            hiloJubilados.start();
        }
        
        for (int i = 1; i <= 10; i++) {
            Persona p = new Persona(sala, "Persona " + i);
            Thread hiloPersonas = new Thread(p, "Persona " + i);
            hiloPersonas.start();
        }

    }

}
