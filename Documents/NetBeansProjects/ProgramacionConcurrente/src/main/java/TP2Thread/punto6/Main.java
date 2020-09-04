
package TP2Thread.punto6;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {
    
        Cliente c1 = new Cliente("CLIENTE 1", new int[]{2, 2, 1, 5, 2, 3});
        Cliente c2 = new Cliente("CLIENTE 2", new int[]{1, 3, 5, 1, 1});

        long initialTime = System.currentTimeMillis();
        Cajera cajera1 = new Cajera("Cajera 1", c1, initialTime);
        Cajera cajera2 = new Cajera("Cajera 2", c2, initialTime);

        Thread t1 = new Thread(cajera1);
        Thread t2 = new Thread(cajera2);

        t1.start();
        t2.start();
    }

    }

    

