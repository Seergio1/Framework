package etu1811.framework.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import utilities.*;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import etu1811.framework.Mapping;
import etu1811.framework.ModelView;
import java.util.Vector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import Annotation.*;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;
    Vector<Class<?>> classes;

    @Override
    public void init()throws ServletException{ 
        super.init();
        Utilitaire utile = new Utilitaire();
        this.classes = new Vector<Class<?>>();
        this.MappingUrls = new HashMap<String,Mapping>();

        String pathToClasses = this.getInitParameter("pathClass");
        String classesPath = this.getServletContext().getRealPath(pathToClasses);

        try{
          this.classes = utile.getAllClasses(classesPath + "\\", classesPath, new Vector<Class<?>>());
          utile.setMappingUrls(this.MappingUrls,this.classes);
      }catch (Exception e) {
              //erreur
      }
  }


  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    res.setContentType( "text/html" );
    try {
       Utilitaire utile = new Utilitaire();
       String contextUrl = processRequest(res, req).replace("/","");
       HashMap<String,Mapping> contextInfo = utile.getContextInformation(this.MappingUrls,contextUrl);
    //    out.println(contextUrl);

       ModelView modelView = utile.callFunction(contextInfo);
       
        Class<?> class1 = Class.forName(contextInfo.get(contextUrl).getClassName());
        Object o = class1.getDeclaredConstructor().newInstance();
        Field[] fields = o.getClass().getDeclaredFields();
        Method[] method = class1.getDeclaredMethods(); //toutes les methodes présent
        Method resultat = utile.getMethodWithParam(method, contextUrl);//methode appelée
        String fieldname;
        String value;
        
        for(Field f : fields){
            fieldname = f.getName();
            value = req.getParameter(fieldname);
            //si la fonction n'a pas de paramètre
            if( resultat.getParameterCount()==0){
                if(value!=null){
                    out.println("lasa ato");
                    if (f.getType() == String.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), String.class).invoke(o,
                                value);
                    } else if (f.getType() == Double.class || f.getType() == double.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), Double.class).invoke(o,
                                Double.parseDouble((value)));
                    } else if (f.getType() == LocalDate.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), LocalDate.class).invoke(o,
                                LocalDate.parse(value));
                    } else if (f.getType() == Integer.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), Integer.class).invoke(o,
                                Integer.parseInt((value)));
                    } else if (f.getType() == int.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), int.class).invoke(o,
                                Integer.parseInt((value)));
                    } else if (f.getType() == Float.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), Float.class).invoke(o,
                                Float.parseFloat((value)));
                    } else if (f.getType() == float.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), float.class).invoke(o,
                                Float.parseFloat((value)));
                    } else if (f.getType() == Boolean.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), Boolean.class).invoke(o,
                                Boolean.parseBoolean((value)));
                    } else if (f.getType() == boolean.class) {
                        o.getClass().getMethod("set" + utile.capitalizeFirstLetter(fieldname), boolean.class).invoke(o,
                                Boolean.parseBoolean((value)));
                    }
                   Object m = o.getClass().getMethod("get"+utile.capitalizeFirstLetter(fieldname)).invoke(o);
                   modelView.addItem(fieldname, value);
                }
            }
            
        }

        if(utile.isMethodHaveParam(this.classes,contextUrl)){
                //les noms de tous les paramètres
                Enumeration<String> parameterNames = req.getParameterNames(); 
                int j = 0;
                while (parameterNames.hasMoreElements()) {
                    String paramName = parameterNames.nextElement();
                    // les valeurs de chaque paramètre
                    String paramValue = req.getParameter(paramName);
                    //si le nombre de paramètre saisi est egal au nombre de paramètre de la fonction appelée
                    if(req.getParameterMap().size() == resultat.getParameterCount()){
                        out.println(paramName+","+paramValue+","+resultat.getParameters()[j].getType());
                    }else if(req.getParameterMap().size() < resultat.getParameterCount()){
                        out.println("Il manque une/des parametre(s) a cette fonction ou une/des parametre sont fausse(s)");
                    }else if(req.getParameterMap().size() > resultat.getParameterCount()){
                        out.println("Le nombre de parametre depasse celle de la fonction que vous essayez d'acceder");
                    }
                    j++;
                }
        }
       

        if(modelView!=null){
            if(modelView.getData()!=null){
                HashMap<String,Object> data = modelView.getData();
                for (String nomVariable : data.keySet()) {
                    req.setAttribute(nomVariable,data.get(nomVariable));
                }
            }
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(modelView.getView());
            dispatcher.forward(req, res);
        }
       
       
       
   } catch (Exception e) {
    out.println("Exception1: " + e.getMessage());
}
}



protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    try {
       Utilitaire utile = new Utilitaire();
       String contextUrl = processRequest(res, req).replace("/","");
       HashMap<String,Mapping> contextInfo = utile.getContextInformation(this.MappingUrls,contextUrl);
   } catch (Exception e) {
    out.println("Exception: " + e.getMessage());
}
}


public String processRequest(HttpServletResponse res, HttpServletRequest req) throws Exception {
    Utilitaire utile = new Utilitaire();
    PrintWriter out = res.getWriter();
    String result = "";
    try{
        StringBuffer url = req.getRequestURL();
        result = utile.getContextUrl(url);
    }catch (Exception e) {
        out.println("Exception: " + e.getMessage());
    }
    return result;
}

}
