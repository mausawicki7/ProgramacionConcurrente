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
        Lista lista0 = new Lista(), lista1 = new Lista(), lista2 = new Lista(), lista3 = new Lista(), lista4 = new Lista();
        Lista listaRes;
        Pila miPila = new Pila();

        Cola q = new Cola(), colaRes;

        miPila.apilar('D');
        miPila.apilar('B');
        miPila.apilar('E');
        miPila.apilar('&');
        miPila.apilar('F');
        miPila.apilar('E');
        miPila.apilar('D');
        miPila.apilar('&');
        miPila.apilar('E');
        miPila.apilar('A');
        miPila.apilar('C');
        miPila.apilar('&');
        miPila.apilar('B');
        miPila.apilar('A');

        q.poner('A');
        q.poner('B');
        q.poner('#');
        q.poner('C');
        q.poner('#');
        q.poner('D');
        q.poner('E');
        q.poner('F');

        Lista nueva = new Lista();

        //LISTA 0
        lista0.insertar(1, 1);
        lista0.insertar(7, 2);
        lista0.insertar(6, 3);
        lista0.insertar(1, 4);
        lista0.insertar(4, 5);
        lista0.insertar(1, 6);
        lista0.insertar(9, 7);

        //LISTA 1
        lista1.insertar(8, 1);
        lista1.insertar(1, 2);
        lista1.insertar(7, 3);
        lista1.insertar(6, 4);
        lista1.insertar(1, 5);
        lista1.insertar(4, 6);
        lista1.insertar(1, 7);
        lista1.insertar(9, 8);

        //LISTA 2
        lista2.insertar(5, 1);
        lista2.insertar(1, 2);
        lista2.insertar(6, 3);
        lista2.insertar(7, 4);

        //LISTA 3
        lista3.insertar('A', 1);
        lista3.insertar('B', 2);
        lista3.insertar('C', 3);
        lista3.insertar('D', 4);
        lista3.insertar('E', 5);
        lista3.insertar('F', 6);

        //LISTA 4
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
        System.out.println("------------------------------------------------------------------------");
        menu();
        System.out.println("------------------------------------------------------------------------");
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
                break;
            case 6:
                System.out.println("Lista 3: " + lista3.toString());
                System.out.println("Lista retorno: " + lista3.obtenerMultiplos(1).toString());
                break;
            case 5:
                System.out.println("Lista 3: " + lista3.toString());
                lista3.eliminarApariciones('A');
                System.out.println("Lista 3 con el elemento eliminado: " + lista3.toString());
                break;
            case 7:
                System.out.println("Lista 2: " + lista2.toString());
                Object elemento = 0;
                lista2.agregarElem(elemento, 2);
                System.out.println("Lista 2 con agregarElem: " + lista2.toString());
                break;
            case 8:
                System.out.println("Secuencia: " + nueva.generarSecuencia(q, 1));
                break;
            case 9:
                System.out.println("Secuencia: " + nueva.generarSecuenciaExpress(q, 1));
                break;
            case 10:
                System.out.println("Lista 1: " + lista1.toString());
                lista1.insertarPromedio();
                System.out.println("Lista 1 + promedio: " + lista1.toString());
                break;
            case 11:
                System.out.println("Lista 1: " + lista1.toString());
                lista1.promedioPublic();
                System.out.println("Lista 1 + promedio: " + lista1.toString());
                break;
            case 12:
                System.out.println("Lista 4: " + lista4.toString());
                lista4.agregarProducto(2);
                System.out.println("Lista 4 con producto agregado: " + lista4.toString());
                break;
            case 13:
                System.out.println("Lista 0: " + lista0.toString());
                System.out.println("Lista 1: " + lista1.toString());
                listaRes = intercalar(lista0, lista1);
                System.out.println("Lista intercalada: " + listaRes.toString());
                break;
            case 14:
                System.out.println("Cola: " + q.toString());
                colaRes = generarCola(q);
                System.out.println(colaRes.toString());
                break;
            case 15:
                System.out.println("Lista 3: " + lista3.toString());
                colaRes = listaToCola(lista3, 3);
                System.out.println("Cola respuesta:");
                System.out.println(colaRes.toString());
                break;
            case 16:
                System.out.println("Lista 0: " + lista0.toString());
                lista0.insertarPosterior(1, 3);
                System.out.println("Lista 0 con posterior insertado: " + lista0.toString());

                System.out.println("Lista 1: " + lista1.toString());
                lista1.insertarPosterior(1, 3);
                System.out.println("Lista 1 con posterior insertado: " + lista1.toString());
                break;
            case 17:
                System.out.println("Pila: " + miPila);
                listaRes = armarLista(miPila);
                System.out.println("Lista " + listaRes);
                break;

        }

    }

    public static void menu() {
        System.out.println("Ingrese una opción:");
        System.out.println("1. (Uso de TDA) Concatenar dos listas L1 y L2");
        System.out.println("2. (Uso de TDA) Comprobar que la lista cumpla con cadena0cadena0cadenaInvertida");
        System.out.println("3. (Uso de TDA) Invertir lista");
        System.out.println("4. (Uso de TDA) Generar lista de ABC --> ABCCBAABC");
        System.out.println("5. (Metodo TDA) Elimina de la lista todas las apariciones de elementos iguales a x");
        System.out.println("6. (Metodo TDA) Obtener multiplos");
        System.out.println("7. (Metodo TDA) Agregar elemento y repetirlo cada x posiciones");
        System.out.println("8. (Metodo TDA) Generar secuencia");
        System.out.println("9. (Metodo TDA) Generar secuencia 2");
        System.out.println("10. (Metodo TDA) Inserta promedio de los elementos de la lista al final de la misma");
        System.out.println("11. (Metodo TDA) Inserta promedio de los elementos de la lista al final de la misma (recursivo)");
        System.out.println("12. (Metodo TDA) Agrega un producto a la lista");
        System.out.println("13. (Uso de TDA) Intercalar lista");
        System.out.println("14. (Uso de TDA) Generar cola AB#C#DEF --> ABBAAB#CCC#DEFFEDDEF");
        System.out.println("15. (Uso de TDA) Lista a cola.");
        System.out.println("16. Insertar Posterior");
        System.out.println("17. armarLista");

    }

    public static Lista armarLista(Pila pila1) {
        Lista res = new Lista();
        Pila pilaAux = new Pila();
        int pos = 1;
        int cont = 1;

        while (!pila1.esVacia()) {
            while (!pila1.esVacia() && (char) pila1.obtenerTope() != '&') {
                    res.insertar(pila1.obtenerTope(), pos);
                    pila1.desapilar();
                    pos++;     
            }
            cont++;
            pila1.desapilar(); // saco el &
            
            if(cont % 2 !=0){
                
            }
        }

        return res;
    }

    /*
        Recibe por parámetro una estructura cola c1 que tiene el siguiente 
        formato: a1#a2#a3#….#an, donde cada ai es una sucesión de letras 
        mayúsculas y a partir de c1 debe generar como salida otra Cola de la 
        forma: a1a1´a1#a2a2´a2#….#anan´an donde cada ai´ es la 
        secuencia de letras mayúsculas ai pero invertida. Ejemplo.: Si c1 es : 
        AB#C#DEF , entonces la operación generar devolverá una Cola con el 
        siguiente formato: ABBAAB#CCC#DEFFEDDEF
     */
    public static Cola generarCola(Cola cola1) {
        Cola salida = new Cola();
        Cola clon = cola1.clone();
        Cola cola2 = new Cola();
        Pila pila1 = new Pila();
        char aux;

        while (!clon.esVacia()) {

            while ((!clon.esVacia() && (char) clon.obtenerFrente() != '#')) {
                aux = (char) clon.obtenerFrente();
                salida.poner(aux);
                pila1.apilar(aux);
                cola2.poner(aux);
                clon.sacar();
            }

            clon.sacar(); // saco el #
            while (!pila1.esVacia()) {

                salida.poner(pila1.obtenerTope());
                pila1.desapilar();

            }

            while (!cola2.esVacia()) {

                salida.poner(cola2.obtenerFrente());
                cola2.sacar();
            }

            if (!clon.esVacia()) {
                salida.poner('#');
            }
        }

        return salida;
    }

    /**
     *
     * generarLista(Listalis): que recibe por parámetro una estructura de tipo
     * Lista lis con elementos de tipo char que tiene el siguiente formato:
     * a1a2a3...an, donde cada ai en una sucesión de letras mayúsculas y a
     * partir de lis debe generar como salida otra Lista de la
     * forma:a1a1”a1∗a2a2”a2∗....∗anan”an donde cada ai” es la secuencia de
     * letras de ai invertida. Ejemplo: Si lis es AB*C*DEF, la operación
     * generarOtraLista devolverá una Lista con el siguiente formato:
     * ABBAAB*CCC*DEFFEDDEF NOTA: Para lograr los tramos invertidos de la Lista
     * de salida debe utilizar una Pila auxiliar y para lograr los tramos no
     * invertidos debe utilizar una Cola auxiliar.
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
     * cadena0cadena0cadena* (donde cadena* es cadena invertida). Ej: si
     * L1=[9,6,5,0,9,6,5,0,5,6,9], cadena=965, luego cadena*=569, entonces la
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
        int aux, longitud = L1.longitud();

        if (!L1.esVacia()) {
            aux = (int) L1.recuperar(pos);
            while (pos <= longitud && (int) L1.recuperar(pos) != 0) {

                colaAux.poner(aux);
                pilaAux.apilar(aux);
                pos++;
                if (pos <= longitud) {
                    aux = (int) L1.recuperar(pos);
                }
            }
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
     * invertir: recibe una lista L y devuelve una lista nueva con los elementos
     * de L invertidos. Ej: si L1=[2,4,6] debe devolver [6,4,2]
     *
     */
    public static Lista invertir(Lista L1) {
        Lista invertida = new Lista(), listaAux;
        Pila pilaAux = new Pila();
        listaAux = L1.clone();
        int pos = 1;

        while (!listaAux.esVacia()) {
            pilaAux.apilar(listaAux.recuperar(1));
            listaAux.eliminar(1);
        }

        while (!pilaAux.esVacia() && pos <= L1.longitud()) {
            invertida.insertar(pilaAux.obtenerTope(), pos);
            pilaAux.desapilar();
            pos++;
        }

        return invertida;
    }

    /**
     * intercalar: dadas dos listas L1 y L2, devuelve una lista nueva con los
     * elementos de L1 y L2 intercalados. Por ejemplo, si L1=[1,3,5] y
     * L2=[2,4,6] debe devolver [1,2,3,4,5,6]
     */
    public static Lista intercalar(Lista L1, Lista L2) {
        Lista intercalada = new Lista();
        int elemL1, elemL2, cont = 1;

        while (!L1.esVacia() && !L2.esVacia()) {

            elemL1 = (int) L1.recuperar(1);
            elemL2 = (int) L2.recuperar(1);

            intercalada.insertar(elemL1, cont);
            intercalada.insertar(elemL2, cont + 1);

            L1.eliminar(1);
            L2.eliminar(1);
            cont = cont + 2;

        }
        return intercalada;
    }

    /*
    Metodo que recibe por parametro un numero entero t y una lista con una sucesion de caracteres char y, sin destruir la estructura
    original, debe generar una Cola con la forma c1c1'c1'&&c2c2'c2'&&..cncn'cn' donde ci es una secuencia de t elementos tomados de 
    la estructura original y cada ci' es la secuencia ci invertida. La ultima subcadena puede ser de longitud menor a t.
     */
    public static Cola listaToCola(Lista l1, int t) {
        int cont = 0;
        char elemento;
        Cola salida = new Cola();
        Lista clon = l1.clone();
        Pila aux = new Pila();
        Pila aux2;
        while (!clon.esVacia()) {
            while ((!clon.esVacia()) && (cont != t)) {

                elemento = (char) clon.recuperar(1);
                salida.poner(elemento);
                aux.apilar(elemento);
                clon.eliminar(1);
                cont++;

            }
            cont = 0;
            aux2 = aux.clone();

            while (!aux.esVacia()) {
                salida.poner(aux.obtenerTope());
                aux.desapilar();

            }
            while (!aux2.esVacia()) {
                salida.poner(aux2.obtenerTope());
                aux2.desapilar();
            }
            if (!clon.esVacia()) {
                salida.poner('&');
                salida.poner('&');
            }
        }
        return salida;
    }

    /*
    Metodo que recibe por parametro una lista con elementos de tipo char, con el formato a1#a2#a3#...an# donde cada ai es una sucesion
    de letras, y debe generar otra lista con la forma a1'a1#a2'a2#..an'an donde cada ai' es la secuencia de letras de ai invertida.
    Ejemplo: Si la lista que entra es 
    AB#C#DEF el metodo devolverá BAAB#CC#FEDDEF
     */
    public static Lista generarOtraLista(Lista l1) {
        Lista salida = new Lista();
        Cola aux2 = new Cola();
        Pila aux = new Pila();
        int pos = 1;
        if (l1.esVacia()) {

        } else {
            Lista clon = l1.clone();
            while (!clon.esVacia()) {
                while (!clon.esVacia() && (char) clon.recuperar(1) != '#') {
                    aux2.poner(clon.recuperar(1));
                    aux.apilar(clon.recuperar(1));
                    clon.eliminar(1);

                }
                //pila
                clon.eliminar(1);
                while (!aux.esVacia()) {
                    salida.insertar(aux.obtenerTope(), pos);
                    aux.desapilar();
                    pos++;

                }
                //cola
                while (!aux2.esVacia()) {
                    salida.insertar(aux2.obtenerFrente(), pos);
                    aux2.sacar();
                    pos++;

                }
                if (aux.esVacia() && aux2.esVacia() && !clon.esVacia()) {
                    salida.insertar('#', pos);
                    pos++;

                }

            }
        }
        return salida;
    }

    /*
    Método que recibe por parámetro una cola con una expresión matemática y verifica que los paréntesis, corchetes y
    llaves estén correctamente balanceados. Debe usar como estructura auxiliar alguno de los TDA lineales vistos.
    Ejemplos: Si q es ← { 5 + [ 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver TRUE
              Si q es ← { 5 + 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver FALSE
     */
    public boolean verificarBalanceo(Cola q) {
        boolean res = false;
        Cola colaAux = new Cola();
        char elem;

        while (!q.esVacia()) {
            while (!q.esVacia() && (char) q.obtenerFrente() == '{') {

            }
        }

        return res;
    }
}
