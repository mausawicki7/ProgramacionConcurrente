/*
 * Mauricio Sawicki
 */
package EjTeoriaSaludoJefe;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {
        int cantEmpleados = 15;

        Oficina oficina = new Oficina(cantEmpleados);

        Jefe jefe = new Jefe(oficina);
        Thread j = new Thread(jefe);
        j.start();

        for (int i = 1; i <= cantEmpleados; i++) {
            Personal p = new Personal(oficina);
            Thread personal = new Thread(p, "Empleado " + i);
            personal.start();
        }

    }

}
