package utilities;
import java.lang.StringBuffer;
import java.util.HashMap;
import java.util.Vector;
import java.io.File;
import etu1811.framework.Mapping;
import java.lang.reflect.Method;
import Annotation.*;

public class Utilitaire {
	Vector<String> allPackage = new Vector<String>();
	// Constructor
	public Utilitaire(){}


	public String getContextUrl(StringBuffer url){
        String[] urlSplit = url.toString().split("/");
        String contextUrl ="";
        for (int i = 4;i<urlSplit.length ;i++ ) {
           contextUrl+="/"+urlSplit[i];

       }

       return contextUrl;
   }

   public Vector<Class<?>> getAllClasses(String pathRacine, String pathTarget, Vector<Class<?>> classes)
   throws Exception {
    File file = new File(pathTarget);
    File[] files = file.listFiles();
    if (files != null) {
        for (int i = 0; i < files.length; i++) {
                // out.println(files[i].getAbsolutePath());
            String allPath = files[i].getAbsolutePath();
            File fileChild = new File(allPath);
            if (fileChild.isDirectory()) {
                getAllClasses(pathRacine, fileChild.getAbsolutePath(), classes);
            } else {
                if (allPath.endsWith(".class")) {
                    String className = allPath.replace(pathRacine, "");
                    classes.add(Class.forName(className.replace("\\", ".").replace(".class", "")));
                }
            }
        }
    }
    return classes;
}

public void setMappingUrls(HashMap<String,Mapping> map, Vector<Class<?>> allClasses){
    Vector<Class<?>> classes = new Vector<Class<?>>();
    Mapping mapping;
    try{
        classes = allClasses;

        for (Class<?> class1 : classes) {
            Method[] methods = class1.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
             if (methods[i].isAnnotationPresent(MethodAnnotation.class)) {
               mapping = new Mapping();
               mapping.setClassName(class1.getSimpleName());
               mapping.setMethod(methods[i].getName());
               // String key =  mapping.getClassName() + "-" + mapping.getMethod();
               String key = methods[i].getAnnotation(MethodAnnotation.class).chemin();
               map.put(key, mapping);                         
           }    
       }
   }

}catch(Exception e){
    System.out.println(e.getMessage());
}
}
public HashMap<String,Mapping> getContextInformation(HashMap<String,Mapping> MappingUrls,String annotationChemin){
    Mapping mapping;
    HashMap<String,Mapping> map = new  HashMap<String,Mapping>();
    for (String key : MappingUrls.keySet()) {
        if (annotationChemin.equals(key)) {
            mapping = new Mapping();
            mapping.setClassName(MappingUrls.get(key).getClassName());
            mapping.setMethod(MappingUrls.get(key).getMethod());
            map.put(key,mapping);
        }

    }
    return map;
}












}