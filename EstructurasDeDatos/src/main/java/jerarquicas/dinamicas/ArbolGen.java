package jerarquicas.dinamicas;

import jerarquicas.NodoGen;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author Carla Nuñez FAI-1631
 * @author Mauricio Sawicki FAI-2256 Clase Arbol Generico
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

    public Lista listarPorNiveles() {
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

    public boolean esPadreDe(Object a, Object b) {
        boolean esHijo = false, encontrado = false;
        if (this.raiz != null) {
            esHijo = esPadreAux(this.raiz, a, b, esHijo, encontrado);
        }
        return esHijo;
    }

    private boolean esPadreAux(NodoGen nodo, Object a, Object b, boolean res, boolean encontrado) {
        if (nodo != null) {
            if (nodo.getElem().equals(a)) {
                encontrado = true;
                NodoGen izquierdo = nodo.getHijoIzquierdo();
                while (izquierdo != null && encontrado && !res) {
                    if (izquierdo.getElem().equals(b)) {
                        res = true;
                    } else {
                        izquierdo = izquierdo.getHermanoDerecho();
                    }

                }

            } else {
                res = esPadreAux(nodo.getHijoIzquierdo(), a, b, res, encontrado);
                NodoGen aux = nodo;

                if (!res && !encontrado) {
                    res = esPadreAux(aux.getHermanoDerecho(), a, b, res, encontrado);
                }
            }

        }
        return res;
    }

    public boolean esHermanoPosterior(Object a, Object b) {
        boolean esHno = false;
        if (this.raiz != null) {
            esHno = esHermanoPosteriorAux(this.raiz, a, b, false, false);
        }
        return esHno;
    }

    private boolean esHermanoPosteriorAux(NodoGen nodo, Object a, Object b, boolean res, boolean encontrado) {
        NodoGen hnoPosterior, hijo;

        if (nodo != null) {
            if (nodo.getElem().equals(b)) {
                encontrado = true;
                hnoPosterior = nodo.getHermanoDerecho();
                while (hnoPosterior != null && !res && encontrado) {
                    if (hnoPosterior.getElem().equals(a)) {
                        res = true;
                    } else {
                        hnoPosterior = hnoPosterior.getHermanoDerecho();
                    }
                }
            } else {
                res = esHermanoPosteriorAux(nodo.getHijoIzquierdo(), a, b, res, encontrado);
                hijo = nodo.getHermanoDerecho();

                if (!encontrado && !res) {
                    res = esHermanoPosteriorAux(hijo, a, b, res, encontrado);
                    //hijo = nodo.getHermanoDerecho();
                }
            }
        }
        return res;
    }

    public boolean insertarNieto(Object p, Object h, Object n) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = insertarNietoAux(this.raiz, p, h, n, false, false);
        }
        return exito;
    }

    private boolean insertarNietoAux(NodoGen n, Object padre, Object hijo, Object nieto, boolean res, boolean encontrado) {
        NodoGen izq, hijoAux, auxiliar;
        if (n != null) {
            if (n.getElem().equals(padre)) {
                encontrado = true;
                izq = n.getHijoIzquierdo();
                while (izq != null && !res && encontrado) {
                    if (izq.getElem().equals(hijo)) {
                        hijoAux = new NodoGen(nieto);

                        if (izq.getHijoIzquierdo() != null) {
                            auxiliar = izq.getHijoIzquierdo();
                            while (auxiliar.getHermanoDerecho() != null) {
                                auxiliar = auxiliar.getHermanoDerecho();
                            }
                            auxiliar.setHermanoDerecho(hijoAux);

                        } else {
                            izq.setHijoIzquierdo(hijoAux);
                        }
                        res = true;
                    } else {
                        izq = izq.getHermanoDerecho();
                    }
                }
            } else {
                res = insertarNietoAux(n.getHijoIzquierdo(), padre, hijo, nieto, encontrado, res);
                NodoGen m = n;

                if (!res && !encontrado) {
                    res = insertarNietoAux(m.getHermanoDerecho(), padre, hijo, nieto, encontrado, res);
                }
            }

        }
        return res;
    }

    public boolean esHermanoAnterior(Object a, Object b) {
        boolean esHno = false;
        if (this.raiz != null) {
            esHno = esHermanoAnteriorAux(this.raiz, a, b, false, false);
        }
        return esHno;
    }

    private boolean esHermanoAnteriorAux(NodoGen nodo, Object a, Object b, boolean res, boolean encontrado) {
        //a es es hno anterior de b? - a es anterior - b es posterior
        NodoGen hnoPosterior, hijo;
        if (nodo != null) {
            //Si encuentro al primer hermano (a)
            if (nodo.getElem().equals(a)) {
                encontrado = true; //encuentro al hno anterior

                hnoPosterior = nodo.getHermanoDerecho();

                while (hnoPosterior != null && !res && encontrado) {
                    if (hnoPosterior.getElem().equals(b)) {
                        res = true;
                    } else {
                        hnoPosterior = hnoPosterior.getHermanoDerecho();
                    }
                }
            }
            //no lo encuentra en el primer nivel que baja
            //por lo tanto recorre los hermanos de 1 nivel mas abajo
            hijo = nodo.getHijoIzquierdo();

            while (hijo != null && !res) {
                res = esHermanoAnteriorAux(hijo, a, b, res, encontrado);
                hijo = hijo.getHermanoDerecho();
            }
        }

        return res;
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

    public int gradoSubarbol(Object elemento) {
        int cant = -1;
        if (this.raiz != null) {
            NodoGen nodo = obtenerNodo(this.raiz, elemento);
            if (nodo != null) {
                cant = gradoSubAux(nodo);
            }
        }
        return cant;
    }

    private int gradoSubAux(NodoGen nodo) {
        int cont = 0;

        if (nodo.getHijoIzquierdo() != null) {
            cont++;
            NodoGen hijo = nodo.getHijoIzquierdo();

            while (hijo.getHermanoDerecho() != null) {
                cont++;
                hijo = hijo.getHermanoDerecho();

            }
        }
        return cont;
    }

    public int grado() {
        int grado = -1;
        if (this.raiz != null) {
            grado = gradoAux(this.raiz);

        }
        return grado;
    }

    private int gradoAux(NodoGen nodo) {
        int cant = 0;
        int cantAux = 0;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cant++;
                hijo = hijo.getHermanoDerecho();
            }

            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cantAux = gradoAux(hijo);
                hijo = hijo.getHermanoDerecho();
                if (cant >= cantAux) {

                    cantAux = cant;
                }

            }

        }

        return cantAux;
    }

    //Recibe una lista L y devuelve verdadero si la listaes igual a 
    //un camino desde la raíz del árbol hasta una hoja y falso en caso contrario
    //Escribir el método utilizando un recorrido recursivo para 
    //resolver la tarea indicada que recorra lo menos posible el árbol.
    public boolean verificarCamino(Lista listado) {
        boolean resultado = false;
        if (this.raiz != null) {
            resultado = verificarCaminoAux(listado, this.raiz, 1);
        }
        return resultado;
    }

    private boolean verificarCaminoAux(Lista lista, NodoGen nodo, int pos) {
        NodoGen aux;
        boolean respuesta = false;
        if (nodo != null) {
            if (pos < lista.longitud()) {
                if (nodo.getElem().equals(lista.recuperar(pos))) {
                    aux = nodo.getHijoIzquierdo();
                    while (respuesta == false && aux != null) {
                        respuesta = verificarCaminoAux(lista, aux, pos + 1);
                        aux = aux.getHermanoDerecho();

                    }
                }
            } else if (pos == lista.longitud()) {
                respuesta = nodo.getElem().equals(lista.recuperar(pos));
            } else {
                respuesta = true;
            }
        }
        return respuesta;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        int cont = 0;
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarEntreNivelesAux(this.raiz, lista, niv1, niv2, cont);
        }
        return lista;
    }

    private void listarEntreNivelesAux(NodoGen nodo, Lista lista, int nivMin, int nivMax, int contNivel) {
        if (nodo != null) {

            // Llamado recursivo con HEI
            if (contNivel < nivMax) {
                listarEntreNivelesAux(nodo.getHijoIzquierdo(), lista, nivMin, nivMax, contNivel + 1);

            }
            if (contNivel >= nivMin) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            if (contNivel < nivMax) {
                if (nodo.getHijoIzquierdo() != null) {
                    // Baja a HD, mirando desde el padre
                    NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();

                    while (hijo != null) {
                        // Llamado recursivo
                        listarEntreNivelesAux(hijo, lista, nivMin, nivMax, contNivel + 1);

                        // Avanzo sobre los hermanos, mirando desde el padre
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }

    }

    public void insertarEnPos(Object elem, Object padre, int pos) {
        if (this.raiz != null) {
            NodoGen nodo = obtenerNodo(this.raiz, padre);
            if (nodo != null) {
                insertarPosAux(nodo, elem, pos);
            }
        }
    }

//Inserta elem como hijo de padre en la posicion pos dentro de la lista de hermano. Si valor de pos es invalido
//debera insertar elem como ultimo hermano derecho.
    private void insertarPosAux(NodoGen raiz, Object elem, int pos) {
        int cont = 0; //voy a contar la cantidad de hermanos
        NodoGen nuevo = new NodoGen(elem);
        if (raiz.getHijoIzquierdo() != null) {
            cont++;
            NodoGen hijo = raiz.getHijoIzquierdo();
            //caso base si pos=1 es decir no tiene hei
            if (cont == pos) {
                raiz.setHijoIzquierdo(nuevo);
                nuevo.setHermanoDerecho(hijo);
            } else {
                while (hijo.getHermanoDerecho() != null && cont < pos) {
                    cont++;
                    if (cont <= pos) {
                        NodoGen posterior = hijo.getHermanoDerecho(); //D
                        //hijo vale C

                        if (cont == pos) {
                            hijo.setHermanoDerecho(nuevo);
                            nuevo.setHermanoDerecho(posterior);
                        }
                    }
                    hijo = hijo.getHermanoDerecho();
                }
                if (hijo.getHermanoDerecho() == null) //caso posicion invalida
                {
                    hijo.setHermanoDerecho(nuevo);
                }
            }
        } else {
            //pos 1 y no tengo hijo izq
            raiz.setHijoIzquierdo(nuevo);
        }
    }

    public Lista listarHastaNivel(int niv) {
        Lista listaniv = new Lista();
        int cont = 0;

        if (this.raiz != null) {
            listarHastaAux(niv, this.raiz, listaniv, cont);
        }
        return listaniv;
    }

    private void listarHastaAux(int niv, NodoGen nodo, Lista listaniv, int cont) {
        if (nodo != null) {
            if (cont <= niv) {
                listaniv.insertar(nodo.getElem(), listaniv.longitud() + 1);
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null) {
                    listarHastaAux(niv, aux, listaniv, cont + 1);
                    aux = aux.getHermanoDerecho();

                }

            }
        }
    }

//    public Lista listaQueJustificaAltura() {
//        return listaQueJustificaAlturaAux(this.raiz, new Lista());
//    }
//
//    private Lista listaQueJustificaAlturaAux(NodoGen nodo, Lista lista) {
//        if (nodo != null) {
//            Lista n, m;
//            n = listaQueJustificaAlturaAux(nodo.getHijoIzquierdo(), new Lista());
//            m = listaQueJustificaAlturaAux(nodo.getHermanoDerecho(), new Lista());
//            n.insertar(nodo.getElem(), 1);
//            lista = (n.longitud() > m.longitud()) ? n : m;
//        }
//        return lista;
//    }
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

}
