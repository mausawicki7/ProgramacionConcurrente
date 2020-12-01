/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioLinkedTransferQueue;

/**
 *
 * @author Fernando Iraira <fmiraira@gmail.com>
 */
public class Auto implements Runnable {

    private Rio rio;
    private String nombre;

    public Auto(Rio rio, String nom) {
        this.rio = rio;
        this.nombre = nom;
    }

    public void run() {
        rio.entrarATransbordador(this);
    }
    
    public String getName(){
        return this.nombre;
    }

}
