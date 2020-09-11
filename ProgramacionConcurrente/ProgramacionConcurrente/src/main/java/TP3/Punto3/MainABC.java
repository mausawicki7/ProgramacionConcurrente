package TP3.Punto3;

/**
 *
 * @author mausa
 */
public class MainABC {

    public static void main(String[] args) {
        
        Variable x = new Variable(0);
        
        ClaseA a = new ClaseA(x);
        ClaseB b = new ClaseB(x);
        ClaseC c = new ClaseC(x);   
        
        Thread[] hilo = new Thread[9];
        
        hilo[0] = new Thread(a);
        hilo[1] = new Thread(b);
        hilo[2] = new Thread(c);
        
        
        for (int i = 0; i <= 2; i++) {
            hilo[i].start();
        }
    }
}
