/*
 * Mauricio Sawicki
 */
package ejTeoriaFilosofosCenando;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Filosofo implements Runnable {

    private String nombre;
    private Mesa laMesa;
    private Tenedor derecho;
    private Tenedor izquierdo;
    private boolean esZurdo;

    public Filosofo(String nombre, Mesa laMesa, Tenedor derecho, Tenedor izquierdo, boolean esZurdo) {
        this.nombre = nombre;
        this.laMesa = laMesa;
        this.derecho = derecho;
        this.izquierdo = izquierdo;
        this.esZurdo = esZurdo;
    }

    public void run() {
        while (true) {
            try {
                this.pensar();
                this.comer();
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void pensar() throws InterruptedException {
        this.pasarElTiempo(); // *filosofo pensando*
    }

    public void comer() {
        agarrarTenedor();
        int tiempoComiendo = randomHasta(8);
        System.out.println(Thread.currentThread().getName() + " comió durante " + tiempoComiendo + " milisegundos.");
        this.pasarElTiempo();
        soltarTenedor();
    }

    private void agarrarTenedor() {
        if (esZurdo) {
            izquierdo.agarrar();
            derecho.agarrar();
        } else {
            derecho.agarrar();
            izquierdo.agarrar();
        }
    }

    private void soltarTenedor() {
        if (esZurdo) {
            izquierdo.soltar();
            derecho.soltar();
        } else {
            derecho.soltar();
            izquierdo.soltar();
        }
    }

    private void pasarElTiempo() {
        try {
            Thread.sleep(randomHasta(5));
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int randomHasta(int maximo) {
        Random rand = new Random();
        return ((rand.nextInt(maximo)) + 1) * 1000;
    }

}
