package tests.lineales;

import lineales.dinamicas.Pila;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author mausa
 */
public class PruebaLista {

    public static void main(String[] args) {
        int opc;
        boolean resMod;
        Lista lista1 = new Lista(), lista2 = new Lista(), lista3, lista4 = new Lista();

        lista1.insertar(2, 1);
        lista1.insertar(4, 2);
        lista1.insertar(6, 3);

        lista2.insertar(5, 1);
        lista2.insertar(1, 2);
        lista2.insertar(6, 3);
        lista2.insertar(7, 4);

        lista4.insertar(9, 1);
        lista4.insertar(6, 2);
        lista4.insertar(5, 3);
        lista4.insertar(0, 4);
        lista4.insertar(9, 5);
        lista4.insertar(6, 6);
        lista4.insertar(5, 7);
        lista4.insertar(0, 8);
        lista4.insertar(5, 9);
        lista4.insertar(6, 10);
        lista4.insertar(9, 11);

        System.out.println("Listas cargadas");
        System.out.println("Lista 1: " + lista1.toString());
        System.out.println("Lista 2: " + lista2.toString());
        System.out.println("-----------------------------------------------------------------");
        menu();
        System.out.println("-----------------------------------------------------------------");
        opc = TecladoIn.readLineInt();

        switch (opc) {
            case 1:
                lista3 = concatenar(lista1, lista2);
                System.out.println("Listas concatenadas: ");
                System.out.println(lista3.toString());
                break;
            case 2:
                System.out.println("Lista 4: " + lista4.toString());
                resMod = comprobar(lista4);
                System.out.println(resMod);
                break;

        }

    }

    /**
     * concatenar: recibe dos listas L1 y L2 y devuelve una lista nueva con los
     * elementos de L1 y L2 concatenados. Ej: si L1=[2,4,6] y L2=[5,1,6,7] debe
     * devolver [2,4,6,5,1,6,7]
     */
    public static Lista concatenar(Lista L1, Lista L2) {
        Lista listaRes = new Lista();
        int pos = 1;
        int elem1, elem2;
        while (L2.recuperar(pos) != null) {
            elem2 = (int) L2.recuperar(pos);
            listaRes.insertar(elem2, pos);
            pos++;
        }
        pos = 1;

        while (L1.recuperar(pos) != null) {
            elem1 = (int) L1.recuperar(pos);
            listaRes.insertar(elem1, pos);
            pos++;
        }

        return listaRes;
    }

    /**
     * comprobar: recibe una lista L1 cargada con dígitos (números enteros de 0
     * a 9) y verifica si los elementos que contiene tienen la forma
     * cadena0cadena0cadena* (donde cadena* es cadena invertida). 
     * Ej: si L1=[9,6,5,0,9,6,5,0,5,6,9], cadena=965, luego cadena*=569, entonces la
     * lista L1 cumple con la condición deseada. Atención: la longitud de cada
     * cadena no se conoce de antemano, hay que identificarla por la primera
     * posición de 0 en la lista. Nota: Utilizar una Pila y una Cola como estructuras auxiliares.
     * Retorna TRUE si respeta la forma, FALSE en caso contrario.
     */
    public static boolean comprobar(Lista L1) {
        boolean res = false;
        int pos = 1;
        Cola colaAux = new Cola();
        Pila pilaAux = new Pila();

        while (L1.recuperar(pos) != null && (int) L1.recuperar(pos) != 0) {
            colaAux.poner((int) L1.recuperar(pos));
            pilaAux.apilar((int) L1.recuperar(pos));
            pos++;
        }
        pos++;
        while (colaAux.obtenerFrente() == L1.recuperar(pos)) {
            colaAux.sacar();
            pos++;
        }
        pos++;
        while (pilaAux.obtenerTope() == L1.recuperar(pos)) {
            pilaAux.desapilar();
            pos++;
        }

        if (pilaAux.obtenerTope() == null && colaAux.obtenerFrente() == null) {
            res = true;
        }

        return res;
    }

    public static void menu() {
        System.out.println("Ingrese una opción:");
        System.out.println("1. Concatenar dos listas L1 y L2");
        System.out.println("2. Comprobar que la lista cumpla con cadena0cadena0cadenaInvertida");
    }
}
