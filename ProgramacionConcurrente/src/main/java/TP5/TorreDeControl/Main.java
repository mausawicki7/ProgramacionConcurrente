package TP5.TorreDeControl;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {
        //Esta variable indica cada cuantos aterrizajes puede despegar un avion
        int cantAvionesConPrioridad = 10;
        //Estos son los aviones que estan en tierra
        int cantDespegues = 15;
        //Estos son los aviones que estan volando
        int cantAterrizajes = 12;
        
        TorreDeControl unaTorre = new TorreDeControl(cantAterrizajes, cantAvionesConPrioridad);
        //Creo los aviones que estan en tierra
        for (int i = 0; i < cantDespegues; i++) {
            Avion unAvion = new Avion(unaTorre, 2);
            Thread a = new Thread(unAvion, "Avion D-" + i);
            a.start();
        }
        //Creo los aviones que estan volando
        for (int i = cantDespegues; i < cantAterrizajes + cantDespegues; i++) {
            Avion unAvion = new Avion(unaTorre,1);
            Thread a = new Thread(unAvion, "Avion A-" + i);
            a.start();
 
        }

    }

}
