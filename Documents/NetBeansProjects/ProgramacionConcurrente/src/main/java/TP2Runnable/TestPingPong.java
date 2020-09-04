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
public class TestPingPong {
    
        public static void main(String[] args) {
        PingPong t1 = new PingPong("PING", 33);
        PingPong t2 = new PingPong("PONG", 10);
        
        Thread t = new Thread(t1);
        Thread h = new Thread(t2);

        t.start();
        h.start();

    }
    
}
