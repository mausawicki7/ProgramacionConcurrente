/*
 * Mauricio Sawicki
 */
package TPO2Obligatorio.SemaforosDePaso;

/**
 *
 * @author mausa
 */
public class HiloT2 implements Runnable {

    private String nombre;
    private Operaciones miOperacion;
    private int b, z;

    public HiloT2(String nombre, int valorZ, Operaciones unaOp) {
        this.nombre = nombre;
        this.miOperacion = unaOp;
        this.b = 0;
        this.z = valorZ;
    }

    public void run() {
       // miOperacion.instruccionS2(nombre, z);
   //     miOperacion.instruccionS3(b);
    }
    

}
