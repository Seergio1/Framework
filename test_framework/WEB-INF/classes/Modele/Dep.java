package Modele;
import etu1811.framework.*;


import Annotation.*;

public class Dep {
    public Dep() {
    }

    String nomDep;

    public String getNomDep() {
        return nomDep;
    }

    public void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }
    @MethodAnnotation(chemin = "Dep-test.do")
	public void test() {

	}
}
