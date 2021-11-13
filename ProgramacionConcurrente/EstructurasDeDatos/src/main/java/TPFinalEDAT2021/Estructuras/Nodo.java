/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Estructuras;

/**
 *
 * @author mausa
 */
public class Nodo {

    private Object elem;
    private Nodo enlace;

    public Nodo(Object elemento, Nodo enlace) {
        this.elem = elemento;
        this.enlace = enlace;
    }

    public Nodo(Object elemento) {
        this.elem = elemento;
    }

    public Object getElem() {
        return this.elem;
    }

    public void setElem(Object elemento) {
        this.elem = elemento;
    }

    public Nodo getEnlace() {
        return this.enlace;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
}
