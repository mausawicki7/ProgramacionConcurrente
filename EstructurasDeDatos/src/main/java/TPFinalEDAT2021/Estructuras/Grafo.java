/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Estructuras;

/**
 *
 * @author mausa
 */

public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        this.inicio = null;

    }

    public Grafo(NodoVert inicio) {
        this.inicio = inicio;
    }

    public NodoVert getInicio() {
        return inicio;
    }

    public int cantidadVertices() {
        int largo = 0;
        NodoVert aux = this.inicio;
        while (aux != null) {
            largo++;
            aux = aux.getSigVertice();
        }
        return largo;
    }

    private NodoVert obtenerVertice(Object vertBuscado) {
        NodoVert aux = this.inicio;
        //recorro los vertices mientras todavia queden o todavia no encontre el buscado
        while (aux != null && !aux.getElem().equals(vertBuscado)) {

            aux = aux.getSigVertice();

        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoVert) {
        boolean respuesta = false;

        NodoVert aux = obtenerVertice(nuevoVert);
        //agrego el vertice si no existe
        if (aux == null) {
            // siempre al principio
            this.inicio = new NodoVert(nuevoVert, this.inicio);
            respuesta = true;
        }

        return respuesta;
    }

    public boolean eliminarVertice(Object vertice) {
        boolean exito = false;

        NodoVert verticeTemp = obtenerVertice(vertice);
        //si el vertice existe...
        if (verticeTemp != null) {
            //System.out.println(verticeTemp.getElem());
            //si es el primero seteo al inicio el siguiente
            if (verticeTemp.equals(this.inicio)) {

                eliminarVerticeAux(this.inicio);
                this.inicio = this.inicio.getSigVertice();

            } else {// sino lo busco para asignarle el siguiente
                boolean encontrado = false;
                NodoVert verticeAux = this.inicio;
                while (verticeAux != null && !encontrado) {
                    if (verticeAux.getElem().equals(vertice)) {
                        eliminarVerticeAux(verticeAux);
                        verticeTemp.setSigVertice(verticeAux.getSigVertice());
                        encontrado = true;
                    } else { //si no es paso al siguiente
                        verticeTemp = verticeAux;
                        verticeAux = verticeTemp.getSigVertice();
                    }
                }
            }
            exito = true;
        }
        return exito;
    }

    private void eliminarVerticeAux(NodoVert verticeEliminar) {
        //Elimina todas las adyacencias de un vertice dado
        NodoAdy adyOrigen = verticeEliminar.getPrimerAdy();
        //elimino todos los que tiene al vertice como destino
        while (adyOrigen != null) {
            //si tiene adyacencia con algun otro vertice
            NodoVert destino = adyOrigen.getVertice();
            //por cada adyacente se que voy a tener un adyacente entonces accedo al origen y elimino el 
            //NODOADY que corresponde al vertice a eliminar
            if (destino != null && destino.getPrimerAdy().getVertice().equals(verticeEliminar)) {
                //si es primer ady le seteo el siguiente como primero
                destino.setPrimerAdy(adyOrigen.getSigAdyacente());
            }//sino recorro hasta encontrarlo
            else {
                if (destino != null) {
                    NodoAdy adyAux = destino.getPrimerAdy();
                    boolean eliminado = false;
                    while (adyAux != null && !eliminado) {//Controlar si ya elimina no continuar!!!!!!
                        if (adyAux.getSigAdyacente() != null) {
                            if (adyAux.getSigAdyacente().getVertice().equals(verticeEliminar)) {

                                adyAux.setSigAdyacente(adyAux.getSigAdyacente().getSigAdyacente());
                                eliminado = true;
                            }
                        }
                        adyAux = adyAux.getSigAdyacente();
                    }
                }
            }
            adyOrigen = adyOrigen.getSigAdyacente();
        }
    }  
    
    public boolean insertarArco(Object origen, Object destino, Comparable etiqueta) {
        //Permite insertar arco con etiqueta entre dos nodos, 
        //lo hace en ambas direcciones de origen a destino y destino a origen
        boolean exito = false;
        //Ubica los dos nodos origen y destino
        NodoVert auxOrigen = this.obtenerVertice(origen);
        NodoVert auxDestino = this.obtenerVertice(destino);
        if (auxDestino != null && auxOrigen != null) {

            if (!existeArco(auxOrigen, auxDestino)) {
                insertarArcoAux(auxOrigen, auxDestino, etiqueta);
                insertarArcoAux(auxDestino, auxOrigen, etiqueta);
                exito = true;
            }
        }

        return exito;
    }

    private void insertarArcoAux(NodoVert origen, NodoVert destino, Comparable etiqueta) {

        NodoAdy nuevoArc = new NodoAdy(destino, etiqueta);
        nuevoArc.setSigAdyacente(origen.getPrimerAdy());
        origen.setPrimerAdy(nuevoArc);
    }

    private boolean existeArco(NodoVert origen, NodoVert destino) {
        boolean existe = false;
        NodoAdy auxAdy = origen.getPrimerAdy();
        while (auxAdy != null && !existe) {
            if (auxAdy.getVertice().equals(destino)) {

                existe = true;
            } else {
                auxAdy = auxAdy.getSigAdyacente();
            }

        }
        return existe;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        //elimina todo arco de origen a destino sabiendo que siempre existe un unico arco
        //reprecentado por 2 nodoAdy indicado por no ser un digrafo
        boolean exito = false;
        NodoVert vertOrigen = obtenerVertice(origen);
        NodoVert vertDestino = obtenerVertice(destino);
        if (existeArco(vertOrigen, vertDestino)) {
            //elimina de origen a destino
            eliminarArcoAux(vertOrigen, vertDestino);
            //elimina de destino a origen 
            eliminarArcoAux(vertDestino, vertOrigen);
            exito = true;
        }
        return exito;
    }

    private void eliminarArcoAux(NodoVert origen, NodoVert destino) {
        //elimina el arco (nodoAdy )formado de origen a destino
        NodoAdy adyOrigen = origen.getPrimerAdy();
        // si es el primer adyacente
        if (adyOrigen.getVertice().equals(destino)) {
            origen.setPrimerAdy(adyOrigen.getSigAdyacente());
        } else {
            NodoAdy auxAdy = adyOrigen.getSigAdyacente();
            boolean encontrado = false;
            while (auxAdy != null && !encontrado) {
                if (auxAdy.getVertice().equals(destino)) {
                    adyOrigen.setSigAdyacente(auxAdy.getSigAdyacente());
                    encontrado = true;
                } else {
                    adyOrigen = auxAdy;
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
    }

    public boolean existeVertice(Object vertice) {
        boolean existe = false;
        NodoVert auxVert = obtenerVertice(vertice);
        if (auxVert != null) {
            existe = true;
        }
        return existe;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        NodoVert auxOrigen = obtenerVertice(origen);
        NodoVert auxDestino = obtenerVertice(destino);
        // si ambos existen
        if (auxOrigen != null && auxDestino != null) {
            Lista visitados = new Lista();

            exito = existeCaminoAux(auxOrigen, destino, visitados);
        }
        return exito;
    }

    public boolean existeCaminoAux(NodoVert origen, Object destino, Lista visitados) {
        boolean exito = false;
        if (origen != null) {
            if (origen.getElem().equals(destino)) {
                //Si origen es el destino, entonces existe camino
                exito = true;
            } else {
                //Si no es el destino, verifica si hay camino entre origen y destino
                visitados.insertar(origen.getElem(), visitados.longitud() + 1);
                NodoAdy ady = origen.getPrimerAdy();
                while (!exito && ady != null) {
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                        //Si no esta en la lista de visitados, continua la busqueda recursiva del camino
                        exito = existeCaminoAux(ady.getVertice(), destino, visitados);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public Lista listarEnProfundidad() {

        Lista visitados = new Lista();

        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {

                listarEnProfundidadAux(aux, visitados);

            }
            aux = aux.getSigVertice();

        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis) {
        if (n != null) {

            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }

        }
    }

    public Lista listarEnAnchura() {
        //Permite listar en anchura todos los nodos
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            //Permite listar cada nodo vertice
            if (visitados.localizar(aux.getElem()) < 0) {
                //Si no fue visitado entonces realiza recursividad
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchuraAux(NodoVert vertice, Lista visitados) {
        //Metodo auxiliar para listar en anchura
        ColaObject q = new ColaObject();
        q.poner(vertice);
        NodoVert aux;
        while (!q.esVacia()) {
            //Mientras que la cola no este vacia obtengo el frente y saco
            aux = (NodoVert) q.obtenerFrente();
            q.sacar();
            //Si no fue visitado
            if (visitados.localizar(aux.getElem()) < 0) {
                //Inserto en la lista el nodo
                visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            }
            NodoAdy ady = aux.getPrimerAdy();
            //Permite obtener todos los adyacentes
            while (ady != null) {
                //Permite listar a todos los nodos adyacentes
                if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                    q.poner(ady.getVertice());
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Lista caminoMasCorto(Object origen, Object destino) {

        Lista caminoMasCorto = new Lista();
        Lista caminoFinal = new Lista();
        Lista visitados = new Lista();

        NodoVert vertOrigen = this.obtenerVertice(origen);
        NodoVert vertDestino = this.obtenerVertice(destino);

        if (vertOrigen != null && vertDestino != null && !vertOrigen.equals(vertDestino)) {

            caminoFinal = caminoMasCortoAux(vertOrigen, caminoMasCorto, visitados, destino);
        }

        return caminoFinal;

    }

    private Lista caminoMasCortoAux(NodoVert n, Lista caminoMasCorto, Lista visitados, Object destino) {
        // si todavia quedan vertices por lo tanto no es null
        if (n != null) {
            //inserto el nodo en el que estoy parado como visitado
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            //si es el destino
            if (n.getElem().equals(destino)) {
                //termine la recurcion por lo tanto comparo si ese camino es mas 
                //corto que Camino mas corto actual o recien inicio y caminoMasCorto es vacio
                if (visitados.longitud() < caminoMasCorto.longitud() || caminoMasCorto.esVacia()) {

                    caminoMasCorto = visitados.clone();
                }
                //sino empieso a recorrer sus arcos para encontrar camino mas corto
            } else {
                NodoAdy adyacente = n.getPrimerAdy();
                while (adyacente != null) {
                    //si el nodo aun no fue visitado
                    if (visitados.localizar(adyacente.getVertice().getElem()) < 0) {
                        //y ademas si al visitarlo la longitud es mayor a la de camino mas corto no voy por ese camino 
                        if (visitados.longitud() < caminoMasCorto.longitud() ^ caminoMasCorto.esVacia()) {

                            caminoMasCorto = caminoMasCortoAux(adyacente.getVertice(), caminoMasCorto, visitados, destino);
                        }
                    }
                    adyacente = adyacente.getSigAdyacente();//paso a buscar otro camino
                }

            }

        }

        visitados.eliminar(visitados.longitud());

        return caminoMasCorto;
    }

    @Override
    public String toString() {
        String cad = "";

        NodoVert n = this.inicio;
        while (n != null) {
            cad += "\nVertice: " + n.getElem() + "\n";
            NodoAdy arco = n.getPrimerAdy();

            cad += "Arcos: \n";
            while (arco != null) {
                cad += "etiqueta: " + arco.getEtiqueta() + " ------> "
                        + arco.getVertice().getElem() + "\n";
                arco = arco.getSigAdyacente();
            }
            n = n.getSigVertice();
        }
        return cad;
    }

}
