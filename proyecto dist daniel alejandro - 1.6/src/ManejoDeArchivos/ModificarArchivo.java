/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoDeArchivos;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author JASO VAIO
 */
public class ModificarArchivo {
    
     public static void main(String []args)
{


try
{
    ////listar el directorio//
String sDirectorio = "C:\\xampp\\htdocs\\Distribuidos\\proyecto dist daniel alejandro - 1.6";
File f = new File(sDirectorio);
/////comprobar que existe el fichero//
if (f.exists())
{
    Integer y=0;
    File[] ficheros = f.listFiles();
    for (int x=0;x<ficheros.length;x++)
    {
        
      System.out.println(y+"."+ficheros[x].getName());
      y++;
    } 
    y=0;
}
else{
     System.out.println("no existe el fichero");    
    }

System.out.println("Que Archivo desea modificar?");
String NumeroArchivo="";
Scanner EntradaScanner;
EntradaScanner = new Scanner (System.in);
NumeroArchivo = EntradaScanner.nextLine ();
File s = new File(sDirectorio);
File[] carpeta = s.listFiles(); 
//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
FileWriter escribir=new FileWriter(carpeta[Integer.parseInt(NumeroArchivo)].getName(),true);

//Escribimos en el archivo con el metodo write
System.out.println("ingrese su modificacion al archivo");
String EntradaTeclado2="";
Scanner EntradaScanner2;
EntradaScanner2 = new Scanner (System.in);
EntradaTeclado2 = EntradaScanner2.nextLine();
escribir.write(EntradaTeclado2);

//Cerramos la conexion
escribir.close();
//System.out.println("Archivo se ha modificado");
}

//Si existe un problema al escribir cae aqui
catch(Exception e)
{
System.out.println("No se modifico el archivo correctamente");
}
}
    
}
