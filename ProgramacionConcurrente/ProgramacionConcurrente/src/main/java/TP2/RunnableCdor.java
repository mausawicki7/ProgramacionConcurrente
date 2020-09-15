/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2;

/**
 *
 * @author mausa
 */
class RunnableCdor implements Runnable {

    Dato unContador;

    public RunnableCdor(Dato elCdor) {

        unContador = elCdor;
    
}


public void run() {

        for (int x = 0; x < 10000; ++x) {
            unContador.incrementar();
        }

    }

}


