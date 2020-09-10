/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2;

class Dato {

    private int valor;

    public synchronized void contar() {
        valor = ++valor;
    }
    
        void incrementar() {
        valor = valor +1;
    }

    int obtenerValor() {
        return valor;
    }

}
