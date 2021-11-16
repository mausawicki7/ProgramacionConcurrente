/*
 * Mauricio Sawicki
 */
package PrimerParcial.CentroHomoterapia;

/**
 *
 * @author mausa
 */
public class Test {
    public static void main(String[] args) {
    int capacidadSalaEspera = 5;    
    Centro centro = new Centro(capacidadSalaEspera);
    Recepcionista r = new Recepcionista(centro);
    int cantDonantes = 10;
    Thread recepcionista = new Thread(r);
    recepcionista.start();
    
        for (int i = 1; i <= cantDonantes; i++) {
            Donante d1 = new Donante("Donante " + i, centro);
            Thread donantes = new Thread(d1, "Donante");
            donantes.start();
        }


    }
}
