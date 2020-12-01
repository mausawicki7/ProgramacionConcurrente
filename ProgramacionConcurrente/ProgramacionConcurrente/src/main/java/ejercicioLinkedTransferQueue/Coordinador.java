/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioLinkedTransferQueue;

/**
 *
 * @author Fernando
 */
public class Coordinador implements Runnable {

    private Rio rio;

    public Coordinador(Rio rio) {
        this.rio = rio;
    }

    public void run() {

        while (true) {
            rio.bajarAuto();
        }
    }

}
