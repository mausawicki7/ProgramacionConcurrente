package FinalDESALG;

/**
 *
 * @author Sawicki Mauricio
 */
public class TestPlanta {

    public static void main(String[] args) {
        int cant, opc, contador;
        boolean despliegaMenu = true; //Variable booleana para abrir menu.
        contador = 0;

        //Solicitamos el tamaño del arreglo
        System.out.println("Ingrese la cantidad de plantas:");
        cant = TecladoIn.readLineInt();

        //Creamos e inicializamos el arreglo con 'cant' plantas.
        Planta[] arregloPlantas;
        arregloPlantas = new Planta[cant];

        while (despliegaMenu) { //Mientras sea true se sigue desplegando el menú
            System.out.println("----- Elija una opción -----");
            System.out.println("1. Cargar una planta");
            System.out.println("2. Mostrar ejercicio recursivo ");
            System.out.println("3. Salir");
            opc = TecladoIn.readLineInt();

            switch (opc) {
                case 1:
                    if (contador < cant) {
                        arregloPlantas[contador] = cargarPlanta(arregloPlantas, contador);
                        if (arregloPlantas[contador] != null) {
                            contador = contador + 1;
                        } else {
                            System.out.println("La planta ingresada no pudo ser cargada.");
                        }
                    } else {
                        System.out.println("Cantidad máxima alcanzada, no se pueden cargar más plantas.");
                    }
                    break;
                case 2:
                    imprimePlantas(arregloPlantas, 0); // Para iniciar desde la posicion 0 del arreglo.
                    break;
                case 3:
                    despliegaMenu = false;
                    break;
                default:
                    System.out.println("Opcion inválida..");
                    break;
            }
        }
    }

    //Método para realizar la carga de una planta al arreglo.
    private static Planta cargarPlanta(Planta[] arreglo, int c) {
        int cod, opcLugar;
        double alturaMax, precio, tempMin;
        String lugarIdeal;
        boolean tieneFlores, esMedicinal;

        System.out.println("Ingrese el codigo de la Planta");
        cod = TecladoIn.readLineInt();
        Planta unaPlanta = new Planta(cod);

        for (int i = 0; i < c; i++) { //Busca el codigo de la planta ingresada en el arreglo, evita repetidos.
            if (arreglo[i].equals(unaPlanta)) { // La planta ya existe
                System.out.println("La planta ya existe en el arreglo, no se pudo cargar.");
                unaPlanta = null;
            }
        }
        if (unaPlanta != null) {
            System.out.println("Ingrese el precio: ");
            precio = TecladoIn.readLineDouble();
            System.out.println("Ingrese altura maxima: ");
            alturaMax = TecladoIn.readLineDouble();
            System.out.println("Ingrese temperatura minima: ");
            tempMin = TecladoIn.readLineDouble();
            System.out.println("Seleccione el lugar ideal: (si selecciona un numero distinto a 1, 2 o 3, se considerará que eligió la opción 1.)");
            System.out.println("1. Sol");
            System.out.println("2. Media sombra");
            System.out.println("3. Sombra total");
            opcLugar = TecladoIn.readLineInt();
            switch (opcLugar) {
                case 1:
                    lugarIdeal = "Sol";
                    break;
                case 2:
                    lugarIdeal = "Media sombra";
                    break;
                case 3:
                    lugarIdeal = "Sombra total";
                    break;
                default:
                    lugarIdeal = "Sol";
                    break;
            }
            System.out.println("¿La planta tiene flores? (si/no)");
            String resp1 = TecladoIn.readLine();
            tieneFlores = false;
            if (resp1.equalsIgnoreCase("si")) {
                tieneFlores = true;
            }
            System.out.println("¿La planta es de uso medicinal? (si/no)");
            String resp2 = TecladoIn.readLine();
            esMedicinal = false;
            if (resp2.equalsIgnoreCase("si")) {
                esMedicinal = true;
            }

            //Carga de datos a la Planta unaPlanta
            unaPlanta.setAlturaMaxima(alturaMax);
            unaPlanta.setPrecio(precio);
            unaPlanta.setLugarIdeal(lugarIdeal);
            unaPlanta.setTempMin(tempMin);
            unaPlanta.setTienesFlores(tieneFlores);
            unaPlanta.setEsMedicinal(esMedicinal);
        }
        return unaPlanta;
    }

    public static void imprimePlantas(Planta[] arreglo, int i) { // Al iniciar i tiene valor 0
        int cant = arreglo.length; // i es la posicion actual que debo revisar en el arreglo
        if (i < cant) { // Todavia quedan plantas por recorrer en el arreglo
            Planta aux = arreglo[i];
            if ((aux.getLugarIdeal().equalsIgnoreCase("Media sombra") && aux.getTempMin() <= 0) || ((aux.getAlturaMaxima() < 10) && (aux.getEsMedicinal()))) {
                System.out.println(aux.toString()); //Si se cumple una de las dos condiciones, se imprime.
            }
            i++; //Se aumenta el contador de la posicion
            imprimePlantas(arreglo, i); //Se llama recursivamente hasta que se terminen las plantas.
        }
    }

}
