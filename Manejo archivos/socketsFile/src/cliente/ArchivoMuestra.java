package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alc_d
 */

public class ArchivoMuestra {
    
        
    
            public void escribirTextoArchivo(String nombreArchivo, List lista, int numero) {
                String texto = "";
                FileWriter salida = null;
                try {
                    salida = new FileWriter(nombreArchivo);
                    BufferedWriter escritor = new BufferedWriter(salida);
                    //escritor.write(texto);
                    Iterator iterator = lista.iterator();
                    while(iterator.hasNext()){
                        texto = (String) iterator.next();
                        System.out.println("escritura "+texto);
                        escritor.write(texto + "\n");
                        escritor.newLine();
                        
                    }
                    /*for (int x=0;x<numero;x++){
                        escritor.append(lista.+"\n");
                    }*/
                    escritor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (salida != null) {
                        try {
                            salida.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }   
                    }   
                }

            }
     
    public Boolean existe(String nombreArchivo){
        File archivo = null;
        archivo = new File("envio\\"+nombreArchivo);
        if(archivo.exists()) {
            return true;
        }
            return false;
    }
    public int contarLineas(String nombreArchivo){
        int numero = 0;
        String texto="";
        FileReader archivo = null;
        String linea ="";
        try{
            archivo = new FileReader(nombreArchivo);
            BufferedReader lector = new BufferedReader(archivo);
            while((linea = lector.readLine()) != null){
                numero++;
            }
            
        }catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
          if (archivo != null) {
            try {
              archivo.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }

        return numero;
               
    }
    
    public List<String> leerTextoArchivo(String nombreArchivo) {
        List lista;
        lista = new ArrayList();
        int numero =0;
        String texto = "";
        FileReader archivo = null;
        String linea = "";
        try {
          archivo = new FileReader(nombreArchivo);
          BufferedReader lector = new BufferedReader(archivo);
          while ((linea = lector.readLine()) != null) {
            //texto += linea + "\n";
             lista.add(linea);
            numero++;
          }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
          if (archivo != null) {
            try {
              archivo.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
        return lista;
      }
    
}
