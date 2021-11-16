package conjuntistas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author Mauricio Sawicki FAI-2256
 */
public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable x) {
        boolean pertenece = false;
        NodoABB nodo = this.raiz;
        Comparable elemento;
        //Si se llega a un subarbol nulo, la busqueda termina sin exito
        while (nodo != null && !pertenece) {
            elemento = nodo.getElemento();
            //Comparar el elemento buscado con el del nodo raíz del subárbol
            if (elemento.equals(x)) {
                //Si coinciden (son iguales) la búsqueda culmina con éxito
                pertenece = true;
                // Si el elemento buscado es menor, la búsqueda continúa por el subárbol izquierdo
            } else if (elemento.compareTo(x) > 0) {
                nodo = nodo.getIzquierdo();
                // Si el elemento buscado es mayor, la búsqueda continúa por el subárbol derecho
            } else {
                nodo = nodo.getDerecho();
            }
        }
        return pertenece;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elemento);
        } else {
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }

    public boolean insertarAux(NodoABB nodo, Comparable elemento) {
        // precondicion: nodo no es nulo
        boolean exito = true;
        //Comparar el elemento a insertar con el del nodo raíz del subárbol
        if (elemento.equals(nodo.getElemento())) {
            // Si el elemento esta repetido -> error.
            exito = false;
        } else if (elemento.compareTo(nodo.getElemento()) < 0) {
            // Elemento es menor que nodo.getElemento()
            // Si tiene HI va bajando por la izquierda, sino agrega elemento
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), elemento);
            } else {
                nodo.setIzquierdo(new NodoABB(elemento));
            }
        } else if (nodo.getDerecho() != null) {
            // Elemento es mayor que nodo.getElemento()
            // Si tiene HD baja a la derecha, sino agrega elemento
            exito = insertarAux(nodo.getDerecho(), elemento);
        } else {
            nodo.setDerecho(new NodoABB(elemento));
        }
        return exito;
    }

    public Lista listar() {
        Lista lista = new Lista();
        listarAux(this.raiz, lista);
        return lista;
    }

    private void listarAux(NodoABB nodo, Lista lista) {
        if (nodo != null) {
            listarAux(nodo.getDerecho(), lista);
            lista.insertar(nodo.getElemento(), 1);
            listarAux(nodo.getIzquierdo(), lista);
        }
    }

    public Lista listarRango(int minimo, int maximo) {
        Lista lista = new Lista();
        listarRangoAux(this.raiz, lista, minimo, maximo);
        return lista;
    }

    private void listarRangoAux(NodoABB nodo, Lista lista, int minimo, int maximo) {
        if (nodo != null) {
            Comparable elemento = nodo.getElemento();

            if (elemento.compareTo(maximo) < 0) {
                listarRangoAux(nodo.getDerecho(), lista, minimo, maximo);
            }

            if (elemento.compareTo(minimo) >= 0 && elemento.compareTo(maximo) <= 0) {
                lista.insertar(elemento, 1);
            }

            if (elemento.compareTo(minimo) > 0) {
                listarRangoAux(nodo.getIzquierdo(), lista, minimo, maximo);
            }
        }
    }

    public Comparable minimo() {
        Comparable elemento = null;
        NodoABB nodo = this.raiz;
        // bajada por la izquierda
        while (nodo != null) {
            elemento = nodo.getElemento();
            nodo = nodo.getIzquierdo();
        }
        return elemento;
    }

    public Comparable maximo() {
        Comparable elemento = null;
        NodoABB nodo = this.raiz;
        // bajada por la derecha
        while (nodo != null) {
            elemento = nodo.getElemento();
            nodo = nodo.getDerecho();
        }
        return elemento;
    }

    public boolean eliminarMinimo() {
        boolean exito = false;
        if (this.raiz != null) {
            NodoABB nodo = this.raiz;
            NodoABB izquierdo = nodo.getIzquierdo();
            if (izquierdo == null) {
                // el nodo derecho puede ser null o no
                this.raiz = nodo.getDerecho();
            } else {
                // bajo por la izquierda
                while (izquierdo.getIzquierdo() != null) {
                    nodo = izquierdo;
                    izquierdo = nodo.getIzquierdo();
                }
                // el nodo derecho puede ser null o no
                nodo.setIzquierdo(izquierdo.getDerecho());
            }
            exito = true;
        }
        return exito;
    }

    public boolean eliminarMaximo() {
        boolean exito = false;
        if (this.raiz != null) {
            NodoABB nodo = this.raiz;
            NodoABB derecho = nodo.getDerecho();
            if (derecho == null) {
                this.raiz = nodo.getIzquierdo();
            } else {
                while (derecho.getDerecho() != null) {
                    nodo = derecho;
                    derecho = nodo.getDerecho();
                }
                nodo.setDerecho(derecho.getIzquierdo());
            }
            exito = true;
        }
        return exito;
    }

    public boolean eliminar(Comparable x) {
        return eliminarAux(this.raiz, null, x);
    }

    private boolean eliminarAux(NodoABB nodo, NodoABB padre, Comparable x) {
        boolean exito = false;
        if (nodo != null) {
            Comparable elemento = nodo.getElemento();
            if (elemento.equals(x)) {
                // elimina el nodo
                exito = eliminarNodo(nodo, padre);
            } else if (elemento.compareTo(x) > 0) {
                // Si es menor desciende por la izquierda del subarbol
                exito = eliminarAux(nodo.getIzquierdo(), nodo, x);
            } else {
                // Si es mayor desciende por la derecha del subarbol
                exito = eliminarAux(nodo.getDerecho(), nodo, x);
            }
        }
        return exito;
    }

    private boolean eliminarNodo(NodoABB nodo, NodoABB padre) {
        NodoABB izquierdo = nodo.getIzquierdo();
        NodoABB derecho = nodo.getDerecho();
        // determino el caso a eliminar
        if (izquierdo == null && derecho == null) {
            // elimino un nodo hoja (sin hijos)
            eliminarHoja(nodo, padre);
        } else if (izquierdo != null && derecho != null) {
            // elimino un nodo con dos hijos
            eliminarConDosHijos(nodo);
        } else {
            // elimino un nodo con un hijo, uno izquiedo o derecho, pero no ambos
            eliminarConUnHijo(nodo, padre);
        }
        return true;
    }

    // caso 1
    private void eliminarHoja(NodoABB hijo, NodoABB padre) {
        if (padre == null) {
            // caso especial un unico elemento
            this.raiz = null;
        } else if (padre.getIzquierdo() == hijo) {
            padre.setIzquierdo(null);
        } else {
            padre.setDerecho(null);
        }
    }

    // caso 2
    private void eliminarConUnHijo(NodoABB hijo, NodoABB padre) {
        // determino el nodo que tiene que reemplazar al hijo
        NodoABB remplazo = (hijo.getIzquierdo() != null) ? hijo.getIzquierdo() : hijo.getDerecho();
        if (padre == null) {
            // caso especial de la raiz con un hijo
            this.raiz = remplazo;
        } else if (padre.getIzquierdo() == hijo) {
            padre.setIzquierdo(remplazo);
        } else {
            padre.setDerecho(remplazo);
        }
    }

    // caso 3
    private void eliminarConDosHijos(NodoABB nodo) {
        NodoABB candidato = nodo.getDerecho();
        NodoABB padreCandidato = nodo;
        // obtengo el menor de los mayores (candidato)
        while (candidato.getIzquierdo() != null) {
            padreCandidato = candidato;
            candidato = candidato.getIzquierdo();
        }
        // remplazo el valor del nodo a eliminar por el valor del candidato
        nodo.setElemento(candidato.getElemento());
        // hijo pude ser null o no
        NodoABB hijoCandidato = candidato.getDerecho();
        // elimina el nodo
        // el candidato es el hijo derecho del nodo a eliminar?
        if (nodo.getDerecho() == candidato) {
            // caso especial, el candidato es hijo del nodo
            nodo.setDerecho(hijoCandidato);
        } else {
            // caso comun, el candidato no es hijo del nodo
            padreCandidato.setIzquierdo(hijoCandidato);
        }
    }

    private NodoABB obtenerNodo(NodoABB nodo, Comparable elem) {
        NodoABB n = null;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElemento()) == 0) {
                n = nodo;
            } else {
                if (elem.compareTo(nodo.getElemento()) < 0) {
                    n = obtenerNodo(nodo.getIzquierdo(), elem);
                } else {
                    n = obtenerNodo(nodo.getDerecho(), elem);
                }
            }
        }
        return n;
    }

    private NodoABB obtenerNodo(Comparable elem) {
        Comparable elemActual;
        NodoABB nodo = this.raiz;
        NodoABB resultado = null;
        while (resultado == null && nodo != null) {
            elemActual = nodo.getElemento();
            if (elemActual.equals(elem)) {
                resultado = nodo;
            } else if (elemActual.compareTo(elem) < 0) {
                nodo = nodo.getDerecho();
            } else {
                nodo = nodo.getIzquierdo();
            }
        }
        return nodo;
    }

    public boolean controlarCamino(Cola q) {
        boolean existeCamino = false, empezar = false;    
        existeCamino = controlarAux(this.raiz, q, false, false, empezar);    
        return existeCamino;
    }

    private boolean controlarAux(NodoABB nodo, Cola cola, boolean res, boolean aux, boolean empiezo) {
        if (nodo != null && !cola.esVacia() && !aux) {
            Comparable elemCola = (Comparable) cola.obtenerFrente();
            Comparable elemNodo = nodo.getElemento();

            if (elemNodo.compareTo(elemCola) == 0) {
                cola.sacar();
                aux = false;
                empiezo = true;
            } else {

                if (empiezo) {
                    aux = true;
                }
            }
            elemCola = (Comparable) cola.obtenerFrente();

            if (elemCola != null) {

                if (elemCola.compareTo(elemNodo) > 0) {
                    res = controlarAux(nodo.getDerecho(), cola, res, aux, empiezo);

                } else {
                    res = controlarAux(nodo.getIzquierdo(), cola, res, aux, empiezo);
                }
            }
            if (cola.esVacia()) {
                res = true;
            }
        }
        return res;

    }

    /*
    Recibe como parametro el elemento elem y el valor entero X. Se debe encontrar elem en el arbol y para ese subarbol
    de raiz elem recorrer en posorden y generar una cadena concatenando X caracteres, uno de cada de nodo visitado, y
    ahi detener el recorrido.
    
    El metodo deberá devolver la cadena generada. Si el subarbol se recorre por completo sin llegar a x-caracteres,
    se deberá iniciar la cadena con '@'. Si el arbol es vacio, o bien no existe elem, el metodo deberá volver '@@@'.
     */
    public String concatenarPosOrdenDesde(Comparable elem, int x) {
        String res = "@@@";
        if (this.raiz != null) {
            NodoABB nuevo = obtenerNodo(elem);
            if (nuevo != null) {
                if (nuevo.getIzquierdo() != null || nuevo.getDerecho() != null) {
                    res = concatenarPosOrdenDesdeAux(nuevo, x, "", 0);
                    if (res.length() < x) {
                        res = "@" + res;
                    }
                }
            }

        }
        return res;
    }

    private String concatenarPosOrdenDesdeAux(NodoABB nodo, int x, String cad, int cont) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                cont++;
                if (cont <= x) {
                    cad = concatenarPosOrdenDesdeAux(nodo.getIzquierdo(), x, cad, cont);
                }
            }
            if (nodo.getDerecho() != null) {
                cont++;
                if (cont <= x) {
                    cad = concatenarPosOrdenDesdeAux(nodo.getDerecho(), x, cad, cont);
                }
            }

            if (cad.length() < x) {
                cad = cad + nodo.getElemento();
            }
        }
        return cad;
    }

    public int sumarInordenDesde(Comparable valor, int t) {
        int res = 0;
        if (this.raiz != null) {
            NodoABB nuevo = obtenerNodo(valor);
            if (nuevo != null) {
                if (nuevo.getIzquierdo() != null || nuevo.getDerecho() != null) {
                    res = sumaInordenAux(nuevo, t, 0);
                    if (res < t) {
                        res = res * (-1);
                    }
                }
            }
        }
        return res;
    }

    private int sumaInordenAux(NodoABB nodo, int t, int acum) {
        if (nodo != null) {
//            if (nodo.getIzquierdo() != null) {
            if (acum <= t) {
                acum = sumaInordenAux(nodo.getIzquierdo(), t, acum);
//                }
            }
            if (acum < t) {
                acum = acum + (int) nodo.getElemento();
            }

//            if (nodo.getDerecho() != null) {
            if (acum < t) {
                acum = sumaInordenAux(nodo.getDerecho(), t, acum);
//                }
            }
        }
        return acum;
    }

    //Retorna la diferencia entre el menor elemento del lado derecho y el mayor elemento del lado izquierdo del subarbol
    //cuya raiz es el elemento pasado por parametro. Si el elemento no existe retorna -1 y si alguno de los subarboles es nulo
    //retorna -2
    public int diferenciaCandidatos(Comparable elem) {
        int res = -1;
        if (this.raiz != null) {
            NodoABB nuevo = obtenerNodo(elem);
            if (nuevo != null) {
                //caso especial que el subarbol sea nulo
                if (nuevo.getIzquierdo() == null && nuevo.getDerecho() == null) {
                    res = -2;
                } else {
                    res = diferenciaCandidatosAuxIzq(nuevo) - diferenciaCandidatosAuxDer(nuevo);
                }
            }
        }
        return res;
    }

    private int diferenciaCandidatosAuxIzq(NodoABB nodo) {
        int menorElemDerecho;
        if (nodo.getDerecho() != null) {
            NodoABB aux = nodo.getDerecho();
            if (aux.getIzquierdo() != null) {
                while (aux.getIzquierdo() != null) {
                    aux = aux.getIzquierdo();
                }
            }
            menorElemDerecho = (int) aux.getElemento();
        } else {
            menorElemDerecho = (int) nodo.getElemento();
        }
        return menorElemDerecho;
    }

    private int diferenciaCandidatosAuxDer(NodoABB nodo) {
        int mayorElemIzquierdo;
        if (nodo.getIzquierdo() != null) {
            NodoABB aux = nodo.getIzquierdo();
            if (aux.getDerecho() != null) {
                while (aux.getDerecho() != null) {
                    aux = aux.getDerecho();
                }
            }
            mayorElemIzquierdo = (int) aux.getElemento();
        } else {
            mayorElemIzquierdo = (int) nodo.getElemento();
        }
        return mayorElemIzquierdo;
    }

    //Retorna la diferencia entre el mayor y el menor elemento del subarbol cuya raiz es el elemento pasado por parametro.
    //Si el elemento no existe retorna -1 y si alguna de los subarboles es nulo retorna la diferencia entre el hijo que está
    //y la raíz del subarbol. Si los dos hijos son null devuelve 0 ya que null - null = 0
    public int amplitudSubarbol(Comparable elem) {
        int res = 0;
        if (this.raiz != null) {
            NodoABB nuevo = obtenerNodo(elem);
            if (nuevo != null) {
                if (nuevo.getIzquierdo() == null && nuevo.getDerecho() == null) {
                    res = 0;
                } else {
                    res = amplitudSubarbolDer(nuevo) - amplitudSubarbolIzq(nuevo);
                }
            } else {
                res = -1;
            }
        }
        return res;
    }

    private int amplitudSubarbolDer(NodoABB nodo) {
        int mayorElem;
        if (nodo.getDerecho() != null) {
            NodoABB aux = nodo.getDerecho();
            while (aux.getDerecho() != null) {
                aux = aux.getDerecho();
            }
            mayorElem = (int) aux.getElemento();
        } else {
            mayorElem = (int) nodo.getElemento();
        }

        return mayorElem;
    }

    private int amplitudSubarbolIzq(NodoABB nodo) {
        int menorElem;
        if (nodo.getIzquierdo() != null) {
            NodoABB aux = nodo.getIzquierdo();
            while (aux.getIzquierdo() != null) {
                aux = aux.getIzquierdo();
            }
            menorElem = (int) aux.getElemento();
        } else {
            menorElem = (int) nodo.getElemento();
        }

        return menorElem;
    }

    public boolean eliminarMinimo2() {
        boolean exito = false;
        if (this.raiz != null) {
            NodoABB nodo = this.raiz;
            NodoABB izquierdo = nodo.getIzquierdo();
            if (izquierdo == null) {
                // el nodo derecho puede ser null o no
                this.raiz = nodo.getDerecho();
            } else {
                // bajo por la izquierda
                while (izquierdo.getIzquierdo() != null) {
                    nodo = izquierdo;
                    izquierdo = nodo.getIzquierdo();
                }
                // el nodo derecho puede ser null o no
                nodo.setIzquierdo(izquierdo.getDerecho());
            }
            exito = true;
        }
        return exito;
    }

    //Devuelve un nuevo arbol que es una copia del subarbol original 
    //cuya raiz es elemento dado y cada hijo esta cambiado de lugar. 
    //Si el elem no existe el arbol devuelve es vacio.
    public ArbolBB clonarParteInvertida(Comparable elem) {
        ArbolBB arbolRetorno = new ArbolBB();
        if (this.raiz != null) {
            NodoABB nodo = obtenerNodo(elem);
            if (nodo != null) {
                //Seteo la raiz del arbol nuevo con el elemento 
                //que está raíz del subarbol obtenido
                arbolRetorno.raiz = new NodoABB(nodo.getElemento());
                clonarParteInvertidaAux(nodo, arbolRetorno.raiz);
            }
        }
        return arbolRetorno;
    }

    private void clonarParteInvertidaAux(NodoABB nodo, NodoABB nodoCopia) {
        if (nodo != null) {
            if (nodo.getDerecho() != null) {
                NodoABB hijoDer = new NodoABB(nodo.getDerecho().getElemento());
                //Seteo el hijo izquierdo del arbol clonado con el hijoDer del arbol original
                nodoCopia.setIzquierdo(hijoDer);
                clonarParteInvertidaAux(nodo.getDerecho(), nodoCopia.getIzquierdo());
            }
            if (nodo.getIzquierdo() != null) {
                NodoABB hijoIzq = new NodoABB(nodo.getIzquierdo().getElemento());
                //Seteo el hijo derecho del arbol clonado con el hijoIzq del arbol original
                nodoCopia.setDerecho(hijoIzq);
                clonarParteInvertidaAux(nodo.getIzquierdo(), nodoCopia.getDerecho());
            }
        }
    }

    //Retorna el candidato que tenga la menor diferencia con elem, si solo hay un candidato devuelve siempre ese.
    //Si elemento no existe retorna 0, y si ambos subarboles son nulos retorna -1. 
    public int mejorCandidato(Comparable elem) {
        int res = 0, mayor, menor;
        if (this.raiz != null) {
            NodoABB nuevo = obtenerNodo(elem);
            if (nuevo != null) {
                if (nuevo.getIzquierdo() == null && nuevo.getDerecho() == null) { //Se puede hacer en el public????
                    res = -1;
                } else {
                    mayor = diferenciaCandidatosAuxDer(nuevo); // Mayor elem del subarbol izquierdo

                    menor = diferenciaCandidatosAuxIzq(nuevo); // Menor elem del subarbol derecho

                    if ((int) menor - (int) elem < (int) elem - (int) mayor) {
                        res = menor;
                    } else {
                        res = mayor;
                    }
                }
            }
        }
        return res;
    }

    //En el TDA árbol binario de búsqueda, agregar el método listarMenores(elem) que dado un elemento devuelve 
    //una lista con los elementos estrictamente menores que elem ordenados de menor a mayor. 
    //El método debe recorrer lo mínimo indispensable del árbol.
    public Lista listarMenorIgual(Comparable elem) {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarMenorAux(this.raiz, lista, elem);
        }
        return lista;
    }

    private void listarMenorAux(NodoABB nodo, Lista lista, Comparable elem) {
        if (nodo != null) {
            if (elem.compareTo(nodo.getElemento()) >= 0) {

                listarMenorAux(nodo.getIzquierdo(), lista, elem);
                lista.insertar(nodo.getElemento(), lista.longitud() + 1);

                if (nodo.getDerecho() != null) {
                    listarMenorAux(nodo.getDerecho(), lista, elem);
                }
            } else {
                listarMenorAux(nodo.getIzquierdo(), lista, elem);

            }
        }

    }

    //En el TDA árbol binario de búsqueda, agregar el método listarMayorIgual(elem) que dado un elemento devuelve
    //una lista con los elementos mayores o iguales que elem ordenados de mayor a menor. 
    //El método debe recorrer lo mínimo indispensable del árbol.
    public Lista listarMayorIgual(Comparable elem) {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarMayorIgualAux(this.raiz, lista, elem);
        }
        return lista;
    }

    private void listarMayorIgualAux(NodoABB nodo, Lista lista, Comparable elem) {
        if (nodo != null) {
            if (elem.compareTo(nodo.getElemento()) <= 0) {
                listarMayorIgualAux(nodo.getDerecho(), lista, elem);
                lista.insertar(nodo.getElemento(), lista.longitud() + 1);
                if (nodo.getIzquierdo() != null) {
                    listarMayorIgualAux(nodo.getIzquierdo(), lista, elem);
                }
            } else {
                listarMayorIgualAux(nodo.getDerecho(), lista, elem);
            }
        }
    }

    //Devuelve una lista con los elementos mayores que valor del subarbol con raíz elem
    //Si no existe elem en el arbol el metodo deberá devolver una lista vacia.
    public Lista listarMayoresQue(Comparable valor, Comparable elem) {
        Lista lista = new Lista();
        NodoABB nuevo;
        if (this.raiz != null) {
            nuevo = obtenerNodo(elem);
            if (nuevo != null) {
                listarMayoresQueAux(nuevo, valor, lista);
            }
        }
        return lista;
    }

    private void listarMayoresQueAux(NodoABB nodo, Comparable valor, Lista lista) {
        if (nodo != null) {
            listarMayoresQueAux(nodo.getIzquierdo(), valor, lista);

            if (nodo.getElemento().compareTo(valor) > 0) {
                lista.insertar(nodo.getElemento(), lista.longitud() + 1);
            }
            listarMayoresQueAux(nodo.getDerecho(), valor, lista);
        }
    }

    // copiado de arbol binario
    @Override
    public String toString() {
        return (this.raiz != null) ? toStringAux(this.raiz, "") : "Arbol Vacio";
    }

    // copiado de arbol binario
    private String toStringAux(NodoABB nodo, String s) {
        if (nodo != null) {
            s += "\n" + nodo.getElemento() + "\t";
            NodoABB izquierdo = nodo.getIzquierdo();
            NodoABB derecho = nodo.getDerecho();
            s += "HI: " + ((izquierdo != null) ? izquierdo.getElemento() : "-") + "\t"
                    + "HD: " + ((derecho != null) ? derecho.getElemento() : "-");
            s = toStringAux(izquierdo, s);
            s = toStringAux(derecho, s);
        }
        return s;
    }

}
