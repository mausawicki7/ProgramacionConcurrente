package TP5.TrenTuristico;

/**
 *
 * @author mausa
 */
public class Vendedor implements Runnable {

    Tren t1;

    public Vendedor(Tren t1) {
        this.t1 = t1;
    }

    @Override
    public void run() {

        while (true) {

            t1.venderTickets();

        }

    }

}
