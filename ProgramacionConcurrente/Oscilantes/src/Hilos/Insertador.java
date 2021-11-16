/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import Compartido.Buffer;

/**
 *
 * @author Fernando
 */
public class Insertador implements Runnable {

    private Buffer compartido;
    private int elemento;

    public Insertador(Buffer res, int elem) {
        this.compartido = res;
        this.elemento = elem;
    }

    public void run() {
        while (true) {
            this.compartido.ingresarElemento(elemento);
            this.elemento++;
            this.procesar();
        }
    }

    private void procesar() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

}
