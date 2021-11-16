/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Estructuras;

/**
 *
 * @author mausa
 */
public class NodoVert {
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;
    

    public NodoVert() {
        
        this.elem = null;
        this.sigVertice = null;
        this.primerAdy = null;
    }
    public NodoVert(Object elemento, NodoVert sigVert){
        //Constructor nodo vertice con un siguiente vertice
        this.elem = elemento;
        this.sigVertice = sigVert;
    }
    
    public Object getElem() {
        return this.elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoVert getSigVertice() {
        return this.sigVertice;
    }

    public void setSigVertice(NodoVert sigVertice) {
        this.sigVertice = sigVertice;
    }

    public NodoAdy getPrimerAdy() {
        return this.primerAdy;
    }

    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }

    public boolean equals(NodoVert obj) {
       return this.elem.equals(obj.getElem());
    }
    
}
