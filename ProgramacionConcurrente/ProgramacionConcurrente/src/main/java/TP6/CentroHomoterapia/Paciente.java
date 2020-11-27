/*
 * Mauricio Sawicki
 */
package TP6.CentroHomoterapia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Paciente implements Runnable {
    
    // Para saber si quiere sentarse o no
    private boolean quiereSentarse;
    //Para saber si pudo efectivamente tomar una silla o no
    private boolean tomoSilla;
    private boolean tomoRevista;
    private Centro centro;
    //Para respetar el orden de llegada
    private int turno;

    public Paciente(Centro centro, int sentarse) {
        this.centro = centro;
        this.quiereSentarse = true;

    }

    public void run() {
        
        //Obtiene el turno en el cual será atendido
        turno= centro.entrarSacarTurno();
        
        //Intenta tomar camilla
        if (centro.tomarCamilla(turno)) {
            this.sacarseSangre();
            centro.finalizar();
        } else {
            //El paciente se sienta o se queda parado según quiera
            tomoSilla = centro.esperar(quiereSentarse);
            //Trata de tomar revista
            tomoRevista = centro.tomarRevista(turno);
            //Se atiende y deja la silla si la tomo y la revista si la tomo
            centro.atenderse(turno,tomoSilla, tomoRevista);
            this.sacarseSangre();
            //Sale de la camilla
            centro.finalizar();
        
        }
    }

    private void sacarseSangre(){
   
        try {
            System.out.println(Thread.currentThread().getName() + " se está realizando uan extracción...");
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
    
}
