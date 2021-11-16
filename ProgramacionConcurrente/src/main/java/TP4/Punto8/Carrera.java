package TP4.Punto8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Carrera {

    public static void main(String[] args) {
        
        int cantCorredores = 15;
        Thread[] hiloCorredores = new Thread[cantCorredores];
        Testigo unTestigo = new Testigo(cantCorredores);
        int topeArray = cantCorredores - 1;
        double tiempoInicial = System.currentTimeMillis();
        
        for (int i = 0; i < cantCorredores; i++) { //se crean los corredores (hilos)
            Corredor unCorredor = new Corredor(i, unTestigo); 
            hiloCorredores[i] = new Thread(unCorredor, "Corredor "+i);
        }
        
        for (int i = 0; i < hiloCorredores.length; i++) {
            hiloCorredores[i].start();
        }
        
        for (int i = 0; i <= topeArray; i++) {
            try {
                hiloCorredores[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Carrera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        double tiempoFinal = System.currentTimeMillis();
        System.out.println("Tardaron: " + (tiempoFinal - tiempoInicial) / 1000 + " segundos");
    }
    
        public static int randomHasta(int maximo) {
        Random rand = new Random();

        return rand.nextInt(maximo + 1);
    }
    
}
