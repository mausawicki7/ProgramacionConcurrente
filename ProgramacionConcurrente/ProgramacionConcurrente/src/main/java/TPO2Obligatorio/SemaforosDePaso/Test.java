/*
 * Mauricio Sawicki
 */
package TPO2Obligatorio.SemaforosDePaso;

/**
 *
 * @author mausa
 */
public class Test {

    public static void main(String[] args) {
        int valorX = 2, valorY = 4, valorZ = 1;
        Operaciones op = new Operaciones();
        HiloT1 primerHilo = new HiloT1("Hilo T1", valorX, valorY, op);
        HiloT2 segundoHilo = new HiloT2("Hilo T2", valorZ, op);
        Thread t1 = new Thread(primerHilo);
        Thread t2 = new Thread(segundoHilo);
        t1.start();
        t2.start();
    }
}
