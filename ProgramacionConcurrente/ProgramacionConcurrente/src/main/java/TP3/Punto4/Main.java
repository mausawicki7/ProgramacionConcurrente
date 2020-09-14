/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3.Punto4;

/**
 *
 * @author mausa
 */
public class Main {
    public static void main(String[]args){
        
        Thread[] autos= new Thread[5];
        Surtidor surt= new Surtidor();
        
        for(int i=0;i<=4;i++){
            autos[i]= new Thread(new Auto((i+1)*100,(i+1)*50,surt),"Auto "+i);
        }
        for(int i=0; i<=4;i++){
            autos[i].start();
        }
    }
}