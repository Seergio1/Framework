package etu1811.framework;
import java.util.HashMap;
import java.io.File;

public class ModelView{
	String view;
	HashMap<String,Object> data = new HashMap<String,Object>();
	HashMap<String,Object> session = new HashMap<String,Object>();
	
	String pathUpload;
	


	


	// Constructor
	public ModelView(){
		
	}

	public String getPathUpload() {
		return pathUpload;
	}
	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
		File directory = new File(this.pathUpload);

        if (!directory.exists()) { // Vérifie si le dossier n'existe pas déjà
            if (directory.mkdirs()) {
                System.out.println("Le dossier a été créé avec succès !");
            } else {
                System.out.println("Erreur lors de la création du dossier.");
            }
        } else {
            System.out.println("Le dossier existe déjà.");
        }
	}

	public String getView(){
		return this.view;
	}
	public void setView(String view){
		this.view = view;
	}
	public HashMap<String,Object> getData(){
		return this.data;
	}
	public void setData(HashMap<String,Object> data){
		this.data = data;
	}

	public void addItem(String nomVariable,Object valeurVariable){
		this.data.put(nomVariable,valeurVariable);
	}
	public void addSession(String nomVariable,Object valeurVariable){
		this.session.put(nomVariable,valeurVariable);
	}
	public HashMap<String, Object> getSession() {
		return session;
	}
}