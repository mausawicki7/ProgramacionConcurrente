/*
 * Mauricio Sawicki
 */
package FilmadoresTraductoresMonitoresLocks;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Socio implements Runnable {

    private Serie serie;
    private boolean verEnCastellano;
    private int capPorVer = 1;

    public Socio(Serie serie, boolean eleccion) {
        this.serie = serie;
        this.verEnCastellano = eleccion;
    }

    @Override
    public void run() {
        while (true) {
            if (verEnCastellano) {
                try {
                    serie.verCapituloEnCastellano(capPorVer);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.simularVerCap();
                this.terminarCap();
            } else {
                try {
                    serie.verCapituloEnIngles(capPorVer);
                    this.simularVerCap();
                    this.terminarCap();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void simularVerCap() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarCap() {
        System.out.println(Thread.currentThread().getName() + " terminé de ver el capítulo " + capPorVer);
        capPorVer++;
    }

}
