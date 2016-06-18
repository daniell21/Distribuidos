/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class servidor {
    public static void main(String[] args) {
        
    ServerSocket servidor;
 
   try {
            
                servidor = new ServerSocket(5000);
            do{
            Socket clienteNuevo;
            clienteNuevo = servidor.accept();
                System.out.println("nueva conexion entrante "+clienteNuevo);
                ((Hilos) new Hilos(clienteNuevo)).start();
            }while(true);
            
        }
         catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
