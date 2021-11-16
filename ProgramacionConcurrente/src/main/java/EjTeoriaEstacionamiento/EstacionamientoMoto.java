/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjTeoriaEstacionamiento;

/**
 *
 * @author mausa
 */
public class EstacionamientoMoto {
    
    private int cantActual = 0;
    private int cantMaxima;
    
    public EstacionamientoMoto(int cantMax){
        this.cantMaxima = cantMax;
        this.cantActual = 0;
        
    }

    public void setCantActual(int cantActual) {
        this.cantActual = cantActual;
    }

    public void setCantMaxima(int cantMaxima) {
        this.cantMaxima = cantMaxima;
    }

    
    public int getCantActual() {
        return cantActual;
    }

    public int getCantMaxima() {
        return cantMaxima;
    }
    
    public synchronized boolean ingresarMoto() throws InterruptedException{
        boolean res = cantActual < cantMaxima;
        
        if(res){
            System.out.println("Estacionando moto "+Thread.currentThread().getName());
            Thread.sleep(200);
            this.cantActual++;
            System.out.println("Moto "+Thread.currentThread().getName()+" estacionada.");
        }else{
            Thread.sleep(100);
            System.out.println("El estacionamiento se encuentra lleno. No se pueden estacioanr mas motos.");
        }
        return res;
        
    }
    
    public synchronized boolean retirarMoto() throws InterruptedException{
        boolean res = cantActual > 0;
        
        if(res){
            System.out.println("Retirando moto "+Thread.currentThread().getName());
            Thread.sleep(160);
            this.cantActual--;
            System.out.println("Moto "+Thread.currentThread().getName()+" retirada.");
        }else{
            Thread.sleep(100);
            System.out.println("No se pueden retirar mas motos ya que el estacionamiento está vacio..");
        }
        return res;
    }
    
}