/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.conjuntistas;
import conjuntistas.ArbolBB;
/**
 *
 * @author Mauricio Sawicki
 */
public class TestABB {
    static String sOk = "\u001B[32m OK! \u001B[0m", sErr = " \u001B[31m ERROR \u001B[0m";

    public static void cartelito(String titulo) {
        int longitud = titulo.length() + 10;
        String barra = "";
        for (int i = 0; i < longitud; i++)
            barra += "*";
        for (int j = 0; j < 4; j++)
            titulo = " " + titulo + " ";
        titulo = "*" + titulo + "*";
        System.out.println();
        System.out.println(barra);
        System.out.println(titulo);
        System.out.println(barra);
        System.out.println();
    }

    public static void linea() {
        String s = "";
        for (int i = 0; i < 194; i++)
            s += "-";
        System.out.println(s);
        System.out.println();
    }

    public static String comprobar(boolean condicion) {
        return (condicion) ? sOk : sErr;
    }

    public static void llenar(ArbolBB arbol, int[] num) {
        for (int i = 0; i < num.length; i++)
            arbol.insertar(num[i]);
    }

    public static void llenar(ArbolBB arbol, String num) {
        num = num.replaceAll("-", "");
        for (int i = 0; i < num.length(); i++)
            arbol.insertar(Character.getNumericValue(num.charAt(i)));
    }

