
package TP2Thread.punto6;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cliente 1", new int[]{2, 2, 1, 5, 2, 3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[]{1, 3, 5, 1, 1});

     long initialTime = System.currentTimeMillis();

        Cajera cajera1 = new Cajera("cajera 1", c1, initialTime);
        Cajera cajera2 = new Cajera("cajera2", c2, initialTime);

        Thread t1 = new Thread(cajera1);
        Thread t2 = new Thread(cajera2);

        t1.start();
        t2.start();

    }

    
}
