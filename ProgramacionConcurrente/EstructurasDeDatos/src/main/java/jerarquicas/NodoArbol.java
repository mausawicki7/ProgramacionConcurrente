package jerarquicas;

/**
 *
 * @author Mauricio
 */
public class NodoArbol {
    
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    public NodoArbol(Object elem, NodoArbol izq, NodoArbol der){
        this.elem = elem;
        this.izquierdo = izq;
        this.derecho = der;
    }
    
    public NodoArbol (Object elem){
        this.elem = elem;
    }
    
    public Object getElem(){
        return this.elem;
    }
    
    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    } 
    
    public NodoArbol getDerecho(){
        return this.derecho;
    } 
    
    public void setElem(Object e){
        this.elem = e;
    }
    
    public void setIzquierdo(NodoArbol izq){
        this.izquierdo = izq;
    }
    
    public void setDerecho(NodoArbol der){
        this.derecho = der;
    }

    
    
    
}
