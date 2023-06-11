package etu1811.framework;
import java.util.HashMap;

public class ModelView{
	String view;
	HashMap<String,Object> data = new HashMap<String,Object>();
	// Constructor
	public ModelView(){
		
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
}