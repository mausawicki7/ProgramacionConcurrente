/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2Thread;

/**
 *
 * @author mausa
 */
public class TestPingPong {

    public static void main(String[] args) {
        PingPong t1 = new PingPong("PING", 33);
        PingPong t2 = new PingPong("PONG", 10);

        t1.start();
        t2.start();

    }
}
