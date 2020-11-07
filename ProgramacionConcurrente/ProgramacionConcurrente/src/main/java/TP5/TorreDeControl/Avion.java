package TP5.TorreDeControl;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Avion implements Runnable {

    private TorreDeControl torre;
    private int despegaOAterriza;

    public Avion(TorreDeControl unaTorre, int opcion) {
        this.torre = unaTorre;
        this.despegaOAterriza = opcion;
    }

    public void run() {
        //despegaOAterriza = 1 el avion esta en el cielo
        //despegaOAterriza = 2 el avion esta en tierra
        switch (despegaOAterriza) {
            case 1:
            {
                try {
                    torre.solicitarPermisoParaAterrizar();
                    torre.usarPista();
                    torre.aterrizar();
                break;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case 2:
            {
                try {
                    torre.solicitarPermisoParaDespegar();
                    torre.usarPista();
                    torre.despegar();
                break;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


        }

    }
    
}
