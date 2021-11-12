/*
 * Mauricio Sawicki
 */
package ejTeoriaFilosofosCenando;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mausa
 */
public class Mesa implements Runnable {

    //Lista donde estan todos los tenedores
    private List<Tenedor> tenedores;
    //Lista donde estan todos los filosofos
    private List<Filosofo> filosofos;

    public Mesa(int cantFilosofos) {
        if (cantFilosofos < 2) {
            throw new IllegalArgumentException("Tiene que haber mas de un filosofo en la mesa!");
        }

        this.tenedores = new ArrayList<>();
        this.filosofos = new ArrayList<>();

        for (int i = 0; i < cantFilosofos; i++) {
            //Creo un nuevo tenedor
            Tenedor t = new Tenedor();
            //Agrego al arrayList de tenedores un nuevo tenedor
            tenedores.add(t);
        }
        
        for (int i = 0; i < cantFilosofos; i++) {
            int n = (i + 1) % cantFilosofos;
            Tenedor izquierdo = tenedores.get(i);
            Tenedor derecho = tenedores.get(n);  
            boolean esZurdo = (n == 0);

            Filosofo f = new Filosofo("Filosofo " + i , this, izquierdo, derecho, esZurdo);
            filosofos.add(f);

        }
    }

  public void run() {
        
    }

}
