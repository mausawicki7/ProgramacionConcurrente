/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjParcialPerrosAlaskaMonitores;

/**
 *
 * @author mausa
 */
public class Main {
    public static void main(String [] args){
        
        int cantPlatos = 5;
        int cantPerros = 6;
        
        Comedero unComedero = new Comedero(cantPlatos);
        Dueño dueño = new Dueño(unComedero);
        Thread hiloDueño = new Thread(dueño, "Dueño: ");
        hiloDueño.start();
        
        
        for (int i = 1; i <= cantPerros; i++) {
            Perro p1 = new Perro(unComedero);
            Thread hiloPerros = new Thread(p1, "Perro "+i);
            hiloPerros.start();
        }
    }
}
