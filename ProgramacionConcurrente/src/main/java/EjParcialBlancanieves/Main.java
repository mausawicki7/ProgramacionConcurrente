/*
 * Mauricio Sawicki
 */
package EjParcialBlancanieves;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int cantSillas = 4;
        int cantEnanos = 3;
        
        Mesa unaMesa = new Mesa(cantSillas);
        Blancanieves b = new Blancanieves(unaMesa);
        
        Thread hiloBlancanieves = new Thread(b, "Blancanieves");
        hiloBlancanieves.start();
        for (int i = 1; i <= cantEnanos; i++) {
            Enanito e1 = new Enanito(unaMesa);
            Thread hiloEnanitos = new Thread(e1, "Enanito " + i);
            hiloEnanitos.start();
        }

    }
}
