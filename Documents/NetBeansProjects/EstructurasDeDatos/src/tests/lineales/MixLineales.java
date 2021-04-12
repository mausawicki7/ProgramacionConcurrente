package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

public class MixLineales {

    public static void main(String[] args) {
        Cola res, unaCola = new Cola();
        unaCola.poner('A');
        unaCola.poner('B');
        unaCola.poner('$');
        unaCola.poner('C');
        unaCola.poner('$');
        unaCola.poner('D');
        unaCola.poner('E');
        unaCola.poner('F');
        unaCola.poner('$');

        res = generarOtraCola(unaCola);
        System.out.println(res.toString());
        
    }

    /**
     *
     * @author Mauricio Sawicki
     */
    /**
     * Ejercicio 2.4 En una nueva clase MixLineales en el paquete test.lineales,
     * implementar el método: generarOtraCola(Cola c1) que recibe por parámetro
     * una estructura de tipo Cola c1 con elementos de tipo char que tiene el
     * siguiente formato: a1$a2$a3$....$an, donde cada ai en una sucesión de
     * letras mayúsculas y a partir de c1 debe generar como salida otra Cola de
     * la forma: a1a1'$a2a2'$....$anan' donde cada ai' es la secuencia de letras
     * de ai invertida. Ejemplo: Si c1 es: AB$C$DEF, la operación
     * generarOtraCola devolverá una Cola con el siguiente formato:
     * ABBA$CC$DEFFED NOTA:Para lograr los tramos invertidos de la Cola de
     * salida debe utilizar una estructura Pila auxiliar.
     */
    public static Cola generarOtraCola(Cola c1) {
        Cola colaRes = new Cola();
        Pila pilaAux = new Pila();

        while (!c1.esVacia()) {
            while (!c1.esVacia() && (char)c1.obtenerFrente() != '$') {
                colaRes.poner((char)c1.obtenerFrente());
                pilaAux.apilar(c1.obtenerFrente());
                c1.sacar();
            }
            c1.sacar(); //Saco el '$'
            while(!pilaAux.esVacia()){
                colaRes.poner(pilaAux.obtenerTope());
                pilaAux.desapilar();       
            }
        colaRes.poner('$');
        }
        return colaRes;
    }

    /**
     * Ejercicio 2.5 (adicional 3): En la clase MixLineales del paquete test.lineales, agregar el método: generarLista(Listalis)
     * que recibe por parámetro una estructura de tipo Lista lis con elementos de tipo char que tiene el siguiente formato:
     * a1∗a2∗a3∗...∗an, donde cada ai en una sucesión de letras mayúsculas y a partir de lis debe generar como salida 
     * otraLista de la forma: a1a1”a1∗a2a2”a2∗. . . .∗anan”an donde cada ai ”es la secuencia de letras de ai invertida.
     * Ejemplo: Si lis es AB*C*DEF, la operación generarOtraLista devolverá una Lista con el siguiente formato:
     * ABBAAB*CCC*DEFFEDDEF
     * NOTA: Para lograr los tramos invertidos de la Lista de salida debe utilizar una Pila auxiliar y para lograr los 
     * tramos no invertidos debe utilizar una Cola auxiliar.
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

}
