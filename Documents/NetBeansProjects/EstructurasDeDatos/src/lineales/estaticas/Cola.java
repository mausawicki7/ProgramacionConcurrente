package lineales.estaticas;

/**
 *
 * @author Mauricio Sawicki
 */
public class Cola {

    private Object arreglo[];
    private int frente;
    private int fin;
    private final int TAM = 10;

    public Cola() {
        this.arreglo = new Object[this.TAM];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean esVacia() {
        return this.frente == this.fin;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.esVacia()) { //La cola esta vacía reporta error
            exito = false;
        } else { // Al menos hay 1 elemento, avanza frente de manera circular
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAM;
        }
        return exito;
    }

    public boolean poner(Object elem) {
        boolean exito = true;
        // Si el frente es distinto del final (avanzado de manera circular) poné el elemento.
        // La condicion frente = final es cola vacia
        // Avanzar de forma circular significa que cuando fin este por llegar al final del arreglo
        // se va poner en 0 nuevamente.
        if (this.frente != (this.fin + 1) % this.TAM) {
            this.arreglo[this.fin] = elem;
            this.fin = (this.fin + 1) % this.TAM;

        } else {
            exito = false;
        }

        return exito;
    }

    public Object obtenerFrente() {
        return this.arreglo[frente];
    }
       

    public void vaciar() {
        if (!this.esVacia()) {
            for (int i = 0; i < this.TAM; i++) {
                this.arreglo[i] = null;
            }
        }
        this.frente = 0;
        this.fin = 0;
    }

    public Cola clone() {
        Cola clon = new Cola();

        clon.frente = this.frente;
        clon.fin = this.fin;
        //Aca estoy llamando al clone() de arreglos. Que lo que hace es clonar los elementos justamente.
        clon.arreglo = this.arreglo.clone();

        return clon;
    }

    public String toString() {
        String st = "";
        int aux = this.frente;

        if (!esVacia()) {

            while (aux != this.fin) {
                st = st + this.arreglo[aux].toString();
                aux = (aux + 1) % this.TAM;
                st = st + " , ";
            }
        } else {
            st = "Cola Vacia";
        }

        return st;
    }
    


}
