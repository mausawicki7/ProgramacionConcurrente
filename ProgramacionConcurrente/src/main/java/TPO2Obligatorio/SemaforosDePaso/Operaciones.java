/*
 * Mauricio Sawicki
 */
package TPO2Obligatorio.SemaforosDePaso;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Operaciones {

    private Semaphore semDePasoS3, semDePasoS4;
    private Semaphore s3, s4;
    private int a=0, b=0, c;

    public Operaciones() {
        semDePasoS4 = new Semaphore(0, true);
        semDePasoS3 = new Semaphore(0, true);
        s3 = new Semaphore(1, true);
        s4 = new Semaphore(1, true);
    }

    public void instruccionS1(String nombre, int valorX, int valorY) throws InterruptedException {
        a = valorX + valorY;
        
      //  semDePaso.release();
       // s3semDePaso.acquire();
    }

    public void instruccionS2(String nombre, int valorZ) throws InterruptedException {
        b = valorZ - 1;
        
      //  semDePaso.release();
       // s3semDePaso.acquire();
        
    }

    public void instruccionS3() throws InterruptedException {
        
        int c = 0;
        c = a - b;
        s4.acquire();
    }

}
