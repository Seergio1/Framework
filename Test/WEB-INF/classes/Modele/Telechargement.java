package Modele;
import etu1811.framework.*;
import Annotation.*;

public class Telechargement {
    FileUpload fileUpload;

    @MethodAnnotation(chemin = "Telechargement-uploadFile.do")
    public ModelView uploadFile(){
        String urlHtml = "/upload.jsp";
		ModelView modeleView = new ModelView();
		modeleView.setView(urlHtml);
        modeleView.setPathUpload("C:/Program Files/Apache Software Foundation/Tomcat 10.1/webapps/Telechargement");
		return modeleView;
    }
}
