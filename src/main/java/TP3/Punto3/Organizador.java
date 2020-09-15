/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3.Punto3;

/**
 *
 * @author mausa
 */
public class Organizador {

    private String[] listaCadenas = {"A", "BB", "CCC"};
    private int turno = 0;

    //Metodo sincronizado para asignar los turnos en que los hilos van a ejecutarse
    public synchronized boolean esTurno(String cadena) {
        // Pregunta si en la posicion "turno" de la listaLetras se encuentra una cadena
        // igual a la pasada por parametro
        return this.listaCadenas[turno].equals(cadena);
    }

    public void siguiente() {
        this.turno = (turno + 1) % 3;
    }

}
