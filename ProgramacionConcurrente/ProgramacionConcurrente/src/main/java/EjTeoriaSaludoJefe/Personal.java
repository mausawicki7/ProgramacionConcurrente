/*
 * Mauricio Sawicki
 */
package EjTeoriaSaludoJefe;

/**
 *
 * @author mausa
 */
public class Personal implements Runnable {

    private Oficina oficina;

    public Personal(Oficina oficina) {
        this.oficina = oficina;
    }

    public void run() {
        this.oficina.entrar();
        this.oficina.saludoEmpleado();
    }

}
