/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author Mauricio Sawicki Fai-2256
 */
public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    //Devuelve una lista con todos los elementos del arbol generico listados en PreOrden
    public Lista listarPreorden() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarPreOrdenAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarPreOrdenAux(NodoGen nodo, Lista lista) {

        if (nodo != null) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            NodoGen hijo = nodo.getHijoIzquierdo();

            while (hijo != null) {
                listarPreOrdenAux(hijo, lista);

                hijo = hijo.getHermanoDerecho();
            }

        }
    }

    //Devuelve una lista con todos los elementos del arbol generico listados en InOrden
    public Lista listarInorden() {

        Lista lista = new Lista();

        if (this.raiz != null) {
            listarInOrdenAux(this.raiz, lista);
        }

        return lista;
    }

    private void listarInOrdenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null) {
                // Llamado recursivo con HEI
                listarInOrdenAux(nodo.getHijoIzquierdo(), lista);
            }

            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            if (nodo.getHijoIzquierdo() != null) {
                // Baja a HD, mirando desde el padre
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();

                while (hijo != null) {
                    // Llamado recursivo
                    listarInOrdenAux(hijo, lista);

                    // Avanzo sobre los hermanos, mirando desde el padre
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    //Devuelve una lista con todos los elementos del arbol generico listados en PosOrden
    public Lista listarPosorden() {

        Lista lista = new Lista();

        if (this.raiz != null) {
            listarPosOrdenAux(this.raiz, lista);
        }

        return lista;
    }

    private void listarPosOrdenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null) {
                // Llamado recursivo con HEI
                listarPosOrdenAux(nodo.getHijoIzquierdo(), lista);

                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();

                while (hijo != null) {
                    // Llamado recursivo con HD. mirando desde el padre
                    listarPosOrdenAux(hijo, lista);

                    // avanzo sobre los hermanos mirando desde el padre
                    hijo = hijo.getHermanoDerecho();
                }
            }

            // inserto el elemento al final de la lista
            lista.insertar(nodo.getElem(), lista.longitud() + 1);

        }
    }

    public Lista listarNiveles() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            Cola cola = new Cola();
            NodoGen nodo, hijo;
            cola.poner(this.raiz);
            while (!cola.esVacia()) {
                nodo = (NodoGen) cola.obtenerFrente();
                lista.insertar(nodo.getElem(), 1);
                cola.sacar();
                hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    cola.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            lista.invertir();
        }
        return lista;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;

        if (this.raiz == null) {
            //Si el arbol esta vacio ponemos el elemento nuevo en la raiz
            this.raiz = new NodoGen(elemNuevo);
        } else {
            //Debemos buscar si existe el padre
            NodoGen padre = obtenerNodo(this.raiz, elemPadre);
            if (padre != null) {
                //Existe el padre
                NodoGen nodoNuevo = new NodoGen(elemNuevo);
                NodoGen nodoIzquierdo = padre.getHijoIzquierdo();
                //Si el padre no tiene hijo izq , inserto el nuevo como hijo izquierdo
                if (nodoIzquierdo == null) {
                    padre.setHijoIzquierdo(nodoNuevo);
                } else {
                    //Si tiene hijo izquierdo, debo insertarlo al final de sus hermanos derechos
                    while (nodoIzquierdo.getHermanoDerecho() != null) {
                        nodoIzquierdo = nodoIzquierdo.getHermanoDerecho();
                    }
                    nodoIzquierdo.setHermanoDerecho(nodoNuevo);
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    //Metodo que me devuelve el nodo del elemento buscado que envio por parametro
    private NodoGen obtenerNodo(NodoGen nodo, Object elemento) {
        NodoGen res = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                res = nodo;
            } else {
                res = obtenerNodo(nodo.getHijoIzquierdo(), elemento);
                if (res == null) {
                    res = obtenerNodo(nodo.getHermanoDerecho(), elemento);
                }
            }
        }
        return res;
    }

    public boolean esVacio() {
        boolean vacio = false;
        if (this.raiz == null) {
            vacio = true;
        }
        return vacio;

    }

    public int altura() {
        return alturaAux(this.raiz, -1);
    }

    private int alturaAux(NodoGen nodo, int altura) {
        if (nodo != null) {
            int n = alturaAux(nodo.getHijoIzquierdo(), altura + 1);
            int m = alturaAux(nodo.getHermanoDerecho(), altura);
            altura = (n > m) ? n : m;
        }
        return altura;
    }

    public int nivel(Object elem) {
        return nivelAux(elem, this.raiz, 0);
    }

    private int nivelAux(Object elem, NodoGen nodo, int niv) {
        int nivel = -1;
        NodoGen aux = null;

        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                nivel = niv;
            } else {
                aux = nodo.getHijoIzquierdo();

                while (aux != null && nivel == -1) {
                    nivel = nivelAux(elem, aux, niv + 1);
                    aux = aux.getHermanoDerecho();
                }

            }
        }

        return nivel;
    }

    public Object padre(Object elemento) {
        //Si se cumple que raiz es vacia o el elemento de la raiz es igual al elemento enviado por parametro
        //Devuelvo null, sino devuelvo el padre
        return (this.raiz == null || this.raiz.getElem().equals(elemento))
                ? null : padreAux(this.raiz, elemento);
    }

    private Object padreAux(NodoGen nodoActual, Object elemento) {
        Object res = null;
        if (nodoActual != null) {
            NodoGen sig = nodoActual.getHijoIzquierdo();
            while (sig != null && !sig.getElem().equals(elemento)) {
                sig = sig.getHermanoDerecho();
            }
            if (sig != null) {
                res = nodoActual.getElem();
            } else {
                res = padreAux(nodoActual.getHijoIzquierdo(), elemento);
                if (res == null) {
                    res = padreAux(nodoActual.getHermanoDerecho(), elemento);
                }
            }
        }
        return res;
    }

    public Lista listaQueJustificaAltura() {
        return listaQueJustificaAlturaAux(this.raiz, new Lista());
    }

    private Lista listaQueJustificaAlturaAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            Lista n, m;
            n = listaQueJustificaAlturaAux(nodo.getHijoIzquierdo(), new Lista());
            m = listaQueJustificaAlturaAux(nodo.getHermanoDerecho(), new Lista());
            n.insertar(nodo.getElem(), 1);
            lista = (n.longitud() > m.longitud()) ? n : m;
        }
        return lista;
    }

    public boolean pertenece(Object buscado) {
        boolean existe = false;
        if (this.raiz != null && buscado != null) {
            existe = perteneceAux(this.raiz, buscado);
        }
        return existe;
    }

    private boolean perteneceAux(NodoGen nodoActual, Object buscado) {
        boolean existe = false;
        if (nodoActual.getElem().equals(buscado)) {
            existe = true;
        } else {
            for (NodoGen hijo = nodoActual.getHijoIzquierdo(); !existe && hijo != null; hijo = hijo.getHermanoDerecho()) {
                existe = this.perteneceAux(hijo, buscado);
            }
        }
        return existe;

    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista ancestros(Object elemento) {
        Lista lista = new Lista();
        ancestrosAux(this.raiz, lista, elemento);
        lista.invertir();
        return lista;
    }

    private boolean ancestrosAux(NodoGen nodo, Lista lista, Object elemento) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                encontrado = true;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !encontrado) {
                    encontrado = ancestrosAux(hijo, lista, elemento);
                    hijo = hijo.getHermanoDerecho();
                }
                if (encontrado) {
                    lista.insertar(nodo.getElem(), 1);
                }
            }
        }
        return encontrado;
    }

    public boolean verificarPatron(Lista lista) {
        return verificarPatronAux(this.raiz, lista, 1);
    }

    private boolean verificarPatronAux(NodoGen nodo, Lista lista, int pos) {
        boolean exito = false;
        if (nodo != null) {
            Object elemento = lista.recuperar(pos);
            if (nodo.getElem().equals(elemento)) {
                // Llega al final ? sino es así continua buscando hacia abajo
                exito = (lista.longitud() == pos) ? true : verificarPatronAux(nodo.getHijoIzquierdo(), lista, pos + 1);
            } else {
                exito = verificarPatronAux(nodo.getHermanoDerecho(), lista, pos);
            }
        }
        return exito;
    }

    @Override
    public String toString() {
        String arbol = "";
        if (!this.esVacio()) {
            arbol = this.toStringAux(this.raiz);
        }

        return arbol;
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            s = s + n.getElem().toString() + " -> ";

            NodoGen hijo;
            for (hijo = n.getHijoIzquierdo(); hijo != null; hijo = hijo.getHermanoDerecho()) {
                s = s + hijo.getElem().toString() + " , ";
            }

            for (hijo = n.getHijoIzquierdo(); hijo != null; hijo = hijo.getHermanoDerecho()) {
                s = s + "\n" + this.toStringAux(hijo);
            }
        }

        return s;
    }

    public Lista frontera() {
        Lista lista = new Lista();
        fronteraAux(this.raiz, lista);
        lista.invertir();
        return lista;
    }

    private void fronteraAux(NodoGen nActual, Lista lista) {
        if (nActual != null) {
            if (nActual.getHijoIzquierdo() == null) {
                lista.insertar(nActual.getElem(), 1);
            } else {
                fronteraAux(nActual.getHijoIzquierdo(), lista);
            }
            fronteraAux(nActual.getHermanoDerecho(), lista);
        }
    }

    @Override
    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen nuevo = new NodoGen(nodo.getElem());
        if (nodo.getHijoIzquierdo() != null) {
            nuevo.setHijoIzquierdo(cloneAux(nodo.getHijoIzquierdo()));
        }
        if (nodo.getHermanoDerecho() != null) {
            nuevo.setHermanoDerecho(cloneAux(nodo.getHermanoDerecho()));
        }
        return nuevo;
    }

    public int misterioso2() {
        return misteriosoAux(this.raiz);
    }

    private int misteriosoAux(NodoGen n) {
        int res = -1;
        int aux = -1;

        if (n != null) {
            NodoGen h = n.getHijoIzquierdo();

            while (h != null) {
                res = misteriosoAux(h);

                if (aux < res) {
                    aux = res;
                }

                h = h.getHermanoDerecho();
            }

            aux = aux + 1;
        }

        return aux;

    }
}
