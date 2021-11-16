/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
 */
package ActividadObligatoria3ConditionLocks2;

public class Test {

    public static void main(String[] args) {
        int cantMaxMuebles = 8;
        int cantCarpinterosC1 = 1;
        int cantCarpinterosC2 = 1;
        int cantCarpinterosC3 = 1;
        int cantEnsambladores = 1;

        Carpinteria carpinteria = new Carpinteria(cantMaxMuebles);

        //Carpinteros ensambladores
        for (int i = 1; i <= cantEnsambladores; i++) {
            Ensamblador e = new Ensamblador(carpinteria, "Ensamblador_" + i);
            Thread ensambladores = new Thread(e);
            ensambladores.start();
        }
        
        //Carpinteros C1
        for (int i = 1; i <= cantCarpinterosC1; i++) {
            Carpintero c1 = new Carpintero(carpinteria, "Carpintero1_" + i, 1);
            Thread carpinteros1 = new Thread(c1);
            carpinteros1.start();
        }

        //Carpinteros C2
        for (int i = 1; i <= cantCarpinterosC2; i++) {
            Carpintero c2 = new Carpintero(carpinteria, "Carpintero2_" + i, 2);
            Thread carpinteros2 = new Thread(c2);
            carpinteros2.start();
        }

        //Carpinteros C3
        for (int i = 1; i <= cantCarpinterosC3; i++) {
            Carpintero c3 = new Carpintero(carpinteria, "Carpintero3_" + i, 3);
            Thread carpinteros3 = new Thread(c3);
            carpinteros3.start();
        }

    }
}




