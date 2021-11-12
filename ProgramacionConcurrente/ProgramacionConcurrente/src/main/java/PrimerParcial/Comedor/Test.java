/*
 * Mauricio Sawicki
 */
package PrimerParcial.Comedor;

/**
 *
 * @author mausa
 */
public class Test {

    public static void main(String[] args) {
        int cantPerros = 30, cantGatos = 20, capMax = 10;
        Comedor comedor = new Comedor(capMax);     
        ControlTurnos c = new ControlTurnos(comedor);
        Thread control = new Thread(c);
        control.start();
        
        
        for (int i = 1; i <= cantPerros; i++) {
            Perro p1 = new Perro("Perro " + i, comedor);
            Thread perros = new Thread(p1, "Perro");
            perros.start();
        }
        
        for (int i = 1; i <= cantGatos; i++) {
            Gato g1 = new Gato("Gato " + i, comedor);
            Thread gatos = new Thread(g1, "Gato");
            gatos.start();
        }

    }

}
