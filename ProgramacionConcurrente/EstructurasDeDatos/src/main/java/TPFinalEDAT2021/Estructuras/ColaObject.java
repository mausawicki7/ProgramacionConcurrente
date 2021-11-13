/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Estructuras;

/**
 *
 * @author mausa
 */
public class ColaObject {

    private Nodo frente;
    private Nodo fin;

    public ColaObject() {
        //Constructor de la cola generica
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elem) {
        //Metodo que permite poner un elemento en la cola
        boolean res;
        Nodo nuevoNodo;
        //Creo nodo
        nuevoNodo = new Nodo(elem);
        //Si la cola esta vacia entonces el fin y el frente son iguales al nodo nuevo
        if (frente == null && fin == null) {
            //Esta vacia entonces guardo el nuevoNodo en fin y frente
            this.frente = nuevoNodo;
            this.fin = nuevoNodo;
            res = true;
        } else {
            //No esta vacia entonces seteo el nodo al fin, y guardo nuevo fin
            this.fin.setEnlace(nuevoNodo);
            this.fin = nuevoNodo;
            res = true;
        }
        return res;
    }

    public boolean sacar() {
        //Metodo que permite sacar un elemento en la cola
        boolean res = false;
        //Si el frente es distinto de null
        if (this.frente != null) {
            //Es distinto de null entonces saco elemento del frente, seteando al frente con el enlace de el mismo
            this.frente = this.frente.getEnlace();
            //Si el nuevo frente es null
            if (this.frente == null) {
                //Es null entonces fin es null, porque la cola esta vacia
                this.fin = null;
            }
            res = true;
        }
        return res;
    }

    public Object obtenerFrente() {
        //Permite obtener elemento del frente de la cola
        Object elem;
        if (esVacia()) {
            //Esta vacia entonces elemento es null
            elem = null;
        } else {
            //No esta vacia entonces obtengo elemento
            elem = this.frente.getElem();
        }
        return elem;
    }

    public boolean esVacia() {
        //Permite verificar si la cola esta vacia
        boolean res = false;
        //Si el frente es null
        if (this.frente == null) {
            //Es null entonces esta vacia
            res = true;
        }
        return res;
    }

    public void vaciar() {
        //Permite vaciar la cola
        this.frente = null;
        this.fin = null;
    }

    public ColaObject clone() {
        //Metodo que permite clonar la cola
        ColaObject clon = new ColaObject();
        Nodo aux = this.frente;
        clon.frente = aux;
        clon.fin = aux;
        while (aux != null) {
            //Permite ir poniendo los elementos en la cola
            clon.poner(aux.getElem());
            aux = aux.getEnlace();
        }
        return clon;
    }

    public String toString() {
        //Metodo que permite mostrar un string con todos los datos de la cola
        String res = "[";
        Nodo aux = this.frente;
        while (aux != null) {
            //Guarda todos los datos de la cola en el string
            res += aux.getElem().toString();
            if (aux.getEnlace() != null) {
                res += ",";
            }
            aux = aux.getEnlace();
        }
        return res + "]";
    }

}
