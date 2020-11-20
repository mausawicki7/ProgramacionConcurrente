/*
 * Mauricio Sawicki
 */
package TP6.CentroHomoterapia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mausa
 */
public class Centro {

    private int camillasDisponibles;
    private int camillasOcupadas = 0;

    private int revistasDisponibles;
    private int leyendo = 0;

    private int sillasDisponibles;
    private int sentados = 0;

    private int capacidadMaxima;
    private int capacidadActual = 0;

    private Lock lock = new ReentrantLock(true);
    private Condition hayLugar = lock.newCondition();
    private Condition revista = lock.newCondition();
    private Condition hayCamillas = lock.newCondition();

    public Centro(int camillasDisponibles, int revistasDisponibles, int sillasDisponibles, int capacidadMaxima) {
        this.camillasDisponibles = camillasDisponibles;
        this.revistasDisponibles = revistasDisponibles;
        this.sillasDisponibles = sillasDisponibles;
        this.capacidadMaxima = capacidadMaxima;

    }

    public void entrar() {

        lock.lock();
        try {

            //Si no hay lugar espera
            while (capacidadActual >= capacidadMaxima) {

                hayLugar.await();

            }

            System.out.println(Thread.currentThread().getName() + " entró al centro..");
            capacidadActual++;

        } catch (InterruptedException ex) {
            Logger.getLogger(Centro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            lock.unlock();
        }

    }

    public boolean sentarse() {

        lock.lock();
        boolean seSento = false;
        /*Si hay silla se sienta, si no se queda parado (para no bloquear un paciente 
        esperando una silla y que siga con el flujo de ejecución) */
        if (sentados < sillasDisponibles) {

            System.out.println(Thread.currentThread().getName() + " se sentó..");
            sentados++;
            seSento = true;

        } else {

            System.out.println(Thread.currentThread().getName() + " no consiguió silla y se quedó parado..");

        }

        lock.unlock();

        return seSento;

    }

    public void tomarRevista() {

        lock.lock();

        try {

            while (leyendo >= revistasDisponibles) {

                revista.await();

            }

            leyendo++;

        } catch (InterruptedException ex) {
            Logger.getLogger(Centro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            lock.unlock();
        }
    }

    public void soltarRevista() {

        lock.lock();

        System.out.println(Thread.currentThread().getName() + " dejó la revista..");
        leyendo--;
        revista.signalAll();

        lock.unlock();

    }

    public void entrarExtraccion(boolean sentado) {

        lock.lock();

        try {
            while (camillasOcupadas >= camillasDisponibles) {

                hayCamillas.await();

            }

            if (sentado) {

                sentados--;

            }

            capacidadActual--;
            hayLugar.signalAll();

            camillasOcupadas++;
            System.out.println(Thread.currentThread().getName() + " pasó a hacerse la extracción..");

        } catch (InterruptedException ex) {
            Logger.getLogger(Centro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            lock.unlock();

        }

    }

    public void salirExtraccion() {

        lock.lock();

        camillasOcupadas--;
        System.out.println(Thread.currentThread().getName() + " salió de la sala");
        hayCamillas.signal();

        lock.unlock();

    }

}
