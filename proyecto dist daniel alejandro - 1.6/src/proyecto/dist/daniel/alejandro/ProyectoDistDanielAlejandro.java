/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dist.daniel.alejandro;
import servidor.servidor;
import cliente.cliente;
/**
 *
 * @author JASO VAIO
 */
public class ProyectoDistDanielAlejandro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new servidor(55001).start();
        
    }
    
}
