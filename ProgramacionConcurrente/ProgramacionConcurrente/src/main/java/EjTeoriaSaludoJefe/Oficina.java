/*
 * Mauricio Sawicki
 */
package EjTeoriaSaludoJefe;

/**
 *
 * @author mausa
 */
public class Oficina {

    private int cantActualEmpleados;
    private int cantTotalEmpleados;
    private boolean jefeSaludo = false;

    public Oficina(int cantTotal) {
        this.cantActualEmpleados = 0;
        this.cantTotalEmpleados = cantTotal;
    }

    public synchronized void saludoJefe() {
        System.out.println("Soy el jefe, entré a la oficina.");
        while (this.cantActualEmpleados != this.cantTotalEmpleados) {
            try {
                this.wait();
            } catch (Exception e) {}
        }

        System.out.println("JEFE> Buenos dias!");
        this.jefeSaludo = true;
        this.notifyAll();
    }

    public synchronized void entrar() {
        System.out.println(Thread.currentThread().getName() + " entro a la oficina.");
        this.cantActualEmpleados++;

        if (this.cantActualEmpleados == this.cantTotalEmpleados) {
            this.notifyAll();
        }
    }

    public synchronized void saludoEmpleado() {
        while ( !this.jefeSaludo) {
            try {
                this.wait();
            } catch (Exception e) {}
        }

        System.out.println(Thread.currentThread().getName() + "> Buenos dias jefe!");
    }
}
