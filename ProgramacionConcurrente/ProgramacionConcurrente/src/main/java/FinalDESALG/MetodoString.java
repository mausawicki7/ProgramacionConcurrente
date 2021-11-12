package FinalDESALG;

/**
 *
 * @author Sawicki Mauricio
 */
//5. STRING. Implementar en Java un algoritmo recursivo que dado un texto almacenado en un String,
//verifique si existe una vocal que aparece más de dos veces en el texto. Nota: puede utilizar si lo desea,
//métodos propios de la clase String Ejemplos: amapola -debe retornar verdadero avioneta debe retornar falso

public class MetodoString {
    public static void main(String[] args) {
        System.out.println("Ingrese un texto: ");
        String texto = TecladoIn.readLine();

        // Creo un arreglo de longitud 5, donde van a contarse las vocales en el siguiente orden [a, e, i, o, u]
        int[] cont = new int[5];
        System.out.println(verificarVocales(texto, cont));
    }

    private static boolean verificarVocales(String texto, int[] contadores) {
        boolean estaRepetida = false;
        int contadorVocal = 0;
        if (texto.length() > 0) { // El algoritmo aún no ha llegado al final del String.
            char aux = texto.charAt(0);
            //Cada vez que aparezca una vocal, depende de que vocal sea, aumento el contador correspondiente.
            switch (aux) {
                case 'a':
                    contadores[0]++;
                    contadorVocal = contadores[0];
                    break;
                case 'e':
                    contadores[1]++;
                    contadorVocal = contadores[1];
                    break;
                case 'i':
                    contadores[2]++;
                    contadorVocal = contadores[2];
                    break;
                case 'o':
                    contadores[3]++;
                    contadorVocal = contadores[3];
                    break;
                case 'u':
                    contadores[4]++;
                    contadorVocal = contadores[4];
                    break;
            }
            if (contadorVocal > 2) {
                estaRepetida = true; // Hay una vocal repetida mas de dos veces
            } else {
                estaRepetida = verificarVocales(texto.substring(1), contadores); //Quita del texto el caracter evaluado mas reciente
            }
        }
        return estaRepetida;
    }
}