    public static void main(String[] args) {
        ArbolBB a = new ArbolBB();
        int[] num = {5, 2, 1, 3, 8, 7, 9};
        cartelito("Test de árbol binario de búsqueda");

        cartelito("Test Vaciar y esVacio");
        System.out.println("Chequeo si es vacío: " + comprobar(a.esVacio()));
        System.out.println("Inserto 5");
        a.insertar(5);
        System.out.println("Chequeo si no es vacío: " + comprobar(!a.esVacio()));
        System.out.println("Vacío");
        a.vaciar();
        System.out.println("Chequeo si es vacío: " + comprobar(a.esVacio()));
        System.out.println("Vacío nuevamente");
        a.vaciar();
        System.out.println("Chequeo si es vacío: " + comprobar(a.esVacio()));

        cartelito("Test insertar");
        llenar(a, num);
        System.out.println("Resultado esperado\n");
        System.out.println("             |------------[5]------------|             ");
        System.out.println("     |------[2]------|           |------[8]------|     ");
        System.out.println("    [1]             [3]         [7]             [9]    ");
        System.out.println();
        System.out.println("Resultado obtenido");
        System.out.println(a);
        System.out.println("vacío");
        System.out.println(a);

        cartelito("Test Mínimo");
        llenar(a, num);
        int[] arr = {1, 2, 3, 5, 7, 8, 9};
        for (int j = 0; j < arr.length; j++) {
            System.out.println("El mínimo es " + arr[j] + ": " + comprobar(a.minimo().equals(arr[j])));
            System.out.println("Elimino el mínimo: " + comprobar(a.eliminarMinimo()));
        }
        System.out.println("El mínimo en árbol vacío: " + comprobar(a.minimo() == null));
        System.out.println("Elimino el mínimo en árbol vacío: " + comprobar(a.eliminarMinimo() == false));

        cartelito("Test máximo");
        llenar(a, num);
        for (int k = arr.length - 1; k >= 0; k--) {
            System.out.println("El máximo es " + arr[k] + ": " + comprobar(a.maximo().equals(arr[k])));
            System.out.println("Elimino el máximo: " + comprobar(a.eliminarMaximo()));
        }
        System.out.println("El máximo en árbol vacío: " + comprobar(a.maximo() == null));
        System.out.println("Elimino el máximo en árbol vacío: " + comprobar(a.eliminarMaximo() == false));

        cartelito("Test eliminar");
        System.out.println("Caso 0: Vacío");
        System.out.println("Elimino en árbol vacío: " + comprobar(a.eliminar(0) == false));
        System.out.println();
        System.out.println("Caso 1: Nodos hojas");
        System.out.println("             |------------[5]------------|             ");
        System.out.println("            [2]                         [8]            ");
        int[] caso1 = {5, 2, 8};
        llenar(a, caso1);
        System.out.println(a + "\n");
        System.out.println("Elimino 2: " + comprobar(a.eliminar(2)));
        System.out.println(a + "\n");
        System.out.println("Elimino 8: " + comprobar(a.eliminar(8)));
        System.out.println(a + "\n");
        System.out.println("Elimino 5: " + comprobar(a.eliminar(5)));
        System.out.println(a + "\n");

        System.out.println("Caso 2: Nodos con al menos un hijo");
        int[] caso2 = {5, 2, 3, 8, 7};
        llenar(a, caso2);
        System.out.println("             |------------[5]------------|             ");
        System.out.println("     |------[2]------|           |------[8]------|     ");
        System.out.println("    [-]             [3]         [7]             [-]    ");
        System.out.println(a + "\n");
        System.out.println("Elimino 2: " + comprobar(a.eliminar(2)));
        System.out.println(a + "\n");
        System.out.println("Elimino 8: " + comprobar(a.eliminar(8)));
        System.out.println(a + "\n");
        System.out.println("Inserto 2 y 8");
        a.insertar(2);
        a.insertar(8);
        System.out.println(a + "\n");
        System.out.println("Elimino 3: " + comprobar(a.eliminar(3)));
        System.out.println(a + "\n");
        System.out.println("Elimino 7: " + comprobar(a.eliminar(7)));
        System.out.println(a + "\n");
        // inicio de las pruebas en la raiz
        System.out.println("Elimino 2: " + comprobar(a.eliminar(2)));
        System.out.println(a + "\n");
        System.out.println("Elimino 5 (raíz): " + comprobar(a.eliminar(5)));
        System.out.println(a + "\n");
        System.out.println("Inserto 6: " + comprobar(a.insertar(6)));
        System.out.println(a + "\n");
        System.out.println("Elimino 8 (raíz): " + comprobar(a.eliminar(8)));
        System.out.println(a + "\n");
        a.vaciar();

        System.out.println("Caso 3: Nodos con dos hijos");
        int[] caso3 = {4, 1, 0, 2, 8, 6, 5, 7, 9};
        llenar(a, caso3);
        System.out.println("                           |-----------------------[4]-----------------------|                           ");
        System.out.println("             |------------[1]------------|                     |------------[8]------------|             ");
        System.out.println("     |------[0]------|           |------[2]------|     |------[6]------|           |------[9]------|     ");
        System.out.println("    [-]             [-]         [-]             [-]   [5]             [7]         [-]             [-]    ");
        System.out.println(a + "\n");
        System.out.println("Elimino 4: " + comprobar(a.eliminar(4)));
        System.out.println(a + "\n");
        System.out.println("Elimino 5: " + comprobar(a.eliminar(5)));
        System.out.println(a + "\n");
        System.out.println("Elimino 1: " + comprobar(a.eliminar(1)));
        System.out.println(a + "\n");
        a.vaciar();

        cartelito("Test listar y listarRango");
        System.out.println("             |------------[5]------------|             ");
        System.out.println("     |------[2]------|           |------[8]------|     ");
        System.out.println("    [1]             [3]         [7]             [9]    ");
        llenar(a, num);
        System.out.println(a + "\n");
        System.out.println("listar es igual a [1, 2, 3, 5, 7, 8, 9]: " + comprobar(a.listar().toString().equals("[1, 2, 3, 5, 7, 8, 9]")));
        System.out.println("rango[1, 9] es igual a [1, 2, 3, 5, 7, 8, 9]: " + comprobar(a.listarRango(0, 9).toString().equals("[1, 2, 3, 5, 7, 8, 9]")));
        System.out.println("rango[3, 7] es igual a [3, 5, 7]: " + comprobar(a.listarRango(3, 7).toString().equals("[3, 5, 7]")));
        System.out.println("rango[1, 2] es igual a [1, 2]: " + comprobar(a.listarRango(1, 2).toString().equals("[1, 2]")));
        System.out.println("rango[8, 9] es igual a [8, 9]: " + comprobar(a.listarRango(8, 9).toString().equals("[8, 9]")));
        a.vaciar();
        System.out.println("listar vacío de árbol vacío: " + comprobar(a.listar().toString().equals("lista vacía")));
        System.out.println("rango vacío de árbol vacío: " + comprobar(a.listarRango(1, 9).toString().equals("lista vacía")));

        cartelito("Test pertenece");
        llenar(a, num);
        System.out.println(a + "\n");
        System.out.println("pertenece 9: " + comprobar(a.pertenece(9)));
        System.out.println("pertenece 1: " + comprobar(a.pertenece(1)));
        System.out.println("pertenece 5: " + comprobar(a.pertenece(5)));
        System.out.println("no pertenece 4: " + comprobar(!a.pertenece(4)));
        a.vaciar();
        System.out.println("no pertenece 1 en vacío: " + comprobar(!a.pertenece(1)));
    }

}
