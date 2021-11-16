package FinalDESALG;

import java.util.Arrays;

/**
 *
 * @author Mauricio Sawicki
 */
public class Insercion {

    public static void main(String[] args) {
        int[] arreglo = new int[6];
        arreglo[0] = 3;
        arreglo[1] = 8;
        arreglo[2] = 1;
        arreglo[3] = 5;
        arreglo[4] = 2;
        arreglo[5] = 6;
        insercion(arreglo);
        System.out.println(Arrays.toString(arreglo));
    }

    public static void insercion(int A[]) {
        int p, j;
        int aux;
        for (p = 1; p < A.length; p++) { // desde el segundo elemento hasta
            aux = A[p]; // el final, guardamos el elemento y
            j = p - 1; // empezamos a comprobar con el anterior
            while ((j >= 0) && (aux < A[j])) { // mientras queden posiciones y el
                // valor de aux sea menor que los
                A[j + 1] = A[j]; // de la izquierda, se desplaza a
                j--;             // la derecha
            }
            A[j + 1] = aux; // colocamos aux en su sitio
        }
    }
}

//En el peor de los casos, el tiempo de ejecución en O(n2).
//
//En el mejor caso (cuando el array ya estaba ordenado), el tiempo de ejecución de este método
//de ordenamiento es O(n).
//El caso medio dependerá de cómo están inicialmente distribuidos los elementos. Cuanto más
//ordenada esté inicialmente más se acerca a O(n) y cuanto más desordenada, más se acerca a O(n2).
