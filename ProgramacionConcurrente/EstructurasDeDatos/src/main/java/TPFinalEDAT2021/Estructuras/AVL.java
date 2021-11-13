/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Estructuras;

//import TPFinalEDAT2021.Clases.Jugador;

/**
 *
 * @author mausa
 */
public class AVL {
    private NodoAVL raiz;

    public AVL() {
        this.raiz = null;
    }

    public Object obtenerDato(Comparable clave) {
        Object dato = null;
        NodoAVL aux = obtenerDatoAux(this.raiz, clave);

        if (aux != null) {
            dato = aux.getDato();
        }
        return dato;
    }

    private NodoAVL obtenerDatoAux(NodoAVL nodo, Comparable clave) {

        NodoAVL datoAux = null;
        if (nodo != null && datoAux == null) {
            if (clave.compareTo(nodo.getClave()) < 0) {
                //clave menor me voy a la izquierda 
                datoAux = obtenerDatoAux(nodo.getIzq(), clave);
            } else {
                //sino si es mayor a derecha
                if (clave.compareTo(nodo.getClave()) > 0) {
                    datoAux = obtenerDatoAux(nodo.getDer(), clave);
                } else {
                    datoAux = nodo;//la encontre
                }
            }
        }
        return datoAux;
    }

    public boolean insertar(Comparable clave, Object dato) {
        boolean exito = true;
        //esta vacio?
        if (this.raiz == null) {// si lo esta sera la raiz
            this.raiz = new NodoAVL(clave, dato);
        } else {
            exito = insertarAux(this.raiz, clave, dato);
        }
        this.raiz.recalcularAltura();
        return exito;
    }

    private boolean insertarAux(NodoAVL nodo, Comparable clave, Object dato) {
        boolean exito = false;
        //comparo la clave del nodo con la que quiero insertar
        //si es menor me voy a la izquierda del arbol
        if (clave.compareTo(nodo.getClave()) < 0) {
            if (nodo.getIzq() == null) {//si no tiene hijo izquierdo agrego
                nodo.setIzq(new NodoAVL(clave, dato));
                exito = true;
            } else {//sino sigo bajando por la rama izquierda
                exito = insertarAux(nodo.getIzq(), clave, dato);
            }
        } else {//sino verifico si es mayor

            if (clave.compareTo(nodo.getClave()) > 0) {
                if (nodo.getDer() == null) {// si no hay hijo derecho agrego
                    nodo.setDer(new NodoAVL(clave, dato));
                    exito = true;
                } else {//sino sigo bajando por la rama derecha
                    exito = insertarAux(nodo.getDer(), clave, dato);
                }

            }
        }
        nodo.recalcularAltura();
        this.balancearArbol(nodo);
        return exito;
    }

