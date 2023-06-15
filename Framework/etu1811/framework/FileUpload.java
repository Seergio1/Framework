package etu1811.framework;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
public class FileUpload {
    
    String nomFichier;
    String path;
    byte[] file;
    public FileUpload(String nomFichier, String path, byte[] file) {
        this.nomFichier = nomFichier;
        this.path = path;
        this.file = file;
    }
    public FileUpload() {
        
    }
    public String getNomFichier() {
        return nomFichier;
    }
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }
    public void uploadFichier() {
        try {
            Path filePath = Path.of(this.path, this.nomFichier);
            Files.write(filePath, this.file, StandardOpenOption.CREATE);
            System.out.println("Fichier telecharger");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
