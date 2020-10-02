/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto8;

/**
 *
 * @author mausa
 */
public class Corredor implements Runnable {

    private int num;
    private Testigo testigo;

    public Corredor(int num, Testigo t) {
        this.num = num;
        this.testigo = t;
    }

    @Override
    public void run() {
        double tiempo = 9 + Math.random() * 2;
        switch (num) {
            case 1 -> testigo.aCorrer1(tiempo);
            case 2 -> testigo.aCorrer2(tiempo);
            case 3 -> testigo.aCorrer3(tiempo);
            case 4 -> testigo.aCorrer4(tiempo);
            default -> System.out.println("Numero no válido.");
        }
    }

}
