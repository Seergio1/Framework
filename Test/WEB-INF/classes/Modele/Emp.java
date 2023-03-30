package Modele;
import Annotation.*;

public class Emp {
	
	
	public Emp(){
		
	}
	
	@MethodAnnotation(chemin="Emp-view")
	public void view(){
		System.out.println("methode view");
	}

	@MethodAnnotation(chemin="Emp-delete")
	public void delete(){
		System.out.println("methode effacer");
	}

}