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

public class Cajera implements Runnable {

    private String nombre;
    private Cliente cliente;
    private long initialTime;

    public Cajera(String nombre, Cliente cliente, long tiempo) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = tiempo;
    }

    public void run() {
        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() + " EN EL TIEMPO: " + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg.");

        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this​.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) + "->Tiempo: " + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg.");
        }

        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " + cliente.getNombre() + " EN EL TIEMPO: " + (System.currentTimeMillis() - this.initialTime) / 1000 + " seg.");
    }

    public void esperarXsegundos(int delay) {

        try {
            Thread.sleep(delay * 1000);

        } catch (Exception e) {

        }

    }
}

