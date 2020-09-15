package TP3.Punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class ClaseA implements Runnable {

    Variable x;

    public ClaseA(Variable x) {
        this.x = x;
    }

    @Override
    public void run() {

        for (int i = 0; i <= 10; i++) {

            if (x.getValor() == 0) {
                System.out.print("A");
                x.setValor(1);
            }

            try {
                Thread.sleep((int)(Math.random()*1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(ClaseA.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
