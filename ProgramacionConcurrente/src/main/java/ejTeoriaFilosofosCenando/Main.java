/*
 * Mauricio Sawicki
 */
package ejTeoriaFilosofosCenando;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Preparando mesa para la cena..");
        Mesa mesa = new Mesa(5); //Mesa con 5 filosofos
        Thread laCena = new Thread(mesa);
        System.out.println("Todo listo, a comer.");
        laCena.start();
        laCena.join();
    }
}
