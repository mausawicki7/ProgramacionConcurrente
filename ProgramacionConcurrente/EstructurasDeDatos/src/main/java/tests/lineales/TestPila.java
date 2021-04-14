/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;

import lineales.estaticas.Pila;

/**
 *
 * @author Mauricio
 */
public class TestPila {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String mes1, mes2, mes3, mes4, mes5, mes6, mes7, mes8, mes9, mes10, mes11, mes12, mes13;
        mes1 = "Enero";
        mes2 = "Febrero";
        mes3 = "Marzo";
        mes4 = "Abril";
        mes5 = "Mayo";
        mes6 = "Junio";
        mes7 = "Julio";
        mes8 = "Agosto";
        mes9 = "Septiembre";
        mes10 = "Octubre";
        mes11 = "Noviembre";
        mes12 = "Diciembre";
        
        Pila p = new Pila();
        p.apilar(mes1);
        p.apilar(mes2);
        p.apilar(mes3);
        p.apilar(mes4);
        p.apilar(mes5);
        p.apilar(mes6);
        p.apilar(mes7);
        p.apilar(mes8);
        p.apilar(mes9);
        p.apilar(mes10);
        p.apilar(mes11);
        p.apilar(mes12);
    }
    
}
