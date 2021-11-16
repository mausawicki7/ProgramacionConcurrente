/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.ComedorGatosyPerros;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Perro implements Runnable {

    private Comedor comedor;

    public Perro(Comedor comedor) {
        this.comedor = comedor;
    }

    public void run() {
        
        try {
            comedor.ingresanPerros();
            comedor.comer();
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

}
