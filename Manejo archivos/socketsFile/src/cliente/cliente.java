/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alc_d
 */
public class cliente {
      public static void main(String[] args) {
          
          Scanner salida = new Scanner(System.in);
        String teclado = "";
        DataInputStream input;
 BufferedInputStream bis;
 BufferedOutputStream bos;
 int in;
 byte[] byteArray;
 //Fichero a transferir
 final String filename = "envio\\testo.txt";
 
try{
 //final File localFile = new File( filename );
 Socket client = new Socket("localhost", 5000);
 //bis = new BufferedInputStream(new FileInputStream(localFile));
 //bos = new BufferedOutputStream(client.getOutputStream());
 DataOutputStream mensaje = new DataOutputStream(client.getOutputStream());
DataInputStream entrada = new DataInputStream(client.getInputStream());
 //Enviamos el nombre del fichero
 DataOutputStream dos=new DataOutputStream(client.getOutputStream());
 //dos.writeUTF(localFile.getName());
 //Enviamos el fichero
 byteArray = new byte[8192];
 /*while ((in = bis.read(byteArray)) != -1){
 bos.write(byteArray,0,in);
 }
 
bis.close();
 bos.close();*/
 do{
            teclado = salida.next();
            if(teclado.equals("adios")){
                System.out.println("detecte adios");
                mensaje.writeUTF("adios");
               String accion = entrada.readUTF();
                System.out.println("el servidor dice "+accion);
                
            }
            if(teclado.equals("envio")){
                PrintStream envio=new PrintStream(client.getOutputStream());
                mensaje.writeUTF(teclado);
                FileInputStream origen=new FileInputStream(filename);
                byte[] buffer = new byte[2097152]; 
                int len; 
                System.out.println("opt");
                while((len=origen.read(buffer))>0){ 
                envio.write(buffer,0,len);
                
                } 
                System.out.println("enviado");
                
                String accion="";
                accion = entrada.readUTF();
                System.out.println(accion);
                
               /*
                byteArray = new byte[8192];
                System.out.println("intento de envio");
                while ((in = bis.read(byteArray)) != -1){
                    bos.write(byteArray,0,in);
                }
                //bis.close();
                 */
                  System.out.println("archivo enviado");
                  mensaje.writeUTF("archivoEnviado");
                  System.out.println("pude enviarle al servidor que el archivo fue enviado exitosamente");
                  
                 //bos.close();
            }else{
           
            mensaje.writeUTF(teclado);
            }
            
        }while(!entrada.equals("Adios"));
 
 
}catch ( Exception e ) {
 System.err.println(e);
 }
    }
}
