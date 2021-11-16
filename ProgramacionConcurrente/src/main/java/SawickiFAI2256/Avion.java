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
public class Avion implements Runnable {

    TorreControl pista;    //Recurso compartido
    String nombre;

    public Avion(TorreControl unaTorre, String nomAvion) {
        this.pista = unaTorre;
        this.nombre = nomAvion;
    }

    public void run() {
        while (true) {
         //   try {

                //this.volar(nombre);
             //   pista.empezarAterrizaje(nombre);
           //     this.tiempoAterrizaje(nombre);
              //  pista.terminarAterrizaje(nombre);

                //Punto (a)
                // pista.despegarA(nombre);
                //    System.out.println("Soy el avión " + nombre + " ya despegué.");
                //Punto (b)
                //if (pista.despegarB(nombre)) {
                    System.out.println("Soy el avión " + nombre + " ya despegué.");
             //   }

          //  } catch (InterruptedException ex) {
           //     Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
           // }
       // }

    }

//    public void tiempoAterrizaje(String nom) {
//
//        try {
//            System.out.println(nom + ": estoy aterrizando!!");
//            Thread.sleep(2000);
//
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    public void volar(String nom) {
//
//        try {
//            System.out.println(nom + ": estoy volando.");
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
//        }
 }
}