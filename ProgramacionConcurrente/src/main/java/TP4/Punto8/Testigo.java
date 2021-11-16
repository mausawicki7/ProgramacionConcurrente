package TP4.Punto8;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Testigo {

    private Semaphore[] turnos; //turnos es un arreglo de semaforos

    public Testigo(int cantCorredores) {
        turnos = new Semaphore[cantCorredores]; //inicializo el arreglo de tamaño cantCorredores
        turnos[0] = new Semaphore(1); //en la pos 0 del arreglo creo un semaforo con 1 permiso

        for (int i = 1; i < this.turnos.length; i++) { //seteo a los demas semaforos del arreglo con 0 permisos
            turnos[i] = new Semaphore(0);
        }
    }

    public void empezarACorrer(int numTurno) {
        try {
            this.turnos[numTurno].acquire(); 
        } catch (Exception e) {
        }
    }

    public void terminarDeCorrer(int numTurno) {
        if (numTurno < this.turnos.length - 1) { //compruebo esto para no pasarme del indice del arreglo
            this.turnos[numTurno + 1].release(); //libero al siguiente corredor
        } else {
            System.out.println("Finalizó la carrera!");
        }
    }

}
