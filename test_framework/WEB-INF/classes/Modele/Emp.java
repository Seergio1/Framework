package Modele;
import etu1811.framework.*;
import utilities.Utilitaire;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;

import Annotation.*;


public class Emp {
	String nom;
	int id;
	Double prix;
	LocalDate dateNaissance;
	int isAdmin; // 0 non 1 oui


	
	
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
	public int getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

	@Auth(profil = "admin")
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
	@MethodAnnotation(chemin = "Emp-upload.do")
	public ModelView makeForm2() {
		String urlHtml = "/upload.jsp";
		ModelView modeleView = new ModelView();
		
		modeleView.setView(urlHtml);
		return modeleView;
	}
	

	@MethodAnnotation(chemin = "Emp-sayHello.do")
	public void sayHello(String mot,int nbr){
		
	}


	@MethodAnnotation(chemin = "Emp-makeFormLogin.do")
	public ModelView makeFormLogin(){
		String urlHtml = "/formLogin.jsp";
		ModelView modelView = new ModelView();
		modelView.setView(urlHtml);
		return modelView;
	}
	@MethodAnnotation(chemin = "Emp-traitLogin.do")
	public ModelView traitLogin(){
		String urlHtml = "/traitementLogin.jsp";
		ModelView modelView = new ModelView();
		modelView.setView(urlHtml);
		return modelView;
	}

	

	@MethodAnnotation(chemin = "Emp-login.do")
	public ModelView login(){
		String urlHtml = "/index.jsp";
		ModelView modelView = new ModelView();
		
		modelView.addSession("Profil", "admin");
		modelView.setView(urlHtml);
		return modelView;
	}
	
	@MethodAnnotation(chemin = "Emp-login2.do")
	public ModelView login2(){
		String urlHtml = "/index.jsp";
		ModelView modelView = new ModelView();
		
		modelView.addSession("Profil", "client");
		modelView.setView(urlHtml);
		return modelView;
	}


	@MethodAnnotation(chemin = "Emp-logOut.do")
	public ModelView logOut(){
		String urlHtml = "/formLogin.jsp";
		ModelView modelView = new ModelView();
		
		modelView.addSession("Profil", null);
		modelView.setView(urlHtml);
		return modelView;
	}
	public static void main(String[] args) {
		Utilitaire utile = new Utilitaire();
		
		HashMap<String,Object> session = new HashMap<String,Object>();
		HashMap<String,Mapping> contextInfo = new HashMap<String,Mapping>();
		Mapping mapping = new Mapping();
		mapping.setClassName("Modele.Emp");
		mapping.setMethod("getInfo");
		contextInfo.put("Emp-info.do", mapping);
		session.put("Profil", "admin");
	}


	



	

}