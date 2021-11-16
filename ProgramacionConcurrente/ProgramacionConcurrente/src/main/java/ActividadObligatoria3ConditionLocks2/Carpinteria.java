/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
 */
package ActividadObligatoria3ConditionLocks2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Carpinteria {

    private final Lock carpinteros1 = new ReentrantLock();
    private final Lock carpinteros2 = new ReentrantLock();
    private final Lock carpinteros3 = new ReentrantLock();
    private final Lock ensambladores = new ReentrantLock();
    private final Lock mutex = new ReentrantLock();
    private final Condition bufferEspera1, bufferEspera2, bufferEspera3, bufferEsperaEnsambladores, esperaAlEnsamblador;
    private int cantPiezas1, cantPiezas2, cantPiezas3;
    private final int cantMueblesMax;
    private int cantActualMuebles = 0;
    private boolean puedeEnsamblar = false, fabricarPieza1 = false, fabricarPieza2 = false, fabricarPieza3 = false;

    public Carpinteria(int cantidadMuebles) {
        this.cantMueblesMax = cantidadMuebles;
        this.cantPiezas1 = 0;
        this.cantPiezas2 = 0;
        this.cantPiezas3 = 0;
        this.bufferEspera1 = carpinteros1.newCondition();
        this.bufferEspera2 = carpinteros2.newCondition();
        this.bufferEspera3 = carpinteros3.newCondition();
        this.bufferEsperaEnsambladores = ensambladores.newCondition();
        this.esperaAlEnsamblador = ensambladores.newCondition();
    }

    //Métodos de los carpinteros que fabrican piezas
    public void fabricarParte1(String nombre) throws InterruptedException {
        while (cantActualMuebles < cantMueblesMax) {
            try {
                carpinteros1.lock();
                while (cantPiezas1 > 0) {
                    System.out.println(nombre + ": ya hay una pieza en mi buffer, espero a que la tome el ensamblador..");
                    bufferEspera1.await(); //Si ya fabriqué una pieza1 espero
                }

                if (cantActualMuebles < cantMueblesMax) {
                    System.out.println(nombre + ": agregando pieza 1..");
                    cantPiezas1++;
                    fabricarPieza1 = false; //Puse mi pieza en el buffer, entonces no fabrico más hasta que el ensamblador me avise         
                }

            } finally {
                ensambladores.lock(); //Agarro el lock de ensambladores para poder despertar a los ensambladores 
                carpinteros1.unlock();
                despertarEnsamblador(); //Si corresponde, despierto a un ensamblador
                while (!fabricarPieza1) {
                    System.out.println(nombre + ": fabriqué la pieza, espero..");
                    esperaAlEnsamblador.await(); //Si ya le entregue la pieza al ensamblador, espero
                }
            }
        }
    }

    public void fabricarParte2(String nombre) throws InterruptedException {
        while (cantActualMuebles < cantMueblesMax) {
            try {
                carpinteros2.lock();
                while (cantPiezas2 > 0) {
                    System.out.println(nombre + ": ya hay una pieza en mi buffer, espero a que la tome el ensamblador..");
                    bufferEspera2.await(); //Si ya fabriqué una pieza2 espero
                }

                if (cantActualMuebles < cantMueblesMax) {
                    System.out.println(nombre + ": agregando pieza 2..");
                    cantPiezas2++;
                    fabricarPieza2 = false; //Puse mi pieza en el buffer, entonces no fabrico más hasta que el ensamblador me avise  

                }
            } finally {
                ensambladores.lock(); //Agarro el lock de ensambladores para poder despertar a los ensambladores 
                carpinteros2.unlock();
                despertarEnsamblador(); //Si corresponde, despierto a un ensamblador
                while (!fabricarPieza2) {
                    System.out.println(nombre + ": fabriqué la pieza, espero..");
                    esperaAlEnsamblador.await(); //Si ya le entregue la pieza al ensamblador, espero
                }
            }
        }
    }

    public void fabricarParte3(String nombre) throws InterruptedException {
        while (cantActualMuebles < cantMueblesMax) {
            try {
                carpinteros3.lock();
                while (cantPiezas3 > 0) {
                    System.out.println(nombre + ": ya hay una pieza en mi buffer, espero a que la tome el ensamblador..");
                    bufferEspera3.await(); //Si ya fabriqué una pieza3 espero
                }

                if (cantActualMuebles < cantMueblesMax) {
                    System.out.println(nombre + ": agregando pieza 3..");
                    cantPiezas3++;
                    fabricarPieza3 = false; //Puse mi pieza en el buffer, entonces no fabrico más hasta que el ensamblador me avise  

                }
            } finally {
                ensambladores.lock(); //Agarro el lock de ensambladores para poder despertar a los ensambladores 
                carpinteros3.unlock();
                despertarEnsamblador(); //Si corresponde, despierto a un ensamblador
                while (!fabricarPieza3) {
                    System.out.println(nombre + ": fabriqué la pieza, espero..");
                    esperaAlEnsamblador.await(); //Si ya le entregue la pieza al ensamblador, me duermo
                }
            }
        }
    }

    //Métodos de los carpinteros ensambladores
    public void ensamblar(String nombre) throws InterruptedException {
        while (cantActualMuebles < cantMueblesMax) {
            try {
                ensambladores.lock();
                while (!puedeEnsamblar) {
                    System.out.println(nombre + ": Aún no están las 3 piezas necesarias para ensamblar el mueble..");
                    bufferEsperaEnsambladores.await();
                }

                if (cantActualMuebles < cantMueblesMax) {
                    System.out.println(nombre + ": Tengo las 3 piezas! Ensamblando mueble..");
                    decrementarBuffer();
                    System.out.println("Muebles ensamblados: " + cantActualMuebles);          
                    esperaAlEnsamblador.signalAll();
                }
                finalizarTrabajoCarpinteros();
                puedeEnsamblar = false;
            } finally {
                ensambladores.unlock();
            }
        }
    }

    public void finalizarTrabajoCarpinteros() {
        if (cantActualMuebles == cantMueblesMax) {
            
            carpinteros1.lock();
            bufferEspera1.signalAll();
            carpinteros1.unlock();
            
            carpinteros2.lock();
            bufferEspera2.signalAll();
            carpinteros2.unlock();
            
            carpinteros3.lock();
            bufferEspera3.signalAll();
            carpinteros3.unlock();
            
            bufferEsperaEnsambladores.signalAll();
        }
    }

    public void despertarEnsamblador() throws InterruptedException {
        try {
            mutex.lock();
            if (cantPiezas1 + cantPiezas2 + cantPiezas3 == 3) { //Si estan listas las 3 piezas necesarias para ensamblar el mueble, se le avisa al ensamblador
                System.out.println("Están las 3 piezas! Se le avisa al ensamblador");
                puedeEnsamblar = true;
                bufferEsperaEnsambladores.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void decrementarBuffer() throws InterruptedException {
        try {
            mutex.lock();
            cantActualMuebles++;
            cantPiezas1--;
            cantPiezas2--;
            cantPiezas3--;
            fabricarPieza1 = true;
            fabricarPieza2 = true;
            fabricarPieza3 = true;
        } finally {
            mutex.unlock();
        }
    }
}
