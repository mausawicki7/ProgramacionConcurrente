package TP5.TrenTuristico;

/**
 *
 * @author mausa
 */
public class Main {
    public static void main(String[] args) {
        
        int cantPasajeros = 10;
        Tren tren = new Tren(5);
        
        
        Vendedor v1 = new Vendedor(tren);
        Thread vendedor = new Thread(v1, "Vendedor");
        vendedor.start();
        
        
        ControlTren c = new ControlTren(tren);
        Thread control = new Thread(c, "Control");
        control.start();
        
        
        for (int i = 0; i <= cantPasajeros; i++) {
            
           Pasajero p = new Pasajero(tren);
           Thread pasajero = new Thread(p, "Pasajero " + i);
           pasajero.start();
            
            
        }
        
        
        
    }
}
