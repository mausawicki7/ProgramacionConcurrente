/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private void atender(){
        System.out.println("Ok, sentate en la silla pibe asi te corto la peluca.");
        try{
            Thread.sleep((int) (Math.random() * 200));
        }catch(InterruptedException e){
            System.out.println("Me interrumpieron mi trabajo.");
        }
    }

    @Override
    public void run() {
        int cuantos = 0;
        System.out.println("Soy el barbero "+nombre);
        
        while(true){
            miBarberia.esperarCliente(nombre);
            this.atender();        
            miBarberia.terminarAtencion();
            cuantos++;
            System.out.println("Atendi a "+cuantos+" clientes.");
        }
    }
    
}
