/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoDeArchivos;
import java.util.Scanner; 
import java.io.File;
import java.io.FileWriter;


/**
 *
 * @author JASO VAIO
 */
public class CrearArchivo {
    
    public static void main(String []args)
{
String sDirectorio = "C:\\xampp\\htdocs\\Distribuidos\\proyecto dist daniel alejandro - 1.6\\Archivos creados";

System.out.println("Escriba el nombre del archivo ha crear");
String EntradaTeclado="";
Scanner EntradaScanner;
EntradaScanner = new Scanner (System.in);
EntradaTeclado = EntradaScanner.nextLine ();

try
{
//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
File archivo=new File ("C:\\xampp\\htdocs\\Distribuidos\\proyecto dist daniel alejandro - 1.6",EntradaTeclado+".txt");

//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
FileWriter escribir=new FileWriter(archivo,true);

//Escribimos en el archivo con el metodo write
//escribir.write(Texto);

//Cerramos la conexion
escribir.close();
System.out.println("Archivo creado");
}

//Si existe un problema al escribir cae aqui
catch(Exception e)
{
System.out.println("No se creo el archivo correctamente");
}
}
    
}
