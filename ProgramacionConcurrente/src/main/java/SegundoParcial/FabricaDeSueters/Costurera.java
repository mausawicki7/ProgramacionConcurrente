/*
 * Mauricio Sawicki
 */
package SegundoParcial.FabricaDeSueters;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Costurera extends Persona implements Runnable {

    private TallerCostura taller;
    private int miNumero;

    public Costurera(TallerCostura unTaller, String nombre, int unNumero) {
        super(nombre);
        taller = unTaller;
        miNumero = unNumero;
    }

    public void run() {
        while (true) {
            this.fabricarPieza();
        }
    }

    public void fabricarPieza() {
        switch (miNumero) {
            case 1:
                System.out.println(nombre + ": Intento fabricar una manga");
                 {
                    try {
                        taller.fabricarManga(nombre);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Costurera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.simularTiempoFabricacion();
                taller.terminarManga(nombre);
                System.out.println(nombre + ": Terminé de fabricar una manga");
                break;

            case 2:
                System.out.println(nombre + ": Intento fabricar un cuerpo");
                 {
                    try {
                        taller.fabricarCuerpo(nombre);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Costurera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.simularTiempoFabricacion();
                taller.terminarCuerpo(nombre);
                System.out.println(nombre + ": Terminé de fabricar un cuerpo");
                break;

            case 3:
                System.out.println(nombre + ": Intento ensamblar un sueter");
                 {
                    try {
                        taller.ensamblarSueter(nombre);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Costurera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.simularTiempoFabricacion();
                taller.terminarSueter(nombre);
                System.out.println(nombre + ": Terminé de fabricar un sueter");
                break;

            default:
                throw new AssertionError();
        }
    }

    public void simularTiempoFabricacion() {
        try {
            Thread.sleep((int) (Math.random() * 200));
        } catch (InterruptedException ex) {
            Logger.getLogger(Costurera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
