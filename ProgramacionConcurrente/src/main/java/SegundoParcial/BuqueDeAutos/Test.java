/*
 * Mauricio Sawicki
 */
package SegundoParcial.BuqueDeAutos;

/**
 *
 * Buque de autos
Un transbordador o buque para transporte de vehículos permite pasar autos de un lado de
un río al otro. Los autos viajan por el lado este del río, cruzan el río en el transbordador y
continúan su viaje por el lado oeste (nunca vuelven). El transbordador tiene espacio para 10
autos y espera a estar lleno para cruzar el río. Cuando ha cruzado y descargado los coches,
vuelve vacío. Considere que los autos deben bajar de a uno por vez.
Considere en el transbordador operaciones ir y volver
- ir hace que el transbordador cruce con los autos
- volver lo hace volver vacío.
Se trata de implementar este problema con hilos, resolviendo la concurrencia con el empleo
de algún mecanismo de sincronización (monitor, semáforos, locks).
Nota 1: los autos NO DEBEN caerse al río
Nota2: Resolver el problema teniendo en cuenta que, en el caso en que se quiera
desbloquear a varios hilos debe hacerse en cadena, esto es, cada proceso debe
desbloquear al siguiente.
 */
public class Test {

    public static void main(String[] args) {

        //Cantidad de hilos pasajero.
        int cantAutos = 20;
        //Cantidad de lugares en el transbordador, maximo de autos.
        int cantLugares = 10;

        Transbordador t = new Transbordador(cantLugares);

        Control c = new Control(t);
        Thread control = new Thread(c);
        control.start();

        for (int i = 1;i <= cantAutos; i++) {
            Auto a = new Auto(t, "Auto: "+i);
            Thread hiloAutos = new Thread(a);
            hiloAutos.start();
        }
    }
}
