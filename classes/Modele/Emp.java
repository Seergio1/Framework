package Modele;
import Annotation.*;

public class Emp {
	
	
	public Emp(){
		
	}
	
	@MethodAnnotation(chemin="Emp-add")
	public void add(){
		System.out.println("methode ajouter");
	}

	@MethodAnnotation(chemin="Emp-delete")
	public void delete(){
		System.out.println("methode effacer");
	}
}