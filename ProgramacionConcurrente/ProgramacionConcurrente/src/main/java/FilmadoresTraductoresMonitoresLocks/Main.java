/*
 * Mauricio Sawicki
 */
package FilmadoresTraductoresMonitoresLocks;

import java.util.Random;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int cantFilmadores = 1;
        int cantTraductores = 2;
        int cantSocios = 3;

        Serie serie = new Serie(); 

        for (int i = 1; i <= cantFilmadores; i++) {
            Filmador f1 = new Filmador(serie);
            Thread hiloFilmadores = new Thread(f1, "Filmador: ");
            hiloFilmadores.start();
        }

        for (int i = 1; i <= cantTraductores; i++) {
            Traductor t1 = new Traductor(serie);
            Thread hiloTraductores = new Thread(t1, "Traductor " + i + ":");
            hiloTraductores.start();
        }

        for (int i = 1; i <= cantSocios; i++) {
            boolean eleccion = (randomHasta(2) == 1);
            Socio unSocio = new Socio(serie, eleccion);
            Thread hiloSocios = new Thread(unSocio, "Socio " + i + ":");
            hiloSocios.start();
        }

    }

    public static int randomHasta(int maximo) {
        Random rand = new Random();
        return rand.nextInt(maximo + 1);
    }

}
