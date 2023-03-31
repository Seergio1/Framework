package Modele;
import etu1811.framework.*;
import Annotation.*;

public class Emp {

	public Emp() {

	}

	@MethodAnnotation(chemin = "Emp-form.do")
	public ModelView makeForm() {
		String urlHtml = "/form.jsp";
		ModelView modeleView = new ModelView();
		modeleView.setView(urlHtml);
		return modeleView;
	}


	

}