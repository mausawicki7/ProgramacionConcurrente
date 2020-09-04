/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2Thread.punto6;

/**
 *
 * @author mausa
 */
public class Cliente {

    private String nombre;
    private int[] carroCompra; // Constructor y métodos de acceso 

    public Cliente(String nom, int[] arreglo) {
    this.nombre = nom;
    this.carroCompra = arreglo;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }
    
    

}
