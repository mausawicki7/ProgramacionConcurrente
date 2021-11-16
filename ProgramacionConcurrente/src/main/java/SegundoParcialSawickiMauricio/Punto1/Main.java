/*
 * Mauricio Sawicki
 */
package SegundoParcialSawickiMauricio.Punto1;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int cantFilmadores = 1;
        int cantTraductores = 2;
        int cantSocios = 4;
 

        Serie serie = new Serie("Concurrente la saga");

        for (int i = 1; i <= cantFilmadores; i++) {
            Filmador f1 = new Filmador(serie);
            Thread hiloFilmadores = new Thread(f1, "Filmador: ");
            hiloFilmadores.start();
        }

        for (int i = 1; i <= cantTraductores; i++) {
            Traductor t1 = new Traductor(serie);
            Thread hiloTraductores = new Thread(t1, "Traductor: ");
            hiloTraductores.start();
        }

        for (int i = 1; i <= cantSocios; i++) {
            Socio unSocio = new Socio(serie);
            Thread hiloSocios = new Thread(unSocio, "Socio "+i+":");
            hiloSocios.start();
        }

    }
}
