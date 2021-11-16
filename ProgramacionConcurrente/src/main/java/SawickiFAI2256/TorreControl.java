/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SawickiFAI2256;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio Sawicki FAI-2256
 */
public class TorreControl implements Runnable {

    private Pista pista;
    private String nombre;

    public TorreControl(Pista unaPista, String nom) {
        this.pista = unaPista;
        this.nombre = nom;
    }

    public void run() {
        while (true) {
            this.activarSistemas(nombre);
           // if(pista.controlarAterrizajes()){
        }
    }

    public void activarSistemas(String nom) {

        try {
            System.out.println(nom + ": activando sistemas de control");
            Thread.sleep(2500);
            System.out.println(nom + ": sistemas de control activados!");
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
