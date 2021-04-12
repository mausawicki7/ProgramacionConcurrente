package lineales.dinamicas;

/**
 *
 * @author Mauricio Sawicki 
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }
    //Devuelve true si está vacía, false en caso contrario.
    public boolean esVacia() {
        return this.frente == null;
    }
    //Metodo para poner elementos en la cola, se ponen nuevos elementos por el fin de la misma.
    public boolean poner(Object elemento) {
        Nodo nuevo = new Nodo(elemento, null);
        //Si el fin es vacío, no hay ningun elemento, entonces engancho el nuevo nodo al frente.
        if (this.fin == null) {
            this.frente = nuevo;
        } else {
            //Apunto el fin al nodo nuevo creado
            this.fin.setEnlace(nuevo);
        }
        this.fin = nuevo;
        return true;
    }
    //Metodo para sacar elementos de la cola, se sacan los elementos por el frente de la misma.
    public boolean sacar() {
        boolean exito = false;
        if (this.frente != null) {
            //Engancho el frente al segundo nodo. (Reengancho el frente).
            //El garbage collector se va a llevar el nodo que queda sin apuntar.
            this.frente = this.frente.getEnlace();
            //Caso especial, cuando no hay elementos.
            if (this.frente == null) {
                this.fin = null;
            }
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elem;

        if (this.frente == null) {
            elem = null;
        } else {
            elem = this.frente.getElem();
        }
        return elem;
    }
    

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    @Override
    public Cola clone() {
        Cola colaClon;
        //nodo1 puntero de la cola original
        //nodo2 puntero de la cola clon
        Nodo nodo1, nodo2;
        Object elem;

        colaClon = new Cola();
        nodo1 = this.frente;

        if (nodo1 != null) {
            elem = nodo1.getElem();
            nodo2 = new Nodo(elem, null);
            colaClon.frente = nodo2;
            colaClon.fin = nodo2;

            while (nodo1.getEnlace() != null) {
                nodo1 = nodo1.getEnlace();
                elem = nodo1.getElem();
                nodo2 = new Nodo(elem, null);
                colaClon.fin.setEnlace(nodo2);
                colaClon.fin = colaClon.fin.getEnlace();
            }
        }

        return colaClon;

    }

    @Override
    public String toString() {
        String texto = "Cola vacía";
        if (this.frente != null) {
            Nodo aux = this.frente;
            texto = "[";
            while (aux != null) {
                texto += aux.getElem();
                aux = aux.getEnlace();
                if (aux != null)
                    texto += ", ";
            }
            texto += "]";
        }
        return texto;
    }
}