    public boolean eliminar(Comparable elem) {
        boolean exito = false;
        //Si el arbol no es vacio
        if (this.raiz != null) {
            //si es la raiz
            if (this.raiz.getClave().compareTo(elem) == 0) {
                if (this.raiz.getDer() == null && this.raiz.getIzq() == null) {
                    this.raiz = null;
                } else {
                    if (this.raiz.getDer() != null && this.raiz.getIzq() == null) {
                        this.raiz = this.raiz.getDer();
                    } else {
                        if (this.raiz.getIzq() != null && this.raiz.getDer() == null) {
                            this.raiz = this.raiz.getIzq();
                        } else {
                            eliminarCaso3(this.raiz);
                        }
                    }
                    this.raiz.recalcularAltura();
                    balancearArbol(this.raiz);
                }
            } else {
                //si no es raiz voy a eliminarAux
                if (elem.compareTo(this.raiz.getClave()) < 0) {
                    exito = eliminarAux(this.raiz.getIzq(), elem, this.raiz);
                } else {
                    exito = eliminarAux(this.raiz.getDer(), elem, this.raiz);
                }
            }
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL nodo, Comparable clave, NodoAVL padre) {
        boolean exito = false;
        if (nodo.getClave().compareTo(clave) == 0) {//encontre el nodo a eliminar
            //si es hoja primer caso
            if (nodo.getDer() == null && nodo.getIzq() == null) {
                if (padre.getDer().getClave().compareTo(clave) == 0) {//es hijo derecho
                    padre.setDer(null);
                } else {
                    if (padre.getIzq().getClave().compareTo(clave) == 0) {//es hijo izquierdo
                        padre.setIzq(null);
                    }
                }
            } else { //caso dos tiene un hijo izquiero o derecho
                NodoAVL hijoDer = nodo.getDer();
                NodoAVL hijoIzq = nodo.getIzq();// casoa hijo izquierdo y derecho del padre
                if (hijoDer != null ^ hijoIzq != null) {
                    if (padre.getDer().equals(nodo)) {
                        if (nodo.getDer() != null) {
                            padre.setDer(nodo.getDer());
                        } else {
                            if (nodo.getIzq() != null) {
                                padre.setDer(nodo.getIzq());
                            }
                        }
                    } else {
                        if (nodo.getDer() != null) {
                            padre.setIzq(nodo.getDer());
                        } else {
                            if (nodo.getIzq() != null) {
                                padre.setIzq(nodo.getIzq());
                            }
                        }
                    }
                } else {// caso 3 debo elegir el maximo de los menores osea sub arbol izquierdo
                    eliminarCaso3(nodo);
                }
            }
            exito = true;
        } else {// mientras no sea el nodo buscado recursivamente lo busco
            // si es mayor voy a la derecha de lo contrario a izquierda
            if (nodo.getClave().compareTo(clave) < 0) {
                //si hay derecho me llamo con hijo derecho.
                if (nodo.getDer() != null) {
                    exito = this.eliminarAux(nodo.getDer(), clave, nodo);
                }
            } else {
                //sino si hay izquierdo me llamo con el hijo izquierdo
                if (nodo.getIzq() != null) {
                    exito = this.eliminarAux(nodo.getIzq(), clave, nodo);
                }
            }
        }
        nodo.recalcularAltura();
        System.out.println("altura: " + nodo.getAltura());
        this.balancearArbol(nodo);

        return exito;
    }

    private void eliminarCaso3(NodoAVL n) {
        NodoAVL m;
        //Verifico si el hijo izquierdo del que deseo eliminar tiene sub-arbol derecho.
        if (n.getIzq().getDer() != null) {
            //predM: padre de m.
            /* NodoAVLDicc predM;
            predM = n.getIzq();
            m = predM.getDer();
            while (m.getDer() != null) {
                predM = m;
                m = predM.getDer();
            }
            //Enlazo el padre del aspirante a su enlace.
            predM.setDer(m.getIzq());
            balancearArbol(predM);//estooooo*/
            m = eliminarCaso3aux(n.getIzq(), n);
        } else {
            m = n.getIzq();
            n.setIzq(m.getIzq());
        }
        n.setClave(m.getClave());
        n.setDato(m.getDato());
        n.recalcularAltura();
    }

    private NodoAVL eliminarCaso3aux(NodoAVL n, NodoAVL padre) {
        NodoAVL candidato = null;
        if (n.getDer() == null) {
            candidato = n;
            padre.setDer(n.getIzq());
        } else {
            candidato = eliminarCaso3aux(n.getDer(), n);
            n.recalcularAltura();//agregue por las dudas
            balancearArbol(n);
        }
        return candidato;
    }

    private void balancearArbol(NodoAVL n) {
        NodoAVL padre = obtenerPadre(this.raiz, n.getClave());
        int balanceN = calcularBalance(n), caidoIzquierda = 2, caidoDerecha = -2;

        if (balanceN == caidoIzquierda) {
            /* arbol caido a la izquierda */
            if ((calcularBalance(n.getIzq())) == -1) {
                /* rotacion doble izq ---> derecha */
                //System.out.println("Rotacion doble izq derecha");
                rotacionDobleIzquierdaDerecha(n, padre);
            } else {
                /* rotacion simple a derecha */
                //System.out.println("Rotacion simple a derecha");
                if (n == this.raiz) {
                    this.raiz = rotacionSimpleDerecha(n);
                    this.raiz.recalcularAltura();
                } else {
                    NodoAVL nuevaRaiz = rotacionSimpleDerecha(n);
                    if (padre.getIzq() == n) {
                        padre.setIzq(nuevaRaiz);
                    } else {
                        padre.setDer(nuevaRaiz);

                    }
                }
            }
        } else {
            if (balanceN == caidoDerecha) {
                /* arbol caido a la derecha */
                if (calcularBalance(n.getDer()) == 1) {
                    /* rotacion doble derecha izquierda */
                    //System.out.println("rotacion doble derecha izquierda");
                    rotacionDobleDerechaIzquierda(n, padre);
                } else {
                    /* rotacion simple a izquierda */
                    //System.out.println("Rotacion simple a izq");
                    if (n == this.raiz) {
                        this.raiz = rotacionSimpleIzquierda(n);
                        this.raiz.recalcularAltura();
                    } else {
                        NodoAVL nuevaRaiz = rotacionSimpleIzquierda(n);
                        if (padre.getIzq() == n) {
                            padre.setIzq(nuevaRaiz);
                        } else {
                            padre.setDer(nuevaRaiz);

                        }
                    }
                }
            }
        }
        n.recalcularAltura();
    }

    private NodoAVL rotacionSimpleIzquierda(NodoAVL pivote) {
        NodoAVL h, temp;
        h = pivote.getDer();
        temp = h.getIzq();
        h.setIzq(pivote);
        pivote.setDer(temp);
        pivote.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotacionSimpleDerecha(NodoAVL pivote) {
        NodoAVL h, temp;
        h = pivote.getIzq();
        temp = h.getDer();
        h.setDer(pivote);
        pivote.setIzq(temp);
        pivote.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private void rotacionDobleDerechaIzquierda(NodoAVL r, NodoAVL padre) {
        r.setDer(rotacionSimpleDerecha(r.getDer()));
        if (r == this.raiz) {
            this.raiz = rotacionSimpleIzquierda(r);
        } else {
            NodoAVL nuevaRaiz = rotacionSimpleIzquierda(r);
            if (padre.getIzq() == r) {
                padre.setIzq(nuevaRaiz);
            } else {
                padre.setDer(nuevaRaiz);
            }
            padre.recalcularAltura();
        }
        r.recalcularAltura();

    }

    private void rotacionDobleIzquierdaDerecha(NodoAVL r, NodoAVL padre) {
        r.setIzq(rotacionSimpleIzquierda(r.getIzq()));
        if (r == this.raiz) {
            this.raiz = rotacionSimpleDerecha(r);
        } else {
            NodoAVL nuevaRaiz = rotacionSimpleDerecha(r);
            if (padre.getIzq() == r) {
                padre.setIzq(nuevaRaiz);
            } else {
                padre.setDer(nuevaRaiz);
            }
            padre.recalcularAltura();
        }
        r.recalcularAltura();

    }

    //buscar padre de un nodo
    private NodoAVL obtenerPadre(NodoAVL n, Comparable clave) {
        NodoAVL padre = null;

        if (n != null) {
            //lo encontre en el izquierdo o derecho inmediato
            if ((n.getIzq() != null && n.getIzq().getClave().compareTo(clave) == 0)
                    || (n.getDer() != null && n.getDer().getClave().compareTo(clave) == 0)) {
                padre = n;
            } else {
                // si es 1 debo ir a la izquierda a buscarlo
                if (n.getClave().compareTo(clave) > 0) {
                    padre = obtenerPadre(n.getIzq(), clave);
                } else {//sino a derecha
                    padre = obtenerPadre(n.getDer(), clave);

                }
            }

        }
        return padre;

    }

    public boolean pertenece(Comparable clave) {

        return perteneceAux(this.raiz, clave);
    }

    private boolean perteneceAux(NodoAVL nodo, Comparable clave) {
        boolean pertenece = false;
        if (nodo != null) {
            //Si no es null voy recorriendo el arbol
            if (clave.compareTo(nodo.getClave()) < 0) {
                //Es menor me voy por la izquierda
                pertenece = perteneceAux(nodo.getIzq(), clave);
            } else {
                if (clave.compareTo(nodo.getClave()) > 0) {
                    //Es mayor me voy por la derecha
                    pertenece = perteneceAux(nodo.getDer(), clave);
                } else {
                    //Son iguales entonces pertenece
                    pertenece = true;
                }
            }
        }
        return pertenece;
    }

    public boolean modificarClaveNodo(Comparable claveModif, Comparable claveNueva) {
        boolean exito = true;
        if (pertenece(claveNueva)) {
            exito = false;
        } else {
            NodoAVL n = obtenerNodo(this.raiz, claveModif);
            n.setClave(claveNueva);
        }
        return exito;
    }

    private NodoAVL obtenerNodo(NodoAVL n, Comparable clave) {
        NodoAVL nuevo = null;
        if (n != null) {
            if (n.getClave().compareTo(clave) == 0) {
                nuevo = n;
            } else {
                if (n.getClave().compareTo(clave) > 0) {
                    nuevo = obtenerNodo(n.getIzq(), clave);
                } else {
                    nuevo = obtenerNodo(n.getDer(), clave);
                }
            }
        }
        return nuevo;
    }
     

    public Lista listarDatos() {
        Lista nLista = new Lista();
        listarAux3(raiz, nLista);
        return nLista;
    }

    private void listarAux3(NodoAVL n, Lista nLista) {
        if (n != null) {
            listarAux3(n.getIzq(), nLista);
            nLista.insertar(n.getDato(), nLista.longitud() + 1);
            listarAux3(n.getDer(), nLista);
        }
    }

    public Lista listarNombreSimilares(String subcadena) {
        Lista nLista = new Lista();
  //      listarAux2(this.raiz, nLista, subcadena);
        return nLista;
    }

  /**  private void listarAux2(NodoAVL n, Lista nLista, String subcadena) {
        if (n != null) {
            listarAux2(n.getIzq(), nLista, subcadena);
            Jugador unJ = (Jugador) n.getDato();
           if (unJ.getNombre().startsWith(subcadena)) {
                nLista.insertar(unJ, nLista.longitud() + 1);
            }
            listarAux2(n.getDer(), nLista, subcadena);
        }
    }

    public Lista listarClaves() {

        Lista l1 = new Lista();
        return listarAux(this.raiz, l1);
    }
*/
    private Lista listarAux(NodoAVL n, Lista l1) {
        // auxiliar para listar todas las claves del arbol
        if (n != null) {
            //Lista en inorden
            listarAux(n.getIzq(), l1);
            l1.insertar(n.getClave(), l1.longitud() + 1);
            listarAux(n.getDer(), l1);
        }
        return l1;
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista l1 = new Lista();
        return listarRangoAux(this.raiz, l1, min, max);
    }

    private Lista listarRangoAux(NodoAVL n, Lista l1, Comparable min, Comparable max) {

        if (n != null) {
            //Lista en inorden
            if (min.compareTo(n.getClave()) < 0) {
                //El elemento minimo es menor a la clave entonces me voy por la izquierda
                listarRangoAux(n.getIzq(), l1, min, max);
            }
            if (min.compareTo(n.getClave()) <= 0 && max.compareTo(n.getClave()) >= 0) {
                //El elemento es mayor o igual al minimo y menor o igual al maximo entonces lo inserto
                l1.insertar(n.getClave(), l1.longitud() + 1);
            }
            if (max.compareTo(n.getClave()) > 0) {
                //El elemento maximo es mayor que la clave entonces me voy por la derecha
                listarRangoAux(n.getDer(), l1, min, max);
            }
        }
        return l1;
    }

    private int calcularBalance(NodoAVL n) {
        //Metodo que permite obtener el balance de un nodo
        int altIzq, altDer;
        //Si el izquierdo es null
        if (n.getIzq() == null) {
            //Es null entonces altIzq es -1
            altIzq = -1;
        } else {
            //No es null entonces obtengo la altura del nodo izquierdo
            altIzq = n.getIzq().getAltura();
        }
        //Si el derecho es null
        if (n.getDer() == null) {
            //Es null entonces altDer es -1
            altDer = -1;
        } else {
            //No es null entones obtengo la altura del nodo derecho
            altDer = n.getDer().getAltura();
        }
        //Retorna altura
        return (altIzq - altDer);
    }

    public boolean esVacio() {
        boolean vacio = false;
        if (this.raiz == null) {
            vacio = true;
        }
        return vacio;
    }

    public boolean existeClave(Comparable clave) {
        //Permite verificar si existe la clave
        return perteneceAux(this.raiz, clave);
    }

    @Override
    public String toString() {

        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoAVL n) {

        String res = "";
        if (n != null) {
            res += "[" + n.getAltura() + "]" + n.getClave() + " ---> ";
            if (n.getIzq() != null) {
                res += n.getIzq().getClave() + ", ";
            } else {
                res += "S/e, ";
            }
            if (n.getDer() != null) {
                res += n.getDer().getClave() + "\r\n";
            } else {
                res += "S/e\r\n";
            }
            res += toStringAux(n.getIzq());
            res += toStringAux(n.getDer());
        }
        return res;
    }

}  
