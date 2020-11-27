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

    //Turno actual empieza en 1 (pasa el cliente con turno 1)
    private int turnoActual = 1;
    //El turno que se le asignará a cada paciente
    private int turno = 1;

    //Variables pasadas por parámetro (Constructor)
    private int camillasDisponibles;
    private int cantidadSillas;
    private int revistasDisponibles;

    private Lock mutex = new ReentrantLock(true);
    private Condition esperaTurno = mutex.newCondition();

    public Centro(int camillasDisponibles, int cantidadSillas, int cantidadRevistas) {
        this.camillasDisponibles = camillasDisponibles;
        this.cantidadSillas = cantidadSillas;
        this.revistasDisponibles = cantidadRevistas;
    }

    public int entrarSacarTurno() {

        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " entró al centro..");
        //Asigno un turno al paciente e incremento el rollo de los turnos
        int turnoPaciente = turno;
        turno++;
        try {
        } finally {

            mutex.unlock();
        }
        //Se le retorna el turno al paciente para que lo almacene
        return turnoPaciente;
    }

    public boolean esperar(boolean quiereSentarse) {

        mutex.lock();
        boolean seSento = false;
        try {

            //Si hay silla se sienta, si no se queda parado
            if (quiereSentarse && cantidadSillas > 0) {

                System.out.println(Thread.currentThread().getName() + " se sentó..");
                seSento = true;
                cantidadSillas--;

            } else {

                System.out.println(Thread.currentThread().getName() + " no consiguió silla y se quedó parado..");

            }
        } finally {
            mutex.unlock();
        }

        return seSento;

    }

    public boolean tomarCamilla(int turnoPaciente) {
        mutex.lock();
        boolean tomoCamilla = false;
        //Si hay camillas disponibles y es su turno puede pasar a ocupar una camilla
        if (turnoPaciente == turno && camillasDisponibles > 0) {
            System.out.println(Thread.currentThread().getName() + " tomó una camilla.");
            camillasDisponibles--;
            tomoCamilla = true;
            //Es hora de que pase el siguiente paciente por lo tanto aumento el turnero
            turnoActual++;
        }
        try {
        } finally {
            mutex.unlock();
        }
        return tomoCamilla;
    }

    public boolean tomarRevista(int turnoPaciente) {

        mutex.lock();
        boolean tomoRevista = false;

        //Si hay revista disponible la toma
        if (revistasDisponibles > 0) {
            System.out.println(Thread.currentThread().getName() + " tomó una revista.");
            tomoRevista = true;
            revistasDisponibles--;
        }
        try {
            //Espera su turno mientras lee la revista.
            esperaTurno.await();

        } catch (InterruptedException ex) {
            Logger.getLogger(Centro.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //Sino tambien puede esperar pero sin leer la revista
            while (turnoPaciente != turnoActual || camillasDisponibles <= 0) {
                esperaTurno.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Centro.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            mutex.unlock();
        }
        return tomoRevista;
    }

    public void finalizar() {

        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " liberó la camilla");

        //Libera la camilla
        camillasDisponibles++;

        System.out.println("Camillas disponibles: " + camillasDisponibles);
        //Les avisa a los que están esperando su turno (leyendo revista o no)
        esperaTurno.signalAll();

        mutex.unlock();
    }

    public void atenderse(int turnoPaciente, boolean tomoSilla, boolean tomoRevista) {
        mutex.lock();
        //Si habia tomado una revista y/o una silla, las deja
        if (tomoRevista) {
            System.out.println(Thread.currentThread().getName() + " dejó la revista..");
            revistasDisponibles++;
        }

        if (tomoSilla) {
            System.out.println(Thread.currentThread().getName() + " dejó la silla..");
            cantidadSillas++;
        }
        System.out.println(Thread.currentThread().getName() + " pasó a una camilla...");
        camillasDisponibles--;
        turnoActual++;

        mutex.unlock();

    }

}
