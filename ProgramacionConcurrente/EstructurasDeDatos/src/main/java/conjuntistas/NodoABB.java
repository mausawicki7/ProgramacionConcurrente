/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author mausa
 */
public class NodoABB {
    
    private Comparable elemento;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Comparable elemento, NodoABB izquierdo, NodoABB derecho) {
        this.elemento = elemento;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public NodoABB(Comparable elemento) {
        this(elemento, null, null);
    }

    public Comparable getElemento() {
        return this.elemento;
    }

    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    public NodoABB getIzquierdo() {
        return this.izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return this.derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }

}
