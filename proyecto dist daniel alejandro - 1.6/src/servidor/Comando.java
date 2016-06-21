/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Archivo;
/**
 *
 * @author JASO VAIO
 */
public class Comando extends Thread{
    private Socket cliente;
    private DataInputStream input;
    private DataOutputStream output;
    private Archivo archivos;
    private String clientType;
    
    public Comando(Socket cliente, String type) {
        this.setCliente(cliente); 
        this.setArchivos(new Archivo(Integer.toString(this.getCliente().getLocalPort())));
        this.setClientType(type);
        this.getArchivos().crearDirectorio();
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        this.listenCommand();
    }
    
    public void listenCommand(){
   
        try {
            String command ="";
            while(!command.equalsIgnoreCase("bye")){
                this.setInput(new DataInputStream(this.getCliente().getInputStream()));
                command = this.getInput().readUTF();
                System.out.println("Cliente manda a ejecutar " + command);
                if(command.equalsIgnoreCase("listFiles")){
                    this.listFiles();
                }
                if(command.equalsIgnoreCase("listAllFiles")){
                    this.listAllFiles();
                }
                
                if(command.contains("file_content:")){
                    String[] parts = command.split(":");
                    this.fileContents(parts[1]);
                }
                
                if(command.contains("update_file_content:")){
                    String[] parts = command.split(":");
                    this.updateFileContents(parts[1], parts[2]);
                }
                
                if(command.contains("create_file:")){
                    String[] parts = command.split(":");
                    this.createFile(parts[1]);
                }
                if(command.contains("delete_file:")){
                    String[] parts = command.split(":");
                    this.deleteFile(parts[1]);
                }
                
            }
        } 
        catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
           Logger.getLogger(Comando.class.getName()).log(Level.SEVERE, null, ex);
        }

        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
    }
    
    
    
    public void fileContents(String fileName){
  
        Archivo archivos = new Archivo(Integer.toString(this.getCliente().getLocalPort()));
        
        List<String> nombres = archivos.fileContents(fileName);
        Iterator iterator = nombres.iterator();
        try {
            this.setOutput(new DataOutputStream(this.getCliente().getOutputStream()));
            while(iterator.hasNext()){
                this.getOutput().writeUTF((String) iterator.next());
            }
            this.getOutput().writeUTF("done");
        } 
        catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
            Logger.getLogger(Comando.class.getName()).log(Level.SEVERE, null, ex);
        }

        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
    }
    
    
    public void updateFileContents(String fileName, String fileContent){
        Archivo archivos = new Archivo(Integer.toString(this.getCliente().getLocalPort()));
        String response = archivos.UpdatefileContents(fileName, fileContent);
    }
    
    
     public void createFile(String fileName){
        Archivo archivos = new Archivo(Integer.toString(this.getCliente().getLocalPort()));
        String response = archivos.CreateFile(fileName);
    }
     
     
    public void deleteFile(String fileName){
        Archivo archivos = new Archivo(Integer.toString(this.getCliente().getLocalPort()));
        String response = archivos.deleteFile(fileName);
    }
    
    public void listFiles(){
  
        Archivo archivos = new Archivo(Integer.toString(this.getCliente().getLocalPort()));
        
        List<String> nombres = archivos.listarArchivos();
        Iterator iterator = nombres.iterator();
        try {
            this.setOutput(new DataOutputStream(this.getCliente().getOutputStream()));
            while(iterator.hasNext()){
                this.getOutput().writeUTF((String) iterator.next());
            }
            this.getOutput().writeUTF("done");
        } 
        catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
            Logger.getLogger(Comando.class.getName()).log(Level.SEVERE, null, ex);
        }

        catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
    }
    
    public void listAllFiles() throws IOException{
        
        for (int i = 55000; i < 55100; i++) {
            try {
                new ServerSocket(i).close();
            } catch (IOException ex) {
               
                Archivo archivos = new Archivo(Integer.toString(i));
                List<String> nombres = archivos.listarArchivos();
                Iterator iterator = nombres.iterator();
                this.setOutput(new DataOutputStream(this.getCliente().getOutputStream()));
                while(iterator.hasNext()){
                    this.getOutput().writeUTF((String) iterator.next());
                }
            }
             catch (Exception e) {
                System.out.println("Excepcion: " + e.getMessage());
            }
        }
        this.getOutput().writeUTF("done");
       
    }
    
    
    /**
     * @return the cliente
     */
    public Socket getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the input
     */
    public DataInputStream getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(DataInputStream input) {
        this.input = input;
    }

    /**
     * @return the output
     */
    public DataOutputStream getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(DataOutputStream output) {
        this.output = output;
    }

    /**
     * @return the archivos
     */
    public Archivo getArchivos() {
        return archivos;
    }

    /**
     * @param archivos the archivos to set
     */
    public void setArchivos(Archivo archivos) {
        this.archivos = archivos;
    }

    /**
     * @return the type
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * @param type the type to set
     */
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    
    
    
}
