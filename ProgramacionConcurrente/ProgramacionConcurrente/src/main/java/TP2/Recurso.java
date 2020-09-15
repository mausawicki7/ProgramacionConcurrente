package TP2;

/**
 *
 * @author mausa
 */
public class Recurso {
    static void uso(){
        Thread t = Thread.currentThread();
        System.out.println("en Recurso soy: "+t.getName());
}
}