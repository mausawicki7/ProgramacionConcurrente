/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioLinkedTransferQueue;

/**
 *
 * @author Fernando Iraira <fmiraira@gmail.com>
 */
public class Transbordador implements Runnable {

    private Rio rio;

    public Transbordador(Rio rio) {
        this.rio = rio;
    }

    public void run() {
        while (true) {
            rio.iniciarViaje();
            this.cruzar();
            rio.volver();
        }
    }

    public void cruzar() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }

}
