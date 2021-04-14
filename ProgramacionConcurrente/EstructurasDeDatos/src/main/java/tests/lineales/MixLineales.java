/*
 * @author Mauricio Sawicki 
 */
package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

public class MixLineales {

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
            case 3:
                System.out.println("Lista 1: " + lista1.toString());           
                System.out.println("Lista 1 invertida: " + invertir(lista1).toString());
                break;
            case 4:
                System.out.println("Lista 2: " + lista2.toString());
                System.out.println("Lista 2 generada: " + generarLista(lista2).toString());
        }

    }
/**
 *
 * generarLista(Listalis): que recibe por parámetro una estructura de tipo Lista lis con elementos de tipo char que 
 * tiene el siguiente formato: a1a2a3...an, donde cada ai en una sucesión de letras mayúsculas y a partir de lis debe 
 * generar como salida otra Lista de la forma:a1a1”a1∗a2a2”a2∗....∗anan”an donde cada ai” es la secuencia de letras de 
 * ai invertida. Ejemplo: Si lis es AB*C*DEF, la operación generarOtraLista devolverá una Lista con el siguiente 
 * formato: ABBAAB*CCC*DEFFEDDEF
 * NOTA: Para lograr los tramos invertidos de la Lista de salida debe utilizar una Pila auxiliar y para lograr 
 * los tramos no invertidos debe utilizar una Cola auxiliar.
 */
    public static Lista generarLista(Lista lis) {
        Lista listX = new Lista();
        if (!lis.esVacia()) {
            int pos = 1;
            Cola colaAux = new Cola();
            Pila pilaAux = new Pila();
            Lista lisAux = lis.clone();
            while (lisAux.recuperar(1) != null) {
                while (lisAux.recuperar(1) != null && !lisAux.recuperar(1).equals(' ')) {
                    Object letraAux = lisAux.recuperar(1);
                    colaAux.poner(letraAux);
                    pilaAux.apilar(letraAux);
                    listX.insertar(letraAux, pos);
                    lisAux.eliminar(1);
                    pos++;
                }
                while (!pilaAux.esVacia()) {
                    listX.insertar(pilaAux.obtenerTope(), pos);
                    pilaAux.desapilar();
                    pos++;
                }
                while (!colaAux.esVacia()) {
                    listX.insertar(colaAux.obtenerFrente(), pos);
                    colaAux.sacar();
                    pos++;
                }
                if (lisAux.recuperar(1) != null) {
                    listX.insertar(' ', pos);
                }
                pos++;
                lisAux.eliminar(1);
            }
        }
        return listX;
    }

    /**
     * concatenar: recibe dos listas L1 y L2 y devuelve una lista nueva con los elementos de L1 y L2 concatenados. 
     * Ej: si L1=[2,4,6] y L2=[5,1,6,7] debe  devolver [2,4,6,5,1,6,7]
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
     * comprobar: recibe una lista L1 cargada con dígitos (números enteros de 0  a 9) 
     * y verifica si los elementos que contiene tienen la forma cadena0cadena0cadena* (donde cadena* es cadena invertida). 
     * Ej: si L1=[9,6,5,0,9,6,5,0,5,6,9], cadena=965, luego cadena*=569, entonces la
     * lista L1 cumple con la condición deseada. Atención: la longitud de cada
     * cadena no se conoce de antemano, hay que identificarla por la primera
     * posición de 0 en la lista. Nota: Utilizar una Pila y una Cola como
     * estructuras auxiliares. Retorna TRUE si respeta la forma, FALSE en caso
     * contrario.
     */
    public static boolean comprobar(Lista L1) {
        boolean res = false;
        int pos = 1;
        Cola colaAux = new Cola();
        Pila pilaAux = new Pila();
        int aux;

        while (!L1.esVacia() && (int) L1.recuperar(pos) != 0) {
            aux = (int) L1.recuperar(pos);
            colaAux.poner(aux);
            pilaAux.apilar(aux);
            pos++;
        }
        pos++;
        while (colaAux.obtenerFrente() == L1.recuperar(pos)) {
            colaAux.sacar();
            pos++;
        }
        pos++;
        while (colaAux.esVacia() && pilaAux.obtenerTope() == L1.recuperar(pos)) {
            pilaAux.desapilar();
            pos++;
        }

        if (pilaAux.esVacia() && colaAux.esVacia()) {
            res = true;
        }

        return res;
    }
    
    /**
     * invertir: recibe una lista L y devuelve una lista nueva con los elementos de L invertidos. 
     * Ej: si L1=[2,4,6] debe devolver [6,4,2]
    **/
    public static Lista invertir(Lista L1){
        Lista invertida = new Lista(), listaAux;
        Pila pilaAux = new Pila();
        listaAux = L1.clone();
        int pos = 1;
        
        while(!listaAux.esVacia()){
            pilaAux.apilar(listaAux.recuperar(1));
            listaAux.eliminar(1);
        }
        
        while(!pilaAux.esVacia() && pos <= L1.longitud()){
            invertida.insertar(pilaAux.obtenerTope(), pos);
            pilaAux.desapilar();
            pos++;
        }
        
        return invertida;
    }
    
    /**
     * intercalar: dadas dos listas L1 y L2, devuelve una lista nueva con los elementos de L1 y L2 intercalados.
     * Por ejemplo, si L1=[1,3,5] y L2=[2,4,6,7] debe devolver [1,2,3,4,5,6,7]
     */
    
    public static Lista intercalar(Lista L1, Lista L2){
        Lista intercalada = new Lista();
        
        
        
        return intercalada;
    }
    public static void menu() {
        System.out.println("Ingrese una opción:");
        System.out.println("1. Concatenar dos listas L1 y L2");
        System.out.println("2. Comprobar que la lista cumpla con cadena0cadena0cadenaInvertida");
        System.out.println("3. Invertir lista");
        System.out.println("4. Generar lista de ABC --> ABCCBAABC");
        System.out.println("5. Intercalar lista");
    }

}
