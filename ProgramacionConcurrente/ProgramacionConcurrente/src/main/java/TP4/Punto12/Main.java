/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto12;

/**
 *
 * @author mausa
 */
public class Main {
    public static void main(String [] args){
        Jaula j1 = new Jaula();
        String [] arrayHamsters = {"Ricky", "Bob", "Stuart", "Wendy", "Sara", "Mailo"};
    
        Thread [] hiloHamster = new Thread[6];
        for(int i = 0; i<hiloHamster.length; i++){
            Hamster unHamster = new Hamster(arrayHamsters[i], j1);
            hiloHamster[i] = new Thread(unHamster);
            hiloHamster[i].start();
        }    
      
    
    }
    
    
}
