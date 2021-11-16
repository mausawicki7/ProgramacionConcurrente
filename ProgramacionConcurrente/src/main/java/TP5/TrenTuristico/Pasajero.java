package TP5.TrenTuristico;

/**
 *
 * @author mausa
 */
public class Pasajero implements Runnable {
    
    private Tren t;

    public Pasajero(Tren t) {
        this.t = t;
    }
    
    
    public void run(){
    
        t.entrar();
        t.comprarTicket();
        t.viajar();
    
    
    }
    
    
    
    
}
