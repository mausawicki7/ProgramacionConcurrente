/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2Runnable;

/**
 *
 * @author mausa
 */
public class testeoRecurso {

    public static void main(String[] args) {
        Cliente c1 = new Cliente();
        Thread juan = new Thread(c1);
        juan.setName("Juan Lopez");
        
        Cliente c2 = new Cliente();
        Thread ines = new Thread(c2);
        ines.setName("Ines Garcia");

        juan.start();
        ines.start();
    }
}
