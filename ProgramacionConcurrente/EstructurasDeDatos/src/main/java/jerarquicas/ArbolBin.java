package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

/**
 *
 * @author Mauricio Sawicki FAI-2256
 */
public class ArbolBin {

    private NodoArbol raiz;
    private int altura = -1;

    public ArbolBin() {
        this.raiz = null;
    }
            
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;
        // inserta elemNuevo como hijo del primero nodo encontrado (en preorden) igual a elemPadre
        // lo pone como hijo izq o hijo der segun char lugar

        if (this.raiz == null) {
            //caso especial
            //si el arbol esta vacío inserto el elemento en la raiz
            this.raiz = new NodoArbol(elemNuevo);
        } else {
            //si no esta vacio se busca al nodo padre
            //el elemPadre ingresa por parametro ahora tengo que buscar el nodo donde esta ese elemPadre
            //lo hago con un metodo privado que me devuelve el nodo donde esta el elemento
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);

            if (nodoPadre != null) {
                //si hay que ubicarlo en el HI y el HI es vacío
                //creo un nuevo nodo e inserto el elemNuevo
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo));
                } else {
                    if (lugar == 'D' && nodoPadre.getDerecho() == null) {
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo));
                    } else {
                        exito = false;
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;

    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol nodoResultado = null;

        if (n != null) {
            if (n.getElem().equals(buscado)) {
                //caso especial que n = buscado
                //si n es el buscado, lo devuelve
                nodoResultado = n;
            } else {
                //si no es el buscado busca primero en el hijo izquierdo    
                nodoResultado = obtenerNodo(n.getIzquierdo(), buscado);
                //si no lo encontro en el izquierdo busca en el derecho
                if (nodoResultado == null) {
                    nodoResultado = obtenerNodo(n.getDerecho(), buscado);
                }

            }
        }
        return nodoResultado;

    }

    public void vaciar() {
        this.raiz = null;
    }

    // solo busca el padre del nodo raiz
   public Object padre(Object elemento) {
        return (this.raiz == null || this.raiz.getElem().equals(elemento)) ? null : padreAux(this.raiz, elemento);
    }

    private Object padreAux(NodoArbol nodo, Object elemento) {
        Object res = null;
        if (nodo != null) {
            NodoArbol izq = nodo.getIzquierdo();
            NodoArbol der = nodo.getDerecho();
            if ((izq != null && izq.getElem().equals(elemento)) || (der != null && der.getElem().equals(elemento))) {
                res = nodo.getElem();
            } else {
                res = padreAux(nodo.getIzquierdo(), elemento);
                if (res == null)
                    res = padreAux(nodo.getDerecho(), elemento);
            }
        }
        return res;
    }

    public int altura() {
        alturaAux(this.raiz, 0);
        return this.altura;
    }

    private void alturaAux(NodoArbol nodoActual, int nivel) {
        if (nodoActual != null) {
            alturaAux(nodoActual.getIzquierdo(), nivel + 1);
            if (nivel > this.altura) {
                this.altura = nivel;
            }
            alturaAux(nodoActual.getDerecho(), nivel + 1);
        }
    }

    public int nivel(Object elem) {
        int level = -1;
        if (!this.esVacio()) {
            level = nivelAux(this.raiz, elem, 0);
        }
        return level;
    }

    private int nivelAux(NodoArbol nodoActual, Object elem, int profundidadActual) {
        int res = -1;

        if (nodoActual != null) {
            //visito nodoActual
            //si el elemActual es igual al elem ingresado por parametro devuelvo el nivel
            if (nodoActual.getElem().equals(elem)) {
                res = profundidadActual;
            } else {
                res = nivelAux(nodoActual.getIzquierdo(), elem, profundidadActual + 1);
                if (res == -1) //corta si lo encontro por izquierda        
                {
                    res = nivelAux(nodoActual.getDerecho(), elem, profundidadActual + 1);
                }

            }
        }
        return res;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public ArbolBin clone() {
        ArbolBin nuevoArbol = new ArbolBin();
        nuevoArbol.raiz = cloneAux(this.raiz);
        return nuevoArbol;
    }

    private NodoArbol cloneAux(NodoArbol nodoActual) {
        NodoArbol hijo = null;
        if (nodoActual != null) {
            hijo = new NodoArbol(nodoActual.getElem(), nodoActual.getIzquierdo(), nodoActual.getDerecho());
        }
        return hijo;
    }

    // (RAIZ NULA)? --> SI = entonces llama a toStringAux : NO = entonces imprimi "Arbol vacio" 
    @Override
    public String toString() {
        return (this.raiz != null)
                ? toStringAux(this.raiz, "") : "Arbol Vacio";
    }

    public String toStringAux(NodoArbol nodo, String s) {
        if (nodo != null) {
            s += "\n" + nodo.getElem() + "\t";
            NodoArbol izq = nodo.getIzquierdo();
            NodoArbol der = nodo.getDerecho();
            s += "HI: " + ((izq != null) ? izq.getElem() : "-") + "\t"
               + "HD: " + ((der != null) ? der.getElem() : "-");
            s = toStringAux(nodo.getIzquierdo(), s);
            s = toStringAux(nodo.getDerecho(), s);
        }
        return s;
    }

    public Lista listarPreOrden() {
        Lista lista = new Lista();
        preOrdenAux(this.raiz, lista);
        return lista;
    }

    private void preOrdenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            preOrdenAux(nodo.getDerecho(), lista);
            preOrdenAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), 1);
        }
    }

    public Lista listarInOrden() {
        Lista lista = new Lista();
        inOrdenAux(this.raiz, lista);
        return lista;
    }

    private void inOrdenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            inOrdenAux(nodo.getDerecho(), lista);
            lista.insertar(nodo.getElem(), 1);
            inOrdenAux(nodo.getIzquierdo(), lista);
        }
    }

    public Lista listarPosOrden() {
        Lista lista = new Lista();
        posOrdenAux(this.raiz, lista);
        return lista;
    }

    private void posOrdenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElem(), 1);			
            posOrdenAux(nodo.getDerecho(), lista);
            posOrdenAux(nodo.getIzquierdo(), lista);
        }
    }
    
        public Lista obtenerAncestros(Object elemento) {
        Lista lista = new Lista();
        obtenerAncestrosAux(this.raiz, lista, elemento);
        // dependiendo de la salida que se desea se invierte o no
        lista.invertir();
        return lista;
    }

    public boolean obtenerAncestrosAux(NodoArbol nodo, Lista lista, Object elemento) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                encontrado = true;
                //Se encuentra a la izquierda o a la derecha del nodo actual?
            } else if (obtenerAncestrosAux(nodo.getIzquierdo(), lista, elemento) 
                      || obtenerAncestrosAux(nodo.getDerecho(), lista, elemento)) {
                // verifica si se encuentra a la izquierda, aprovecho el corto circuito para en caso de que este, no se busque
                // caso contrario se ejecutara el siguiente llamado recursivo
                lista.insertar(nodo.getElem(), 1);
                encontrado = true;
            }
        }
        return encontrado;
    }


    public Lista niveles() {
        Cola cola = new Cola();
        Pila pila = new Pila();
        Lista lista = new Lista();
        cola.poner(this.raiz);
        NodoArbol nodo;
        while (cola.obtenerFrente() != null) {
            nodo = (NodoArbol) cola.obtenerFrente();
            cola.sacar();
            pila.apilar(nodo.getElem());
            if (nodo.getIzquierdo() != null)
                cola.poner(nodo.getIzquierdo());
            if (nodo.getDerecho() != null)
                cola.poner(nodo.getDerecho());
        }
        while (pila.obtenerTope() != null) {
            lista.insertar(pila.obtenerTope(), 1);
            pila.desapilar();
        }
        return lista;
    }
    
    public Lista frontera() {
        Lista lista = new Lista();

        int pos = 1;
        fronteraRec(this.raiz, lista, pos);

        return lista;
    }

    private void fronteraRec(NodoArbol a, Lista lista2, int pos) {
        if (a != null) {
            if (a.getDerecho() == null && a.getIzquierdo() == null) {

                lista2.insertar(a.getElem(), pos);

            } else {
                fronteraRec(a.getDerecho(), lista2, pos);
                fronteraRec(a.getIzquierdo(), lista2, pos);

            }
        }
    }
}
