package EjTeoriaBarberoDormilon;

/**
 *
 * @author mausa
 */
public class Barbero extends Persona implements Runnable {
    
    private Barberia miBarberia;
    
    public Barbero(String nombre, Barberia bd){
        super(nombre);
        miBarberia = bd;
        
    }
    
    public boolean atender(){
        boolean atendiendo = true;
        System.out.println("Ok, sentate en el sillon asi te corto el pelo.");
        try{
            Thread.sleep((int) (Math.random() * 200));
        }catch(InterruptedException e){
            System.out.println("Me interrumpieron mi trabajo.");
        }
        return atendiendo;
    }

    @Override
    public void run() {
        int cuantos = 0;
        System.out.println("Soy el barbero "+nombre);
        
        //Espera activa
        while(true){
            miBarberia.esperarCliente(nombre);
            this.atender();        
            miBarberia.terminarAtencion();
            cuantos++;
            System.out.println("Soy el barbero atendi a "+cuantos+" clientes.");
        }
    }
    
}
