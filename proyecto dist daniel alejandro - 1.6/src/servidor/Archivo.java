package servidor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
