/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compartido;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Fernando
 */
public class Buffer {

    private Queue<Integer> cola1, cola2;
    private boolean sacarDe1;
    private Lock lockIngresar, lockSacar;
    private Condition puedeSacar;

    public Buffer() {
        this.cola1 = new LinkedList();
        this.cola2 = new LinkedList();

        this.lockIngresar = new ReentrantLock(true);
        this.lockSacar = new ReentrantLock(true);

        this.puedeSacar = this.lockSacar.newCondition();
        this.puedeSacar = this.lockIngresar.newCondition();

        this.sacarDe1 = false;
    }

    public void sacarElemento() {
        int res;

        this.lockSacar.lock();
        try {

            while (!this.lockIngresar.tryLock()) {
                System.out.println("[Extractor] Libero el lock sacar");
                this.puedeSacar.await();
            }

            while (sacarDe1 && cola1.isEmpty() || !sacarDe1 && cola2.isEmpty()) {
                System.out.println("[Extractor] Suelto lock porque no hay elementos en la cola extraer");
                this.puedeSacar.await();
            }

            String cola;
            if (sacarDe1) {
                res = this.cola1.remove();
                cola = "cola 1";
            } else {
                res = this.cola2.remove();
                cola = "cola 2";
            }

            System.out.println(Thread.currentThread().getName() + " saco el elemento: " + res + " de la " + cola + ".");

            if (sacarDe1 && cola1.isEmpty() || !sacarDe1 && cola2.isEmpty()) {
                this.sacarDe1 = !this.sacarDe1;
            }

            this.puedeSacar.signal();
            System.out.println("[Extractor] AVISA");

        } catch (Exception e) {
        } finally {
            this.lockIngresar.unlock();
            this.lockSacar.unlock();
        }

    }

    public void ingresarElemento(int elem) {
        this.lockIngresar.lock();
        try {
            if (sacarDe1) {
                this.cola2.add(elem);
                System.out.println(Thread.currentThread().getName() + " ingreso el elemento: " + elem + " a la cola 2.");
            } else {
                this.cola1.add(elem);
                System.out.println(Thread.currentThread().getName() + " ingreso el elemento: " + elem + " a la cola 1.");
            }
            System.out.println("[Insertador] Trato de tomar el lock sacar");
            this.lockSacar.lock();
            System.out.println("[Insertador] Trato de tome el lock sacar");

            if (sacarDe1 && cola1.isEmpty() || !sacarDe1 && cola2.isEmpty()) {
                this.sacarDe1 = !this.sacarDe1;
            }

            this.puedeSacar.signal();
            System.out.println("[Insertador] AVISA");

        } catch (Exception e) {
        } finally {
            this.lockIngresar.unlock();
            this.lockSacar.unlock();
        }
    }
}
