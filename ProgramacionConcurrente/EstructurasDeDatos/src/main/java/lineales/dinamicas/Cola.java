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

    public boolean esVacia() {
        //Retorna true si el frente es igual a fin, es decir, si la cola está vacia.
        return this.frente == null;
    }

    public boolean poner(Object elemento) {
        //Creo un nuevo nodo le seteo el elem pasado por parametro y el enlace nulo.
        Nodo nuevo = new Nodo(elemento, null);
        //Si el fin es nulo entonces significa que debo poner el primer elemento, lo pongo en el frente.
        if (this.fin == null) {
            this.frente = nuevo;
        } else {
        //Si no es nulo el fin, quiere decir que ya hay un elemento en la cola, entonces seteo el enlace del
        //elemento que esta en el fin al nodo nuevo creado.
            this.fin.setEnlace(nuevo);
        }
        this.fin = nuevo;
        return true;
    }

    public boolean sacar() {
        boolean exito = false;
        if (this.frente != null) {
            this.frente = this.frente.getEnlace();
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
    
     public Cola clone2() {
        Cola clon = new Cola();
        if (!esVacia()) {
            Nodo aux1 = this.frente;
            clon.frente = new Nodo(aux1.getElem(), null);
            aux1 = aux1.getEnlace();
            Nodo aux2 = clon.frente;
            while (aux1 != null) {
                aux2.setEnlace(new Nodo(aux1.getElem(), null));
                aux2 = aux2.getEnlace();
                aux1 = aux1.getEnlace();
            }
            clon.fin = aux2;
        }

        return clon;
    }

    @Override
    public String toString() {
        String texto = "cola vacia";
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
