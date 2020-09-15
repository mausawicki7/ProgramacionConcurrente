package TP2;

/**
 *
 * @author mausa
 */
class ThreadTesting {

    public static void main(String[] args) {
        Thread miHilo = new MiEjecucion();
        miHilo.start();
        System.out.println("En el main");
    }
}