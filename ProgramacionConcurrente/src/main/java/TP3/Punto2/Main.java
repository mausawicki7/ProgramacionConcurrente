package TP3.Punto2;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        Jugador player = new Jugador();

        Thread[] hiloOrcos = new Thread[4];
        Thread[] hiloCurandero = new Thread[2];

        for (int i = 0; i < hiloOrcos.length; i++) {
            Orco unOrco = new Orco(player, "Orco "+i); 
            hiloOrcos[i] = new Thread(unOrco);
            hiloOrcos[i].start();
        }

        for (int i = 0; i < hiloCurandero.length; i++) {
            Curandero unCurandero = new Curandero(player, "Curandero "+i);
            hiloCurandero[i] = new Thread(unCurandero);
            hiloCurandero[i].start();
        }

    }

}
