/*
 * Mauricio Sawicki
 */
package SegundoParcial.FabricaDeSueters;

/**
 *
 * Fabrica de Suéters Tenemos un taller de costura, dedicado a hacer Suéters. En
 * su interior tenemos a tres personas trabajando de sol a sol por cuatro pesos.
 * Una persona está continuamente fabricando mangas, que va depositando en un
 * cesto. El cesto tiene una capacidad limitada: cuando se llena, la costurera
 * deja de coser más mangas hasta que haya un lugar libre en el canasto. Otra
 * persona está continuamente fabricando los cuerpos de los Suéters, que también
 * deposita en su correspondiente cesta de capacidad limitada. Una tercera
 * persona se encarga continuamente de ensamblar Suéters. Para poder armarlo, en
 * cada caso agarra dos mangas de la cesta de mangas y un cuerpo de la cesta de
 * cuerpos. Cuando esta persona construye 10 Suéters, los embala colocándolos
 * doblados en una caja para su posterior distribución. Se trata de escribir el
 * código que sincronice a estas tres personas, de forma que las dos primeras
 * personas no avancen si su cesta está llena, y que la tercera persona no
 * avance si le faltan piezas para hacer un nuevo Suéter. Además se quiere saber
 * cuántas cajas completas tienen en un determinado tiempo. Nota: Se supone que
 * las capacidades de las dos cestas son constantes y distintas (supongan, p.ej.
 * que son dos constantes enteras "NumMaxMangas" y "NumMaxCuerpos").
 */
public class Test {

    public static void main(String[] args) {
       
        int cantMaxMangas = 8;
        int cantMaxCuerpos = 8;
        int cantCosturerasMangas = 1;
        int cantCosturerasCuerpos = 1;
        int cantCosturerasEnsambladoras = 1;

        TallerCostura taller = new TallerCostura(cantMaxMangas, cantMaxCuerpos);

        //Costureras ensambladoras
        for (int i = 1; i <= cantCosturerasEnsambladoras; i++) {
            Costurera e = new Costurera(taller, "Ensambladora" + i, 3);
            Thread costureras = new Thread(e);
            costureras.start();
        }

        //Costureras mangas
        for (int i = 1; i <= cantCosturerasMangas; i++) {
            Costurera c1 = new Costurera(taller, "CostureraManga" + i, 1);
            Thread costurerasMangas = new Thread(c1);
            costurerasMangas.start();
        }

        //Costureras cuerpos
        for (int i = 1; i <= cantCosturerasCuerpos; i++) {
            Costurera c2 = new Costurera(taller, "CostureraCuerpo" + i, 2);
            Thread costurerasCuerpos = new Thread(c2);
            costurerasCuerpos.start();
        }

    }
}
