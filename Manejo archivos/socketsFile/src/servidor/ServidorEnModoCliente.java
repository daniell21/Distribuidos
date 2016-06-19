/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Lorente
 */
public class ServidorEnModoCliente  {
     /*en este archivo debo crear un socket para cada SD para hacer las peticiones
      a todos los SD con su puerto e IP correspondientes. La entrada por teclado 
      no debe existir, solo un String que tenga PERLICULAS_POR_SERVIDOR y asi tener 
      automatizado la carga de las listas para el SC para mostrar a los clientes.*/
    String PPS = "Dame_tus_Vecinos";
    //Este bloque es donde creare todos los sockets para comunicarme con cada SD
    Socket SocketVecino1;
    Socket SocketVecino2;
    Socket SocketVecino3;
    //Este bloque es para crear los DATAOUTPUT para cada servidor
    DataOutputStream SalidaVecino1;
    DataOutputStream SalidaVecino2;
    DataOutputStream SalidaVecino3;
    
    //Este bloque es para crear los Bufferedreader
    DataInputStream EntradaVecino1;
    DataInputStream EntradaVecino2;
    DataInputStream EntradaVecino3;
    
    //Creacion de la lista con los servidores vecinos
     List listaVecinos =new ArrayList();
     //InterfaceServidor i = new InterfaceServidor();
    
    
    //Este bloque de codigo es donde se guardaran las DirIP y puertos de los servidores
    int PuertoVecino1 = 55006;        String DirIPVecino1 = "127.0.0.1";
    int PuertoVecino2 = 55001;        String DirIPVecino2 = "127.0.0.1";
    int PuertoVecino3 = 55005;        String DirIPVecino3 = "127.0.0.1";
    
    public void inicio ()
    {
        try {   
            //Este bloque es para inicializar cada socket a su respectivo servidor
                    SocketVecino1 = new Socket(DirIPVecino1,PuertoVecino1);
                    SocketVecino2 = new Socket(DirIPVecino2,PuertoVecino2);
                    SocketVecino3 = new Socket(DirIPVecino3,PuertoVecino3);
           //cliente = new Socket(ip, puerto);
           //ESte bloque es para inicializar cada entrada proviniente de los SD al SCc
                   // EntradaVecino1 = new BufferedReader(new InputStreamReader(SocketVecino1.getInputStream()));
                    //EntradaVecino2 = new BufferedReader(new InputStreamReader(SocketVecino2.getInputStream()));
                    //EntradaVecino3 = new BufferedReader(new InputStreamReader(SocketVecino3.getInputStream()));
           /* entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            teclado = new BufferedReader(new InputStreamReader(System.in));*/
            
            //String tec = teclado.readLine();
            
            //Este bloque es para inicializar cada salida del SC hacia los SD
                    SalidaVecino1 = new DataOutputStream(SocketVecino1.getOutputStream());
                    SalidaVecino2 = new DataOutputStream(SocketVecino2.getOutputStream());
                    SalidaVecino3 = new DataOutputStream(SocketVecino3.getOutputStream());
            
            //Este bloque es para pedirle a cada SD sus peliculas
                    SalidaVecino1.writeUTF(PPS); //PPS significa Pelicula Por Servidor
                    SalidaVecino2.writeUTF(PPS);
                    SalidaVecino3.writeUTF(PPS);
            //Aqui creo mis String donde guardare la informacion que me mande cada SD                
                    String MensajeServidor1 = EntradaVecino1.readLine();
                    String MensajeServidor2 = EntradaVecino2.readLine();
                    String MensajeServidor3 = EntradaVecino3.readLine();
                            
      
               /*     
             //llamo al metodo que me llenara el archvo con las peliculas que tiene cada servidor
                    //ManejoDeArchivos archivo = new ManejoDeArchivos();
                    //archivo.CrearArchivo();//creo el archivo "PeliculasEnSD.txt" y el archivo "ControlDeSolicitudes.txt"
                    archivo.llenarArchivo("PeliculasEnSD.txt",MensajeServidor1.toString());//llamo el metodo llenarArchivo y le mando el mensaje que me devolvio los SD
                    archivo.llenarArchivo("PeliculasEnSD2.txt",MensajeServidor2.toString());
                    archivo.llenarArchivo("PeliculasEnSD3.txt",MensajeServidor3.toString());*/
                    
                 
                
             
                
           
               
                
           //Este bloque es para cerrar todas las entradas
                EntradaVecino1.close();
                EntradaVecino2.close();
                EntradaVecino3.close();           
           //Este bloque es para cerrar todas las salidas
                SalidaVecino1.close();
                SalidaVecino2.close();
                SalidaVecino3.close();
           //este bloque es para cerrar todos los sockets
                SocketVecino1.close();
                SocketVecino2.close();
                SocketVecino3.close();
                
                
         
            
        } catch (IOException ex) {
          //  Logger.getLogger(ClienteSC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   /* public void ImprimirLista(ArrayList <Server> lista)
    {
        if(CrearLista)
        {
            LimpiarTextArea();
            for( int i = 0 ; i  < lista.size(); i++)
            {
                  JOptionPane.showMessageDialog(null, "NODO #"+i+"\n"
                                    +"Servidor Actual"+"\n"+lista.get(i).getIpPropia()+" "+lista.get(i).puertoPropio+"\n"
                                    +"VECINO 1"+"\n"+lista.get(i).getIp1()+" "+lista.get(i).puerto1+"\n"
                                    +"VECINO 2"+"\n"+lista.get(i).getIp2()+" "+lista.get(i).puerto2+"\n"
                                    +"VECINO 3"+"\n"+lista.get(i).getIp3()+" "+lista.get(i).puerto3+"\n");   
                SetTextArea("NODO #"+i+"\n"
                           +"Servidor Actual"+"     IP:"+lista.get(i).getIpPropia()+"   Puerto:"+lista.get(i).puertoPropio+"\n"
                           +"VECINO 1"+"     IP:"+lista.get(i).getIp1()+"   Puerto:"+lista.get(i).puerto1+"\n"
                           +"VECINO 2"+"     IP:"+lista.get(i).getIp2()+"   Puerto:"+lista.get(i).puerto2+"\n"
                           +"VECINO 3"+"     IP:"+lista.get(i).getIp3()+"   Puerto:"+lista.get(i).puerto3+"\n");
            }
        }
        else
            SetTextArea("DEBE CREAR LA LISTA PRIMERO");
    }*/
   
    
    
    
    //este metodo te devolvera true si la IP y el puerto del vecino del vecino es igual al servidor quien pide la informacion
    
    
    String cadena; 
    public void Setcadena(String cadena)
    {
        this.cadena = cadena;
    }

    public String getCadena() {
        return cadena;
    }
    
   
    
    
    /*
    public List<String> getVecinos(){
        System.out.println("Los vecinos que se han conectado son");
        return listaVecinos;
    }
    public List<String> agregarVecinos(String Vecino){
        System.out.println("se ha agregado un vecino y es "+Vecino);
        listaVecinos.add(Vecino);
         Iterator iter = listaVecinos.iterator();
                while (iter.hasNext())
                    System.out.println(iter.next());
        return listaVecinos;
    }
    */
    
    
}
