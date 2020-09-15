package TP3.Punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class ClaseB implements Runnable {

    Variable x;

    public ClaseB(Variable x) {
        this.x = x;
    }

    @Override
    public void run() {

        for (int i = 0; i <= 10; i++) {

            if (x.getValor() == 1) {
                System.out.print("BB");
                x.setValor(2);
            }

            try {
                Thread.sleep((int)(Math.random()*1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(ClaseA.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
