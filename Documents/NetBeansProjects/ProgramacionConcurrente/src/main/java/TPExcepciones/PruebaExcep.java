/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPExcepciones;

import utiles.*;

/**
 *
 * @author mausa
 */
public class PruebaExcep {

    public static void esMayorDeEdad(int edad) throws RuntimeException {
        if (edad < 18) {
            throw new RuntimeException("Es menor de 18 años");
        }
    }

    public static void numeroRuleta(int num) throws RuntimeException {
        int numeroAleatorio = (int) (Math.random() * 36 + 1);
        if (num != numeroAleatorio) {
            throw new RuntimeException("No salió el numero que elegiste..");
        }
    }

    public static void llenarArreglo() {
        int i, n;
        int[] array = new int[5];
        System.out.println("A continuación ingrese 5 numeros:");
        for (i = 0; i <= 4; i++) {
            System.out.println("Ingrese un numero:");
            n = TecladoIn.readLineInt();
            array[i] = n;
        }
        try {
            for (i = 0; i <= 7; i++) {
                System.out.println(array[i]);
            }
            throw new RuntimeException();
            
        } catch (RuntimeException exc) {
            System.out.println("Esta intentando visitar posiciones del arreglo que no existen..");;
        }

    }

    public static void main(String[] args) {
        int edad, num, n, i;

        try {
            System.out.println("Ingrese una edad: ");
            edad = TecladoIn.readLineInt();
            esMayorDeEdad(edad);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Ingrese una numero para la ruleta: ");
            num = TecladoIn.readLineInt();
            numeroRuleta(num);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        llenarArreglo();
        

    }

}
