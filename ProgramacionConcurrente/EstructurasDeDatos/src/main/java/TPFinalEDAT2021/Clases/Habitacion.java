/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Clases;

/**
 *
 * @author mausa
 */
public class Habitacion {

    private String nombre;
    private int codigo;
    private int planta;
    private int mtsCuadrados;
    private boolean haySalida;

    public Habitacion(String nombre, int codigo, int planta, int mtsCuadrados, boolean haySalida) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.planta = planta;
        this.mtsCuadrados = mtsCuadrados;
        this.haySalida = false;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getPlanta() {
        return planta;
    }

    public int getMtsCuadrados() {
        return mtsCuadrados;
    }

    public boolean isHaySalida() {
        return haySalida;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public void setMtsCuadrados(int mtsCuadrados) {
        this.mtsCuadrados = mtsCuadrados;
    }

    public void setHaySalida(boolean haySalida) {
        this.haySalida = haySalida;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "Nombre=" + nombre + ", Codigo=" + codigo + ", Planta=" + planta + ", mtsCuadrados=" + mtsCuadrados + ", Existe una salida=" + haySalida + '}';
    }
    
}
