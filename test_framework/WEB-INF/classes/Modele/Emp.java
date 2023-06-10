package Modele;
import etu1811.framework.*;

import java.time.LocalDate;
import java.util.Vector;

import Annotation.*;

public class Emp {
	String nom;
	int id;
	Double prix;
	LocalDate dateNaissance;


	
	public Emp() {

	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setPrix(Double prix){
		this.prix = prix;
	}
	public String getNom(){
		return this.nom;
	}
	public int getId(){
		return this.id;
	}
	public Double getPrix(){
		return this.prix;
	}

	@MethodAnnotation(chemin = "Emp-info.do")
	public ModelView getInfo() {
		Vector<String> allData = new Vector<String>();
		allData.add("Sergio");
		allData.add("ETU001811");
		allData.add("W-63");
		String urlHtml = "/info.jsp";
		ModelView modeleView = new ModelView();
		modeleView.addItem("allData",allData);
		modeleView.setView(urlHtml);
		return modeleView;
	}

	@MethodAnnotation(chemin = "Emp-form.do")
	public ModelView makeForm() {
		String urlHtml = "/form.jsp";
		ModelView modeleView = new ModelView();
		modeleView.setView(urlHtml);
		return modeleView;
	}

	@MethodAnnotation(chemin = "Emp-save.do")
	public ModelView save() {
		String urlHtml = "/info2.jsp";
		ModelView modeleView = new ModelView();
		modeleView.setView(urlHtml);
		return modeleView;
	}
	@MethodAnnotation(chemin = "Emp-saveByLink.do")
	public ModelView saveByLink(){
		String urlHtml = "/info2.jsp";
		ModelView modeleView = new ModelView();
		modeleView.setView(urlHtml);
		return modeleView;
	}



	

}