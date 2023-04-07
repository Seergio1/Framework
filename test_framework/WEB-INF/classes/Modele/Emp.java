package Modele;
import etu1811.framework.*;
import java.util.Vector;

import Annotation.*;

public class Emp {

	public Emp() {

	}

	@MethodAnnotation(chemin = "Emp-form.do")
	public ModelView makeForm() {
		Vector<String> allData = new Vector<String>();
		allData.add("Sergio");
		allData.add("ETU001811");
		allData.add("W-63");
		String urlHtml = "/form.jsp";
		ModelView modeleView = new ModelView();
		modeleView.addItem("allData",allData);
		modeleView.setView(urlHtml);
		return modeleView;
	}


	

}