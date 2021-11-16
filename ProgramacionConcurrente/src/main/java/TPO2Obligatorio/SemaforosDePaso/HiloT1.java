/*
 * Mauricio Sawicki
 */
package TPO2Obligatorio.SemaforosDePaso;

/**
 *
 * @author mausa
 */
public class HiloT1 implements Runnable {

    private String nombre;
    private Operaciones miOperacion;
    private int a, x, y;

    public HiloT1(String nombre, int valorX, int valorY, Operaciones unaOp) {
        this.nombre = nombre;
        this.miOperacion = unaOp;
        this.a = 0;
        this.x = valorX;
        this.y = valorY;
    }

    public void run() {
      //  this.instruccionS1(nombre,x,y);
      //  miOperacion.instruccionS3(a);
    }
    

}
