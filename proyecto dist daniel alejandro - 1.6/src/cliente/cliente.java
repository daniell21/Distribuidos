/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JASO VAIO
 */
public class cliente extends Thread{
    
    private Socket socket;
    private int puerto;
    private DataOutputStream salida;//Enviar datos
    private DataInputStream entrada;//Guardar los datos provenientes del exterior

    public cliente(int puerto) {
        this.puerto = puerto;
    }
    
   public void connect(){
        
        try {    
            InetAddress addr = InetAddress.getByName("127.0.0.1"); //Se crea un objeto de tipo InetAddress
            this.setSocket(new Socket(addr,this.getPuerto()));
            String answer = "";
            System.out.println("Conectado con el server: " +  String.valueOf(this.getPuerto()));
            this.setEntrada(new DataInputStream(this.getSocket().getInputStream()));
            answer = this.getEntrada().readUTF();
            if (answer.equalsIgnoreCase("who are you? server/client")){
                this.whoAmI();
            }
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
    
    }
    
    public void logout(){
   
        try {
            this.setSalida(new DataOutputStream(this.getSocket().getOutputStream()));
            this.getSalida().writeUTF("bye");
            this.getSocket().close();
        } 
        catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
            //Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
    }
    
    public void whoAmI(){
   
        try {
            this.setSalida(new DataOutputStream(this.getSocket().getOutputStream()));
            this.getSalida().writeUTF("client");
        } 
        catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
            //Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
    }
    
    
    public List<String> listFiles(){
        
        this.connect();
        List response = new ArrayList();
        try {
            this.setSalida(new DataOutputStream(this.getSocket().getOutputStream()));
            this.getSalida().writeUTF("listAllFiles");
            this.setEntrada(new DataInputStream(this.getSocket().getInputStream()));
            String answer = this.getEntrada().readUTF();
            while(!answer.equalsIgnoreCase("done")){
                System.out.println("Nombre de Archivo: " + answer );
                response.add(answer);
                this.setEntrada(new DataInputStream(this.getSocket().getInputStream()));
                answer = this.getEntrada().readUTF();
            }
            System.out.println("Todos los archivos recibidos" );
            
        } 
        catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
            //Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
        this.logout();
        return response;
    }
    
    

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        try {
            this.listFiles();
         
        } 
        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
        finally{
           
        }
    }

    
    
    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * @return the puerto
     */
    public int getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the salida
     */
    public DataOutputStream getSalida() {
        return salida;
    }

    /**
     * @param salida the salida to set
     */
    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    /**
     * @return the entrada
     */
    public DataInputStream getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }
    
    
    
    
}
