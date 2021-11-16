/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioLinkedTransferQueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Fernando Iraira <fmiraira@gmail.com>
 */
public class Rio {

    LinkedTransferQueue cruce = new LinkedTransferQueue();

    private Semaphore semIniciarViaje = new Semaphore(0);
    private Semaphore semPuedeEntrar = new Semaphore(1);
    private Semaphore semLugares;
    private Semaphore semBajarTransbordador = new Semaphore(0);
    private Semaphore semVolver = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private Semaphore sePuedeBajar = new Semaphore(0, true);
    private int cantAutosABordo = 0;
    private int cantLugares;


    public Rio(int cantLugares) {
        this.cantLugares = cantLugares;
        this.semLugares = new Semaphore(cantLugares);
    }

    public void entrarATransbordador(Auto auto) {
        try {
            //Verifica si tiene lugar para entrar, si es asi, ingresa, sino queda bloqueado

            //Con esto controlo la cantidad de autos permitidos a la vez
            this.semLugares.acquire();

            //Controlo que ingresen de a 1
            this.semPuedeEntrar.acquire();

            System.out.println(auto.getName() + " pudo entrar al buque");
            //Se suma a la cola y queda bloqueado
            cruce.add(auto);
            
            Thread.sleep(1000);

            this.semPuedeEntrar.release();

            this.mutex.acquire();
            
            this.cantAutosABordo++;

            if (this.cantAutosABordo == this.cantLugares) {
                this.semIniciarViaje.release();
            }
            this.mutex.release();
            this.sePuedeBajar.acquire();
            
        } catch (InterruptedException e) {
        }
    }

    public void iniciarViaje() {
        try {
            this.semIniciarViaje.acquire();

            System.out.println(Thread.currentThread().getName() + " esta lleno, inicia el viaje.");

        } catch (InterruptedException e) {
        }

    }

    public void volver() {
        try {
            System.out.println(Thread.currentThread().getName() + " llegó a destino, esperando a que se bajen los autos.");
            this.semBajarTransbordador.release();

            this.semVolver.acquire();

            System.out.println(Thread.currentThread().getName() + " esta realizando el viaje de vuelta...");

            //Simula el tiempo que le toma al buque para volver
            Thread.sleep(5000);

            System.out.println(Thread.currentThread().getName() + " retorno a su lugar de origen.");

            this.semLugares.release(this.cantLugares);
        } catch (InterruptedException e) {
        }
    }

    public void bajarAuto() {
        try {
            this.semBajarTransbordador.acquire();
            
            //Mientras hayan autos en la LinkedTransferQueue, sacarlos
            while (!cruce.isEmpty()) {
                Auto a = (Auto) this.cruce.remove();
                System.out.println("Descendió del buque " + a.getName());
                this.sePuedeBajar.release();
                Thread.sleep(1000);
            }
            this.cantAutosABordo = 0;
        } catch (InterruptedException e) {
        }
        
        semVolver.release();
    }

}
