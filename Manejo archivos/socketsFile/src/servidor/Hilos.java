package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author alc_d
 */
public class Hilos extends Thread{

    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    //DataOutputStream output;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    String file;
    byte[] receivedData;
    int in;

    public Hilos(Socket socket) {
        this.socket = socket;
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            bis = new BufferedInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
         public void desconectar(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    
    public void run(){
        
        byte[] receivedData;
    int in;
    String file;
    try{
        String accion = "";
       
       
            
            do{
            accion = entrada.readUTF();//recibo el mensaje del cliente
            System.out.println("el cliente esta escribiendo "+accion);//muestro por consola lo que envio en cliente
            
            
            if(accion.equals("adios")){
                System.out.println("el cliente se va");
                salida.writeUTF("adios");
                desconectar();  
            }
            
            if(accion.equals("envio")){
                //int puerto = Integer.parseInt(entrada.readUTF());
                System.out.println("obtuve el puerto por el que se encuentra el cliente ");
               System.out.println("se procedera a prepararse para recibir el archivo"); 
                FileOutputStream destino;
                destino = new FileOutputStream("C:\\Users\\alc_d\\Documents\\NetBeansProjects\\socketsFile\\testo3.txt");
                byte[] buffer = new byte[2097152]; 
                    int len; 
                    while((len=entrada.read(buffer))>0)
                { 
                    destino.write(buffer,0,len); 
                    //i++;
                    //System.out.println(i+" len:"+len);
                } 
                    desconectar();
                    System.out.println("sali");
                    //destino.close();
                System.out.println("archivo recibido");
               
                salida.writeUTF("ArchivoRecibido");
                
                 accion = entrada.readUTF();
                System.out.println("mensaje del cliente "+accion);
               
                /*
                
                receivedData = new byte[1024];
            
            
            //Recibimos el nombre del fichero
            //file = dis.readUTF();
            file = "testo.txt";
                System.out.println("el nombre del archivo es "+file);
            file = file.substring(file.indexOf('\\')+1,file.length());
            //Para guardar fichero recibido
            bos = new BufferedOutputStream(new FileOutputStream(file));
            while ((in = bis.read(receivedData)) != -1){
                bos.write(receivedData,0,in);
                System.out.println("archivo recibido");
            } 
            bos.close();
            bis.close();
            
           */
            }
            }while (!accion.equals("adios"));
            
           
        
        }catch (Exception e ) {
        System.err.println(e);
        }
        
        }
    
}
