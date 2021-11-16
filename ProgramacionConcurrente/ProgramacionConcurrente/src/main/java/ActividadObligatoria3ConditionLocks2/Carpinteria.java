/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
 */
package ActividadObligatoria3ConditionLocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Carpinteria {

    private final Lock carpinteros = new ReentrantLock();
    //private final Lock carpinteros2 = new ReentrantLock();
    //private final Lock carpinteros3 = new ReentrantLock();
    //private final Lock ensambladores = new ReentrantLock();
    private final Condition bufferEspera1, bufferEspera2, bufferEspera3, bufferEsperaEnsambladores;
    private int cantPiezas1, cantPiezas2, cantPiezas3, totalPiezas;
    private final int cantMueblesMax;
    private int cantActualMuebles = 0;

    public Carpinteria(int cantidadMuebles) {
        this.cantMueblesMax = cantidadMuebles;
        this.cantPiezas1 = 0;
        this.cantPiezas2 = 0;
        this.cantPiezas3 = 0;
        this.totalPiezas = 0;
        this.bufferEspera1 = carpinteros.newCondition();
        this.bufferEspera2 = carpinteros.newCondition();
        this.bufferEspera3 = carpinteros.newCondition();
        this.bufferEsperaEnsambladores = carpinteros.newCondition();
    }

    //Métodos de los carpinteros que fabrican piezas
    public void fabricarParte1(String nombre) throws InterruptedException {
        while (cantActualMuebles < cantMueblesMax) {
            try {
                carpinteros.lock();
                while (cantPiezas1 > 0) {
                    System.out.println(nombre + ": ya hay una pieza en mi buffer, espero a que la tome el ensamblador..");
                    bufferEspera1.await(); //Si ya fabriqué una pieza1 espero
                }

                if (cantActualMuebles < cantMueblesMax) {
                    System.out.println(nombre + ": agregando pieza 1..");
                    cantPiezas1++;
                    totalPiezas++;

                    if (totalPiezas == 3) {
                        //ensambladores.lock();
                        bufferEsperaEnsambladores.signal();
                    }
                }
            } finally {
                //ensambladores.unlock();
                carpinteros.unlock();
            }
        }
    }

    public void fabricarParte2(String nombre) throws InterruptedException {
        while (cantActualMuebles < cantMueblesMax) {
            try {
                carpinteros.lock();
                while (cantPiezas2 > 0) {
                    System.out.println(nombre + ": ya hay una pieza en mi buffer, espero a que la tome el ensamblador..");
                    bufferEspera2.await(); //Si ya fabriqué una pieza2 espero
                }

                if (cantActualMuebles < cantMueblesMax) {
                    System.out.println(nombre + ": agregando pieza 2..");
                    cantPiezas2++;
                    totalPiezas++;

                    if (totalPiezas == 3) {
                        // ensambladores.lock();
                        bufferEsperaEnsambladores.signal();
                    }
                }
            } finally {
                //ensambladores.unlock();
                carpinteros.unlock();
            }
        }
    }

    public void fabricarParte3(String nombre) throws InterruptedException {
        while (cantActualMuebles < cantMueblesMax) {
            try {
                carpinteros.lock();
                while (cantPiezas3 > 0) {
                    System.out.println(nombre + ": ya hay una pieza en mi buffer, espero a que la tome el ensamblador..");
                    bufferEspera3.await(); //Si ya fabriqué una pieza3 espero
                }

                if (cantActualMuebles < cantMueblesMax) {
                    System.out.println(nombre + ": agregando pieza 3..");
                    cantPiezas3++;
                    totalPiezas++;

                    if (totalPiezas == 3) {
                        //ensambladores.lock();
                        bufferEsperaEnsambladores.signal();
                    }
                }
            } finally {
                //ensambladores.unlock();
                carpinteros.unlock();
            }
        }
    }

    //Métodos de los carpinteros ensambladores
    public void ensamblar(String nombre) throws InterruptedException {
        while (cantActualMuebles < cantMueblesMax) {
            try {
                carpinteros.lock();
                while (totalPiezas < 3) {
                    System.out.println(nombre + ": Aún no están las 3 piezas necesarias para ensamblar el mueble..");
                    bufferEsperaEnsambladores.await();
                }

                if (cantActualMuebles < cantMueblesMax) {
                    cantActualMuebles++;
                    System.out.println(nombre + ": Tengo las 3 piezas! Ensamblando mueble.." + " cantActualMuebles: " + cantActualMuebles);
                    cantPiezas1--;
                    cantPiezas2--;
                    cantPiezas3--;
                    totalPiezas = 0;
                    bufferEspera1.signal();
                    bufferEspera2.signal();
                    bufferEspera3.signal();
                }

                if (cantActualMuebles == cantMueblesMax) {
                    totalPiezas = 3;
                    bufferEspera1.signalAll();
                    bufferEspera2.signalAll();
                    bufferEspera3.signalAll();
                    bufferEsperaEnsambladores.signalAll();
                }

            } finally {
                carpinteros.unlock();
            }
        }
    }
}
