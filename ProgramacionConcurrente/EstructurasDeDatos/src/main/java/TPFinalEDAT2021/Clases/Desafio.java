/*
 * Mauricio Sawicki
 */
package TPFinalEDAT2021.Clases;

/**
 *
 * @author mausa
 */
public class Desafio {
    private int puntaje;
    private String nombre;
    private char tipo;

    public Desafio(int puntaje, String nombre, char tipo) {
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public char getTipo() {
        return tipo;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
    
}
