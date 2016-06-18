/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoarhivos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alc_d
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List lista;
        lista = new ArrayList<String>();
        ArchivoMuestra archivo = new ArchivoMuestra();
    lista = archivo.leerTextoArchivo("Prueba.txt");
    int numero = archivo.contarLineas("Prueba.txt");
        //System.out.println("el archivo posee "+numero+" lineas");
    if(archivo.existe("Prueba.txt"))
    archivo.escribirTextoArchivo("PruebaCopia.txt", lista, numero);
    }
    
}
