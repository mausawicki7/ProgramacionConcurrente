/*
 * Mauricio Sawicki
 */
package EjTeoriaSaludoJefe;

/**
 *
 * @author mausa
 */
public class Jefe implements Runnable {

    private Oficina oficina;

    public Jefe(Oficina oficina) {
        this.oficina = oficina;
    }

    public void run() {
        oficina.saludoJefe();
    }

}
