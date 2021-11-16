/*
 * Mauricio Sawicki
 */
package SegundoParcial.FabricaDeSueters;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mausa
 */
public class TallerCostura {

    private Lock costurerasMangas = new ReentrantLock();
    private Lock costurerasCuerpos = new ReentrantLock();
    private Lock costurerasEnsambladoras = new ReentrantLock();
    private int bufferMangas, bufferCuerpos, bufferSueters, bufferCajas, tamanioBufferMangas, tamanioBufferCuerpos;
    private final Condition esperaCosturerasMangas, esperaCosturerasCuerpos, esperaCosturerasEnsambladoras;

    public TallerCostura(int tamBufferMangas, int tamBufferCuerpos) {
        this.tamanioBufferMangas = tamBufferMangas;
        this.tamanioBufferCuerpos = tamBufferCuerpos;
        this.esperaCosturerasMangas = costurerasMangas.newCondition();
        this.esperaCosturerasCuerpos = costurerasCuerpos.newCondition();
        this.esperaCosturerasEnsambladoras = costurerasEnsambladoras.newCondition();
        this.bufferCajas = 0;
        this.bufferSueters = 0;
        this.bufferCuerpos = 0;
        this.bufferMangas = 0;
    }

    public void avisarEnsambladoras() {
        costurerasEnsambladoras.lock();
        esperaCosturerasEnsambladoras.signal();
        costurerasEnsambladoras.unlock();
    }

    public void consumirMangas() {
        try {
            costurerasMangas.lock();
            bufferMangas = bufferMangas - 2;
            esperaCosturerasMangas.signal();
        } finally {
            costurerasMangas.unlock();
        }
    }

    public void consumirCuerpos() {
        try {
            costurerasCuerpos.lock();
            bufferCuerpos--;
            esperaCosturerasCuerpos.signal();
        } finally {
            costurerasCuerpos.unlock();
        }
    }

    public void fabricarManga(String nombre) throws InterruptedException {
        try {
            costurerasMangas.lock();
            while (bufferMangas >= tamanioBufferMangas) { //Buffer lleno
                System.out.println(nombre + ": El buffer de mangas está lleno, espero..");
                esperaCosturerasMangas.await();
            }
        } finally {
            costurerasMangas.unlock();
        }
    }

    public void terminarManga(String nombre) {
        try {
            costurerasMangas.lock();
            bufferMangas++;
            System.out.println(nombre + ": Agregué una manga al buffer de mangas");

            if (bufferMangas > 2) {
                avisarEnsambladoras();
            }

        } finally {
            costurerasMangas.unlock();

        }
    }

    public void fabricarCuerpo(String nombre) throws InterruptedException {
        try {
            costurerasCuerpos.lock();
            while (bufferCuerpos >= tamanioBufferCuerpos) { //Buffer lleno
                System.out.println(nombre + ": El buffer de cuerpos está lleno, espero..");
                esperaCosturerasCuerpos.await();
            }
        } finally {
            costurerasCuerpos.unlock();
        }
    }

    public void terminarCuerpo(String nombre) {
        try {
            costurerasCuerpos.lock();
            bufferCuerpos++;
            System.out.println(nombre + ": Agregué un cuerpo al buffer de cuerpos");

            if (bufferCuerpos > 1) {
                avisarEnsambladoras();
            }

        } finally {
            costurerasCuerpos.unlock();
        }
    }

    public void ensamblarSueter(String nombre) throws InterruptedException {
        try {
            costurerasEnsambladoras.lock();
            while (bufferCuerpos < 1 || bufferMangas < 2) {
                System.out.println(nombre + ": No están las piezas necesarias para ensamblar el sueter, espero..");
                esperaCosturerasEnsambladoras.await();
            }
            consumirMangas();
            consumirCuerpos();

        } finally {
            costurerasEnsambladoras.unlock();
        }

    }

    public void terminarSueter(String nombre) {
        try {
            costurerasEnsambladoras.lock();
            bufferSueters++;
            System.out.println(nombre + ": Ensamblé un sueter! BufferSueters: " + bufferSueters);
            if (bufferSueters == 10) {
                bufferCajas++;
                System.out.println(nombre + ": Se llevan armadas " + bufferCajas + " cajas de sueters");
                bufferSueters = 0;
            }
        } finally {
            costurerasEnsambladoras.unlock();
        }
    }
}
