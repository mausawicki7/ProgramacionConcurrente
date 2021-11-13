
package jerarquicas;

/**
 *
 * @author Mauricio
 */
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;  


public NodoGen(Object elemento){
    this.elem = elemento;
}    

    NodoGen(Object elemento, NodoGen izq, NodoGen der) {
        this.elem = elemento;
        this.hijoIzquierdo = izq;
        this.hermanoDerecho = der;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
public Object getElem(){
    return this.elem;
}

public NodoGen getHijoIzquierdo(){
    return this.hijoIzquierdo;
}

public NodoGen getHermanoDerecho(){
    return this.hermanoDerecho;
}

public void setElem(Object elemento){
    this.elem = elemento;
}

public void setHijoIzquierdo(NodoGen nodo){
    this.hijoIzquierdo = nodo;
}

public void setHermanoDerecho(NodoGen nodo){
    this.hermanoDerecho = nodo;
}

}