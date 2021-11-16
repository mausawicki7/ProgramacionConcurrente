/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto13;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Main {
       public static void main(String [] args){
        Confiteria unaConfiteria = new Confiteria("El cafecito");
        Mozo unMozo = new Mozo("Borges", unaConfiteria);
        
        Thread hiloMozo = new Thread(unMozo);
        hiloMozo.start();
        
        Thread [] hiloEmpleado = new Thread[6];
        
        for(int i = 0; i<hiloEmpleado.length; i++){
            Empleado unEmpleado = new Empleado("Empleado "+i, unaConfiteria);
            hiloEmpleado[i] = new Thread(unEmpleado);
            hiloEmpleado[i].start();
        }
        
        try{
            Thread.sleep((int) (Math.random() * 500));
            
        }catch(InterruptedException ex){
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
