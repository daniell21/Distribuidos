package servidor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JASO VAIO
 */
public class Archivo {
    private String directorio;

    public Archivo(String directorio) {
        this.setDirectorio(directorio);
    }

    public void crearDirectorio(){
        File folder = new File(this.getDirectorio());
        if (!folder.isDirectory()){
            folder.mkdir();
            System.out.println("Se creo el directorio " + this.getDirectorio());
        }
    }
    
    
    
    public List<String> listarArchivos(){
        List response = new ArrayList();
        try {
            
            File folder = new File(this.getDirectorio());
            File[] listOfFiles = folder.listFiles();
      
            System.out.println("Directory length " + listOfFiles.length);
            for (int i = 0; i < listOfFiles.length; i++) {
               if (listOfFiles[i].isFile()) {
                 System.out.println("File " + listOfFiles[i].getName());
                 response.add(listOfFiles[i].getName());
               } else if (listOfFiles[i].isDirectory()) {
                 System.out.println("Directory " + listOfFiles[i].getName());
               }
            }
        } catch (Exception e) {
            System.out.println("Excepcion Listar Archivos: " + e.getMessage());
        }
        return response;
    }
    
    public List<String> fileContents(String fileName){
        List response = new ArrayList();
        boolean found = false;
        int i = 55000;
        while (!found && (i<55100)){
            FileReader fr = null;
            try {
                fr = new FileReader("src\\files\\" +Integer.toString(i)+ "\\" +fileName);
                BufferedReader br = new BufferedReader(fr);
                String s;
                while((s = br.readLine()) != null) {
                    System.out.println(s);
                    response.add(s);
                }   
                found = true;
                fr.close();
            } catch (FileNotFoundException ex1) {
                //Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex1);
                i++;
            } catch (IOException ex1) {
                //Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex1);
            } 
            
        }
        return response;
    }
    
    
    public String UpdatefileContents(String fileName, String content){
        String response = "not found";
        boolean found = false;
        int i = 55000;
        while (!found && (i<55100)){
            FileReader fr = null;
            FileWriter fw = null;
            try {
                fr = new FileReader("src\\files\\" +Integer.toString(i)+ "\\" +fileName);
                BufferedReader br = new BufferedReader(fr);
                found = true;
                fr.close();
                fw = new FileWriter("src\\files\\" +Integer.toString(i)+ "\\" +fileName);
                fw.write(content);
                fw.close();
                response = "file updated";
            } catch (FileNotFoundException ex1) {
                //Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex1);
                i++;
            } catch (IOException ex1) {
                //Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex1);
            } 
            
        }
        return response;
    }

    /**
     * @return the directorio
     */
    public String getDirectorio() {
        return directorio;
    }

    /**
     * @param directorio the directorio to set
     */
    public void setDirectorio(String directorio) {
        this.directorio = "src\\files\\" + directorio;
        
    }


}
