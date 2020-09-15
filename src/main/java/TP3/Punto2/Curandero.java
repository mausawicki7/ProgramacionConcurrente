/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3.Punto2;

/**
 *
 * @author mausa
 */
public class Curandero implements Runnable {
    private Jugador objetivo;
    
    public Curandero(Jugador player){
        objetivo = player;
    }
    
    public void run(){
        int vida = objetivo.getVida();
        objetivo.setVida(vida + 3);
    }
}
