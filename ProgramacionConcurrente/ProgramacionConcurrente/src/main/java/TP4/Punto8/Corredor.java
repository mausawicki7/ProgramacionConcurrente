package TP4.Punto8;

import java.util.Random;

/**
 *
 * @author mausa
 */
public class Corredor implements Runnable {

    private int turno;
    private Testigo testigo;

    public Corredor(int num, Testigo t) {
        this.turno = num;
        this.testigo = t;
    }

    @Override
    public void run() {

        //Si es mi turno, empezar a correr
        this.testigo.empezarACorrer(turno);
        this.correr();
        this.testigo.terminarDeCorrer(turno);

    }

    private void correr() {
        Random r = new Random();
        long tiempoF, tiempoInicial = System.currentTimeMillis() / 1000;
        try {
            System.out.println(Thread.currentThread().getName() + " empezó a correr!" + " arranca en " + tiempoInicial );
            Thread.sleep((r.nextInt(2) + 9) * 200);
            tiempoF = System.currentTimeMillis() / 1000;
            System.out.println(Thread.currentThread().getName() + " Finalizo carrera con tiempo -->" + (tiempoF - tiempoInicial) + " segundos");
        } catch (Exception e) {
        }

    }

}
