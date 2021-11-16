/*
 * Mauricio Sawicki
 */
package PrimerParcial.Comedor;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Comedor {

    private Semaphore semEntradaGato, semSalidaGato, semEntradaPerro, semSalidaPerro;
    private boolean perrosComiendo;
    private int capActual, capMax, cantPerrosEsperando, cantGatosEsperando, perrosComidos = 0, gatosComidos = 0;

    public Comedor(int capacidadMax) {
        this.capMax = capacidadMax;
        this.capActual = 0;
        this.cantPerrosEsperando = 0;
        this.cantGatosEsperando = 0;

        this.perrosComiendo = true;
//        this.semPrimeroEnLlegar = new Semaphore(1, true);
//        this.semTomarComedorPerro = new Semaphore(1, true);
//        this.semTomarComedorGato = new Semaphore(1, true);

        this.semEntradaGato = new Semaphore(0, true);
        this.semEntradaPerro = new Semaphore(0, true);
        this.semSalidaGato = new Semaphore(1, true);
        this.semSalidaPerro = new Semaphore(1, true);
    }


    public void quienEntra(int gatoOPerro) {
        if (gatoOPerro == 1) { // 1 son los perros
            System.out.println("Los perros llegaron primero, comen primero.");
            this.semEntradaPerro.release();
        } else { // 0 son los gatos
            System.out.println("Los gatos llegaron primero, comen primero.");
            perrosComiendo = false;
            this.semEntradaGato.release();
        }
    }

    public void entrarPerro(String nombre) throws InterruptedException { //devolver boolean para saber si pudo entrar a comer o no
        semEntradaPerro.acquire();

        if (this.perrosComiendo) {
            System.out.println("Estan comiendo los perros " + nombre + " puede entrar!");
            this.capActual++;
            this.perrosComidos++;
            System.out.println(nombre + " empieza a comer.");

            if (this.capActual == this.capMax) {
                System.out.println(nombre + ": comedor lleno!!");
            } else {
                this.semEntradaPerro.release();
            }
            
        } else {
            cantPerrosEsperando++;
            System.out.println(nombre + ": no puedo entrar porque están comiendo los gatos!");
        }
    }

    public void entrarGato(String nombre) throws InterruptedException {
        semEntradaGato.acquire();

        if (!this.perrosComiendo) {
            System.out.println("Estan comiendo los gatos " + nombre + " puede entrar!");
            this.capActual++;
            this.gatosComidos++;
            System.out.println(nombre + " empieza a comer.");

            if (this.capActual == this.capMax) {
                System.out.println(nombre + ": comedor lleno!!");
            } else {
                this.semEntradaGato.release();
            }
        } else {
            cantGatosEsperando++;
            System.out.println(nombre + ": no puedo entrar porque están comiendo los perros!");
        }
    }

    public void salirGato(String nombre) throws InterruptedException {
        this.semSalidaGato.acquire();
        System.out.println(nombre + " terminó de comer y salió del comedor.");
        this.capActual--;
        
        if (this.capActual == 0 || (gatosComidos > 6 && cantPerrosEsperando > 0)) {
            this.perrosComiendo = true;
            gatosComidos = 0;
            cantPerrosEsperando = 0;
            this.semEntradaPerro.release(); //estamos dejando entrar a un perro, cuando hay gatos comiendo. Revisar.
        }
        this.semSalidaGato.release();
    }

    public void salirPerro(String nombre) throws InterruptedException {
        semSalidaPerro.acquire();
        System.out.println(nombre + " terminó de comer y salió del comedor.");
        capActual--;
        if (this.capActual == 0 || (perrosComidos > 6 && cantGatosEsperando > 0)) {
            this.perrosComiendo = false;
            perrosComidos = 0;
            cantGatosEsperando = 0;
            this.semEntradaGato.release();
        }
        semSalidaPerro.release();
    }
}
