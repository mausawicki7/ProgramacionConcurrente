/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;


/**
 *
 * @author Mauricio
 */
public class Pila {

    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean esVacia() {
        return this.tope == null;
    }

    public void vaciar() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        //crea un nuevo Nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        //se actualiza el tope para que apunte al nodo nuevo, es decir el nuevo tope es el tope del nuevo nodo
        this.tope = nuevo;
        //nunca hay error de pila llena por eso siempre se devuelve true
        return true;
    }

    public boolean desapilar() {
        boolean exito = false;
        //Si no estoy en el ultimo nodo (que su enlace apunta a null) HACER:
        if (this.tope != null) {
            // Al tope actual lo hago apuntar al enlace del nodo que esta debajo del actual
            // El nodo de mas arriba queda sin enlace entonces se lo lleva el Garbage Collectorm
            Nodo aux = this.tope.getEnlace();
            this.tope = aux;
            exito = true;
        }

        return exito;

    }

    public Object obtenerTope() {
        Object res = null;
        //Si no estoy en el ultimo nodo (que su enlace apunta a null) HACER:
        if (this.tope != null) {
            //Guardo en res el elemento que se encuentra en el tope y lo devuelvo
            res = this.tope.getElem();
        }
        return res;
    }

    @Override
    public Pila clone() {
        Pila pilaClonada = new Pila();
        Nodo nodoOriginal, nodoClon, nodoNuevo;
        if (this.tope != null) {
            
            //Seteo el tope al nodoOriginal
            nodoOriginal = this.tope;
            //Creo el primer nodo de la pila clon con el elemento que se encuentra en la pila original
            pilaClonada.tope = new Nodo(tope.getElem(), null);
            //Seteo el tope al nodoClon
            nodoClon = pilaClonada.tope;
            
            while (nodoOriginal.getEnlace() != null) {
                nodoOriginal = nodoOriginal.getEnlace();
                nodoNuevo = new Nodo(nodoOriginal.getElem(), null);
                nodoClon.setEnlace(nodoNuevo);
                nodoClon = nodoClon.getEnlace();

            }
        }

        return pilaClonada;
    }

    @Override
    public String toString() {
        String s = "Pila vacia";
        if (this.tope != null) {
            Nodo aux = this.tope;
            s = "[";
            while (aux != null) {
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null)
                    s += ", ";
            }
            s += "]";
        }
        return s;
    }
}
