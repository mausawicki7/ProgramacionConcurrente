/*
 * Mauricio Sawicki
 */
package PrimerParcial.TrenTuristico;

/**
 *
 * @author mausa
 */
public class Test {
        public static void main(String[] args) {
        
        int cantPasajeros = 20, capMax = 5;
        Tren tren = new Tren(capMax);  
        
        Vendedor v1 = new Vendedor(tren);
        Thread vendedor = new Thread(v1, "Vendedor");
        vendedor.start();
        
        
        ControlTren c = new ControlTren(tren, capMax);
        Thread control = new Thread(c, "Control");
        control.start();
        
        
        for (int i = 0; i <= cantPasajeros; i++) {
           Pasajero p = new Pasajero(tren, "Pasajero " + i);
           Thread pasajero = new Thread(p);
           pasajero.start();
            
            
        }
        
        
        
    }
}
