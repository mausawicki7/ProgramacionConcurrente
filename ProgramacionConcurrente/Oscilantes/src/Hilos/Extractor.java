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
public class Extractor implements Runnable {

    private Buffer compartido;

    public Extractor(Buffer res) {
        this.compartido = res;
    }

    public void run() {
        while (true) {
            this.compartido.sacarElemento();
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
