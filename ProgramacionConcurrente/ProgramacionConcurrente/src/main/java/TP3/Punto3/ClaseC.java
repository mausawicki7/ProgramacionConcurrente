package TP3.Punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class ClaseC implements Runnable {

    Variable x;

    public ClaseC(Variable x) {
        this.x = x;
    }

    @Override
    public void run() {

      for (int i = 0; i <= 10; i++) {

            if (x.getValor() == 2) {
                System.out.print("CCC");
                x.setValor(0);
            }

            try {
                Thread.sleep((int)(Math.random()*1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(ClaseA.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
      
    }
}
