/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;

import jerarquicas.ArbolGen;

/**
 *
 * @author Mauricio
 */
public class TestAncestros {

    public static void main(String[] args) {
        ArbolGen a = new ArbolGen();
        
        a.insertar('A', null);
        a.insertar('B', 'A');
        a.insertar('C', 'A');
        a.insertar('D', 'A');
        a.insertar('E', 'B');
        a.insertar('F', 'B');
        a.insertar('D', 'G');
        a.insertar('H', 'F');
        
        System.out.println(a.ancestros('H').toString());
        

    }

}
