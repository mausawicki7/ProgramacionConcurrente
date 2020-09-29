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
public class Cliente extends Persona implements Runnable {
    
    Barberia laBarberia;
    
    public Cliente(String nombre, Barberia bd){
        super(nombre);
        laBarberia = bd;
    }

    private void caminarHastaBarberia(){
        System.out.println("Soy "+nombre+" y estoy caminando hasta la barberia "+laBarberia.miNombre);
        
        try{
            Thread.sleep((int) (Math.random() * 300));
        }catch(InterruptedException e){
            
        }
    }    

    @Override
    public void run() {
       this.caminarHastaBarberia();
        if(laBarberia.entrar(nombre)){
            laBarberia.solicitarCorte(nombre);
            System.out.println("Soy "+nombre+" ya me atendieron, me voy..");
            laBarberia.salir(nombre);
        }else{
            System.out.println("Soy "+nombre+" y el barbero me dice que esta ocupado..");
        }
    }
    
}
