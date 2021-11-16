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
                if (res == null) {
                    res = padreAux(nodo.getDerecho(), elemento);
                }
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

    /**
     * @PREORDEN
     */
    public Lista listarPreOrden() {
        Lista lista = new Lista();
        preOrdenAux(this.raiz, 1, lista);
        return lista;
    }

    private void preOrdenAux(NodoArbol nodo, int pos, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElem(), pos);
            preOrdenAux(nodo.getIzquierdo(), pos + 1, lista);
            preOrdenAux(nodo.getDerecho(), lista.longitud() + 1, lista);
        }
    }

    /**
     * @POSORDEN
     */
    public Lista listarPosOrden() {
        Lista lista2 = new Lista();
        posOrdenAux(this.raiz, lista2);
        return lista2;
    }

    private void posOrdenAux(NodoArbol nodo, Lista lista2) {
        if (nodo != null) {
            posOrdenAux(nodo.getIzquierdo(), lista2);
            posOrdenAux(nodo.getDerecho(), lista2);
            lista2.insertar(nodo.getElem(), lista2.longitud() + 1);
        }

    }

    /**
     * @INORDEN
     */
    public Lista listarInOrden() {
        Lista lista3 = new Lista();
        inOrdenAux(this.raiz, lista3);
        return lista3;

    }

    private void inOrdenAux(NodoArbol nodo, Lista lista3) {
        if (nodo != null) {
            inOrdenAux(nodo.getIzquierdo(), lista3);
            lista3.insertar(nodo.getElem(), lista3.longitud() + 1);
            inOrdenAux(nodo.getDerecho(), lista3);
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
            if (nodo.getIzquierdo() != null) {
                cola.poner(nodo.getIzquierdo());
            }
            if (nodo.getDerecho() != null) {
                cola.poner(nodo.getDerecho());
            }
        }
        while (pila.obtenerTope() != null) {
            lista.insertar(pila.obtenerTope(), 1);
            pila.desapilar();
        }
        return lista;
    }

    /*
    Verifica que el árbol otro sea igual al arbol this. 
    No puede usar los métodos básicos del TDA (insertar, existe, etc) 
    y debe de recorrer lo mínimo ambos árboles.
     */
    public boolean equals(ArbolBin otro) {
        return equalsAux(this.raiz, otro.raiz);
    }

    private boolean equalsAux(NodoArbol nodoA1, NodoArbol nodoA2) {
        boolean res = true;
        if (nodoA1 != null && nodoA2 != null) {
            if (!nodoA1.getElem().equals(nodoA2.getElem())) {
                res = false;
            } else {
                res = equalsAux(nodoA1.getIzquierdo(), nodoA2.getIzquierdo());

                if (res) {
                    res = equalsAux(nodoA1.getDerecho(), nodoA2.getDerecho());
                }
            }
        }

        return res;
    }

    public void alterarParte(Object hi, Object hd, Object pp) {
        if (this.raiz != null) {
            NodoArbol nuevo = obtenerNodo(this.raiz, pp);
            if (nuevo != null) {
                alterarParteAux(this.raiz, hi, hd);
            }
        }
    }

    private void alterarParteAux(NodoArbol nuevo, Object hi, Object hd) {
        if(nuevo.getIzquierdo() != null && nuevo.getDerecho() == null){
            nuevo.setIzquierdo(new NodoArbol(hi));
            nuevo.setDerecho(new NodoArbol(hd));

        }else{
            nuevo.setIzquierdo(new NodoArbol(hi));
            //HACEEER
        }
    }

    
    
    
    //Metodo que me devuelve una lista con todas las hojas del arbol
    //el orden es n porque se visita una vez cada nodo del arbol 
    //este seria el texto para justificar el orden
    public Lista frontera() {
        Lista lista = new Lista();
        fronteraRec(this.raiz, lista, 1);
        return lista;
    }

    private void fronteraRec(NodoArbol nodo, Lista lista2, int pos) {
        if (nodo != null) {
            if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {

                lista2.insertar(nodo.getElem(), pos);

            } else {
                fronteraRec(nodo.getDerecho(), lista2, pos);
                fronteraRec(nodo.getIzquierdo(), lista2, pos);
            }
        }
    }

    /**
     * Metodo que recibe por parámetro una lista patron y determina si coincide
     * exactamente con al menos un camino del árbol que comience en la raíz y
     * termine en una hoja
     */
    public boolean verificarPatron(Lista listaPatron) {
        boolean res;
        res = verificarRecursivo(this.raiz, listaPatron, 1);
        return res;

    }

    private boolean verificarRecursivo(NodoArbol nodo, Lista listaPatron, int posicion) {
        boolean aux = false;

        if (posicion > listaPatron.longitud()) {
            aux = true;

        } else if ((nodo != null) && (nodo.getElem()).equals(listaPatron.recuperar(posicion))) {

            aux = verificarRecursivo(nodo.getIzquierdo(), listaPatron, posicion + 1);

            if (!aux) {
                aux = verificarRecursivo(nodo.getDerecho(), listaPatron, posicion + 1);
            }

        }
        return aux;
    }

    /*
    clonarInvertido() que devuelve un nuevo árbol, que es una copia del árbol original(this) pero donde los hijos   
    están cambiados de lugar. Atención: el método devuelve un nuevo árbol, sin modificar el árbol original.
     */
    public ArbolBin clonarInvertido() {
        ArbolBin arbolClon = new ArbolBin();
        if (this.raiz != null) {
            arbolClon.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            clonarInvertidoAux(this.raiz, arbolClon.raiz);
        }
        return arbolClon;
    }

    private void clonarInvertidoAux(NodoArbol nOrig, NodoArbol nClon) {
        if (nOrig.getDerecho() != null) {
            //Si el HD del arbol original tiene elemento
            //Creo un nuevo nodo y a ese nodo le seteo el elemento HD del arbol original
            //Ese nuevo nodo creado se lo pongo como HI al arbolClon
            nClon.setIzquierdo(new NodoArbol(nOrig.getDerecho().getElem(), null, null));
            //Llamo recursivamente para hacer lo mismo con toda la rama izquierda del arbol original
            clonarInvertidoAux(nOrig.getDerecho(), nClon.getIzquierdo());
        }
        if (nOrig.getIzquierdo() != null) {
            //Si el HI del arbol original tiene elemento
            //Creo un nuevo nodo y a ese nodo le seteo el elemento HI del arbol original
            //Ese nuevo nodo creado se lo pongo como HD al arbolClon
            nClon.setDerecho(new NodoArbol(nOrig.getIzquierdo().getElem(), null, null));
            //Llamo recursivamente para hacer lo mismo con toda la rama derecha del arbol original
            clonarInvertidoAux(nOrig.getIzquierdo(), nClon.getDerecho());
        }
    }
}
