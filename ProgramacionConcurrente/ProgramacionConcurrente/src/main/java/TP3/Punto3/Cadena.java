/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3.Punto3;

/**
 *
 * @author mausa
 */
public class Cadena implements Runnable {

    private String cadena;
    private Organizador org;

    public Cadena(String cad, Organizador org1) {
        this.cadena = cad;
        this.org = org1;
    }

    @Override
    public void run() {
        //Cuantas veces quiero que imprima las letras
        for (int i = 0; i < 10; i++) {
            
            while (!org.esTurno(cadena)) {   }            
            
            System.out.println(cadena);
            org.siguiente();

        }
    }

    public String getCadena() {
        return this.cadena;
    }

}
