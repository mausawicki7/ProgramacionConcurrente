/*
 * Mauricio Sawicki
 */
package ejTeoriaFilosofosCenando;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mausa
 */
//Recurso compartido por los filosofos
public class Tenedor {

    private ReentrantLock mutex;
    private boolean tenedorTomado;

    public Tenedor() {
        this.mutex = new ReentrantLock();
    }

    public void agarrar() {
        mutex.lock();
         tenedorTomado = true;
    }

    public void soltar() {
        if(tenedorTomado)
        mutex.unlock();
         tenedorTomado = false;
    }
   
}
