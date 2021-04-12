/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author Mauricio
 */
//obtenerTope devuelve el elemento que esta en el tope, osea devuelve un Object.
public class Pila {

    private final Object[] arreglo;
    private int tope;
    private static final int TAM = 10;

    public Pila() {
        this.arreglo = new Object[TAM];
        this.tope = -1;
    }

    public boolean apilar(Object nuevoElem) {
        boolean exito;
        if (this.tope + 1 >= Pila.TAM) //Error: pila llena
        {
            exito = false;
        } else {
            // Pone el elemento en el tope de la pila e incrementa el tope
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    public boolean esVacia() {
        return this.tope == 0;
    }

    public boolean desapilar() {
        boolean exito;
        if (this.esVacia()) // No se puede desapilar, la pila esta vacía.
        {
            exito = false;
        } else {
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        Object elem = null;
        if (this.esVacia()) {
            System.out.println("La pila esta vacía.");
        } else {
            elem = arreglo[this.tope];
        }
        return elem;
    }

    public void vaciar() {
        if (!this.esVacia()) {
           for(int i = 0; i <= this.tope; i++){
             this.arreglo[i] = null;
        }
        }
    }

    @Override
    public String toString() {
        String s = "";
        if (!this.esVacia()) {
            for(int i=0; i<= this.tope; i++) {
                s += arreglo[i];
                s += ",";
            }

        }
        return s;
    }

    @Override
    public Pila clone() {
        int i;
        Pila p2 = new Pila();
        p2.tope = this.tope;

        for (i = 0; i <= this.tope; i++) {
            p2.arreglo[i] = this.arreglo[i];
        }
        return p2;
    }
}
