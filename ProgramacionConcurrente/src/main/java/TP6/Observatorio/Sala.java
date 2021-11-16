/*
 * Mauricio Sawicki
 */
package TP6.Observatorio;

/**
 *
 * @author mausa
 */
public class Sala {
    
    private int capacidadMaxActual;
    private int capacidadMax;
    private int capacidadReducida;
    private int cantidadActualPersonas = 0;
    private int cantSillaRuedas = 0;
    private int turno = 0;

 //0 VISITANTE, 1 MANTENIMIENTO, 2 INVESTIGADOR
    public Sala(int capacidadMax, int capacidadRed) {
        this.capacidadMax = capacidadMax;
        this.capacidadMaxActual = this.capacidadMax;
        this.capacidadReducida = capacidadRed;
    }

    public synchronized void entrarVisitante(boolean tieneSilla) {
        while (this.cantidadActualPersonas >= this.capacidadMaxActual || this.turno != 0) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }

        System.out.println(Thread.currentThread().getName() + " pudo entrar al observatorio.");
        this.cantidadActualPersonas++;

        if (tieneSilla) {
            if (this.cantSillaRuedas == 0) {
                this.capacidadMaxActual = this.capacidadReducida;
                System.out.println("La capacidad ahora es de " + this.capacidadMaxActual);
            }
            this.cantSillaRuedas++;
        }
    }

    public synchronized void salir(boolean tieneSilla) {
        if (tieneSilla) {
            this.cantSillaRuedas--;

            if (this.cantSillaRuedas == 0) {
                this.capacidadMaxActual = this.capacidadMax;
            }
        }

        this.cantidadActualPersonas--;
        System.out.println(Thread.currentThread().getName() + " salió del observatorio.");

        if (this.cantidadActualPersonas == 0) {
            this.turno = 1;
            this.notifyAll();
        }
    }

    public synchronized void entrarMantenimiento() {
        while (this.cantidadActualPersonas >= this.capacidadMaxActual || this.turno != 1) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }

        System.out.println(Thread.currentThread().getName() + " entro al observatorio para realizar mantenimiento.");
        this.cantidadActualPersonas++;
    }

    public synchronized void salirMantenimiento() {
        this.cantidadActualPersonas--;

        System.out.println(Thread.currentThread().getName() + " salió del observatorio.");
        System.out.println(" Cantidad personas actual: " + this.cantidadActualPersonas);
        if (this.cantidadActualPersonas == 0) {
            this.turno = 2;
            this.notifyAll();
        } else {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
    }

    public synchronized void entrarInvestigador() {
        while (this.cantidadActualPersonas >= 1 || this.turno != 2) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }

        System.out.println(Thread.currentThread().getName() + " entro al observatorio para realizar su investigacion.");
        this.cantidadActualPersonas++;
    }

    public synchronized void salirInvestigador() {
        this.cantidadActualPersonas--;
        this.turno = 0;
        this.notifyAll();
    }

 
}

