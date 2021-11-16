/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Compartido.Buffer;
import Hilos.Extractor;
import Hilos.Insertador;

/**
 *
 * @author Fernando
 */
public class main {

    public static void main(String[] args) {
        Buffer compartido = new Buffer();
        int cantExtractor = 1, cantInsertador = 1;

        for (int i = 1; i <= cantExtractor; i++) {
            Extractor e = new Extractor(compartido);
            Thread extractor = new Thread(e, "Extractor " + i);
            extractor.start();
        }

        for (int i = 1; i <= cantInsertador; i++) {
            Insertador in = new Insertador(compartido, i * 1000);
            Thread insertador = new Thread(in, "Insertador " + i);
            insertador.start();
        }
    }

}
