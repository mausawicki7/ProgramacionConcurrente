
package EstructurasLineales.dinamicas;


/**
 *
 * @author Mauricio Sawicki
 */
public class Pila {

    private Nodo tope;

    public Pila() {
        this.tope = null;
    }
    //Retorno true en caso de estar vacia la pila
    public boolean esVacia() {
        return this.tope == null;
    }

    public void vaciar() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        //crea un nuevo Nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        //se actualiza el tope para que apunte al nodo nuevo, es decir el nuevo tope es el tope del nuevo nodo
        this.tope = nuevo;
        //nunca hay error de pila llena por eso siempre se devuelve true
        return true;
    }

    public boolean desapilar() {
        boolean exito = false;
        //Si no estoy en el ultimo nodo (que su enlace apunta a null) HACER:
        if (this.tope != null) {
            // Al tope actual lo hago apuntar al enlace del nodo que esta debajo del actual
            // El nodo de mas arriba queda sin enlace entonces se lo lleva el Garbage Collectorm
            Nodo aux = this.tope.getEnlace();
            this.tope = aux;
            exito = true;
        }

        return exito;

    }

    public Object obtenerTope() {
        Object res = null;
        //Si no estoy en el ultimo nodo (que su enlace apunta a null) HACER:
        if (this.tope != null) {
            //Guardo en res el elemento que se encuentra en el tope y lo devuelvo
            res = this.tope.getElem();
        }
        return res;
    }

    @Override
    public Pila clone() {
        Pila pilaClon = new Pila();
        Nodo aux, aux2, aux3;
        
        if (this.tope != null) {
            aux = this.tope;
            pilaClon.tope = new Nodo(tope.getElem(), null);
            aux2 = pilaClon.tope;
            while (aux.getEnlace() != null) {
                aux = aux.getEnlace();
                aux3 = new Nodo(aux.getElem(), null);
                aux2.setEnlace(aux3);
                aux2 = aux2.getEnlace();

            }
        }

        return pilaClon;
    }

    @Override
    public String toString() {
        String s = "Pila vacia";
        if (this.tope != null) {
            Nodo aux = this.tope;
            s = "[";
            while (aux != null) {
                s += aux.getElem().toString();
                aux = aux.getEnlace();  
                if (aux != null)
                    s += ", ";
            }
            s += "]";
        }
        return s;
    }
}
