package TP2;

/**
 *
 * @author mausa
 */
public class PingPong extends Thread {

    private int delay;            //info de las iteraciones
    private Dato miD;          // para mantener el total
    private int miCta = 0;    // para contar mis iteraciones

// constructor 1, que refina al constructor heredado de Thread
    public PingPong(String cartel, int cantMs) {
        super(cartel);
        this.delay = cantMs;
    }

// constructor 2, que utiliza al constructor 1
    public PingPong(String cartel, int cantMs, Dato dd) {
        this(cartel, cantMs);
        this.miD = dd;
    }

    public void run() {

        for (int i = 1; i < delay * 2; i++) {
// System.out.print(this.getName() + " ");
            miCta++;
            this.miD.contar();

        } // del for
        System.out.println(miCta + " veces " + this.getName());

    } // del run

    public static void main(String[] args) {

        Dato cuenta = new Dato();
        PingPong t1 = new PingPong("PING", (int) (Math.random() * 2300), cuenta);
        PingPong t2 = new PingPong("PONG", (int) (Math.random() * 2000), cuenta);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {

        }

        System.out.println(Thread.currentThread() + " chau-chau.adios");
        System.out.println(" dato " + cuenta.obtenerValor());

    }
}