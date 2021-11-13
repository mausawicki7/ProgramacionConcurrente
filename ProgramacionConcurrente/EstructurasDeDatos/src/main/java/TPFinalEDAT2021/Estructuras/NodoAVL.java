/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Estructuras;

/**
 *
 * @author mausa
 */
public class NodoAVL {
        private Comparable clave;
    private Object dato;
    private int altura;
    private NodoAVL hijoIzq;
    private NodoAVL hijoDer;

    public NodoAVL() {
        this.altura = 0;
        this.hijoIzq = null;
        this.hijoDer = null;
    }

    public NodoAVL(Comparable clave, Object dato) {
        this.clave = clave;
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object nuevo) {
        dato = nuevo;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        // altura = recalcularAltura(this);
        if (this.getIzq() != null) {
            if (this.getDer() != null) {
                altura = 1 + Math.max(this.getDer().getAltura(), this.getIzq().getAltura());
            } else {
                altura = 1 + this.getIzq().getAltura();
            }
        } else {
            if (this.getDer() != null) {
                altura = 1 + this.getDer().getAltura();
            } else {
                if (this.getDer() == null) {
                    altura = 0;
                }
            }
        }
    }

    public NodoAVL getIzq() {
        return hijoIzq;
    }

    public NodoAVL getDer() {
        return hijoDer;
    }

    public void setIzq(NodoAVL izquierdo) {
        hijoIzq = izquierdo;
        if (izquierdo != null) {
            izquierdo.recalcularAltura();
        }
        this.recalcularAltura();
    }

    public void setDer(NodoAVL derecho) {
        hijoDer = derecho;
        if (derecho != null) {
            derecho.recalcularAltura();
        }
        this.recalcularAltura();
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }

    public Comparable getClave() {
        return this.clave;
    }

}

