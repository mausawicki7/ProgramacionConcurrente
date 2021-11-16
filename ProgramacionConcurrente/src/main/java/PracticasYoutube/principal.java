package PracticasYoutube;

/**
 *
 * @author mausa
 */
public class principal implements Runnable {

    private static int cont = 0;
    private static Object obj = new Object();

    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 0; i < 20000; i++) {

                cont++;
            }
        }

    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int numNucleos = runtime.availableProcessors();

        //Declaro un arreglo con tantos hilos como tenga mi procesador
        Thread[] hilos = new Thread[numNucleos];

        for (int i = 0; i < hilos.length; i++) {
            Runnable run = new principal();
            //Le mando el objeto Runnable a la clase Thread para poder lanzar los hilos
            hilos[i] = new Thread(run);
            //Los lanzo
            hilos[i].start();

        }

        for (int i = 0; i < hilos.length; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(cont);
    }

}
