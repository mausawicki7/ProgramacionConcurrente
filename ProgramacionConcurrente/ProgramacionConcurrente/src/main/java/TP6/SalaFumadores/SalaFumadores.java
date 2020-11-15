/*
 * Mauricio Sawicki
 */
package TP6.SalaFumadores;

/**
 *
 * @author mausa
 */
public class SalaFumadores {

    //para saber si hay alguien fumando
    private boolean fumando = false;
    private boolean ingredienteEnMesa = false;
    private int turno;
    
    public synchronized void colocarIngrediente(int unIngrediente) throws InterruptedException{
        while(fumando || ingredienteEnMesa){ //Si hay alguien fumando o un ingrediente en la mesa, espera.
            System.out.println("Soy el agente, aun no puedo poner el ingrediente en la mesa.");
            this.wait();
        }
        ingredienteEnMesa = true;
        System.out.println("Soy el agente ya coloqué el ingrediente.");
        //Seteo la variable turno con la ID del ingrediente entrado por parametro
        //Esto es para que solo fume el fumador que tenga su id == unIngrediente 
        turno = unIngrediente;
        //Notifico a todos los fumadores que estan esperando a tomar un ingrediente.
        this.notifyAll();       
    }
    
    
    
  
    public synchronized void entraFumar(int idFumador) throws InterruptedException{
        while(fumando || idFumador != turno){ //Si hay alguien fumando o el turno (que es unIngrediente) != idFumador
            System.out.println("Soy el fumador "+idFumador+" aun no puedo fumar.");
            this.wait();
        }
        fumando = true;
        System.out.println("Soy el fumador "+idFumador+" estoy fumando.");
        
        //Agarra el ingrediente de la mesa y setea la variable en false para que el agente se entere que pueda poner mas ingredientes
        ingredienteEnMesa = false;
        this.notifyAll();
    
        
    }
    
 public synchronized void terminaFumar(int id) {
        //Deja de fumar y notifica
        System.out.println("Fumador " + id + " terminó de fumar");
        fumando = false;
        this.notifyAll();

    }
    
}
