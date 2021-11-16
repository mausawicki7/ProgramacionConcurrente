/*
 * Mauricio Sawicki
 */
package TPO2Obligatorio.PlantaEmbotelladora;

/**
 *
 * @author mausa
 */
public class Test {

    public static void main(String[] args) {

        int capacidadCaja = 10;

        Caja c = new Caja(capacidadCaja);

        Embotellador unEmbotellador = new Embotellador(c);
        Thread embotellador = new Thread(unEmbotellador, "Embotellador");
        embotellador.start();

        Empaquetador unEmpaquetador = new Empaquetador(c);
        Thread empaquetador = new Thread(unEmpaquetador, "Empaquetador");
        empaquetador.start();

    }
}
