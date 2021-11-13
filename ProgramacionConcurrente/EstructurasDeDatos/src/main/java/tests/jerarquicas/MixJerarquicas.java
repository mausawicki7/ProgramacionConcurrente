package tests.jerarquicas;

import jerarquicas.ArbolBin;
import lineales.dinamicas.Lista;
import tests.lineales.TecladoIn;

/**
 *
 * @author Mauricio
 */
public class MixJerarquicas {

    public static void main(String[] args) {
        ArbolBin a = new ArbolBin();
        ArbolBin b = new ArbolBin();
        ArbolBin c = new ArbolBin();
        Lista lista = new Lista();
        int opc;
        boolean res;

        // 'elem' 'padre' 'lugar'
        a.insertar('A', null, 'I');
        a.insertar('B', 'A', 'I');
        a.insertar('C', 'A', 'D');
        a.insertar('D', 'C', 'D');

        b.insertar('A', null, 'I');
        b.insertar('B', 'A', 'I');
        b.insertar('C', 'A', 'D');
        b.insertar('E', 'C', 'D');

        lista.insertar('A', 1);
        lista.insertar('F', 2);
        lista.insertar('J', 3);

        menu();
        opc = TecladoIn.readLineInt();
            
        switch (opc) {
        case 1:
            System.out.println("Arbol A: " + a.toString());
            c = a.clonarInvertido();
            System.out.println("Arbol A clon invertido: " + c.toString());
            break;
        case 2:
            System.out.println("Arbol: "+a.toString());
            System.out.println("Lista: "+lista.toString());
            System.out.println("Verificando patron..");
            res = a.verificarPatron(lista);
            System.out.println(res);
            break;
        case 3:
            System.out.println("Arbol a: "+a.toString());
            System.out.println("Arbol b: "+b.toString());
            System.out.println("Son iguales?");
            System.out.println(a.equals(b));
            break;
        case 4:
            System.out.println("Arbol a: "+a.toString());
            System.out.println("Frontera de a: "+a.frontera().toString());
            break;
        }
    }

    public static void menu() {
        System.out.println("Ingrese una opción:");
        System.out.println("1. (TDA ArbolBin) Clonar invertido");
        System.out.println("2. (TDA ArbolBin) Verificar patron");
        System.out.println("3. (TDA ArbolBin) Equals 2 arboles binarios");
        System.out.println("4. (TDA ArbolBin) Frontera de un arbol");
    }
    

}
        
