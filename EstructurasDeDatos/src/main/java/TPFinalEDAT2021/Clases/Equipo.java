/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Clases;

/**
 *
 * @author mausa
 */
public class Equipo {
    private String nombreEquipo;
    private int puntajeExigido;
    private int puntajeTotal;
    private String habActual;
    private int puntajeActual;

    public Equipo(String nombreEquipo, int puntajeExigido, String habActual) {
        this.nombreEquipo = nombreEquipo;
        this.puntajeExigido = puntajeExigido;
        this.puntajeTotal = 0;
        this.habActual = habActual;
        this.puntajeActual = 0;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public int getPuntajeExigido() {
        return puntajeExigido;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public String getHabActual() {
        return habActual;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setPuntajeExigido(int puntajeExigido) {
        this.puntajeExigido = puntajeExigido;
    }

    public void setHabActual(String habActual) {
        this.habActual = habActual;
    }

    public void setPuntajeActual(int puntajeActual) {
        this.puntajeActual = puntajeActual;
    }
    
}
