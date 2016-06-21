/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Comando;

/**
 *
 * @author JASO VAIO
 */
public class servidor extends Thread {

    private ServerSocket server;
    private Socket socket;
    private int puerto;
    private DataOutputStream salida;//Enviar datos
    private DataInputStream entrada;//Guardar los datos provenientes del exterior
    private List<Socket> serversConnectedToMe;
    private List<Socket> clientsConnectedToMe;

    public servidor(int puerto) {
        this.setPuerto(puerto);
        this.setServersConnectedToMe(new ArrayList<Socket>());
        this.setClientsConnectedToMe(new ArrayList<Socket>());
    }

    public String whoAreYou() {
        String command = "";
        try {
            this.setSalida(new DataOutputStream(this.getSocket().getOutputStream()));
            this.getSalida().writeUTF("who are you? server/client");
            this.setEntrada(new DataInputStream(this.getSocket().getInputStream()));
            command = this.getEntrada().readUTF();
            System.out.println("Cliente responde: " + command);
            if (command.equalsIgnoreCase("server")) {
                this.getServersConnectedToMe().add(this.getSocket());
            }
            if (command.equalsIgnoreCase("client")) {
                this.getClientsConnectedToMe().add(this.getSocket());
            }

        } catch (IOException ex) {
            System.out.println("Excepcion: " + ex.getMessage());
            Logger.getLogger(Comando.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
        } finally {
            return command;
        }

    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Iniciando Server " +  String.valueOf(this.getPuerto()));
        boolean listen = true;
        boolean retry = true;
        while (retry) {
            try {
                this.setServer(new ServerSocket(this.getPuerto()));
                while (listen) {
                    this.setSocket(this.getServer().accept());
                    String type = this.whoAreYou();
                    new Comando(this.getSocket(), type).start();
                    retry = false;
                }
                this.getServer().close();
            } catch (IOException e) {//Se captura la exception
                System.out.println("Puerto " + this.getPuerto() + " en uso");
                this.setPuerto(this.getPuerto() + 1);
            } catch (Exception e) {
                System.out.println("Excepcion: " + e.getMessage());
            }
        }
    }

    /**
     * @return the server
     */
    public ServerSocket getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(ServerSocket server) {
        this.server = server;
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

    /**
     * @return the serversConnectedToMe
     */
    public List<Socket> getServersConnectedToMe() {
        return serversConnectedToMe;
    }

    /**
     * @param serversConnectedToMe the serversConnectedToMe to set
     */
    public void setServersConnectedToMe(List<Socket> serversConnectedToMe) {
        this.serversConnectedToMe = serversConnectedToMe;
    }

    /**
     * @return the clientsConnectedToMe
     */
    public List<Socket> getClientsConnectedToMe() {
        return clientsConnectedToMe;
    }

    /**
     * @param clientsConnectedToMe the clientsConnectedToMe to set
     */
    public void setClientsConnectedToMe(List<Socket> clientsConnectedToMe) {
        this.clientsConnectedToMe = clientsConnectedToMe;
    }

}
