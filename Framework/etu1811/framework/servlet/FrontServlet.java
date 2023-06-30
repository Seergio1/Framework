package etu1811.framework.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import utilities.*;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import etu1811.framework.FileUpload;
import etu1811.framework.Mapping;
import etu1811.framework.ModelView;
import java.util.Vector;

import javax.print.DocFlavor.STRING;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import Annotation.*;

@MultipartConfig(maxFileSize=20000000)
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;
    HashMap<String,Object> allClassSingleton; 
    HashMap<String,Object> Allsession;
    Vector<Class<?>> classes;
    ModelView modelView;

    @Override
    public void init()throws ServletException{ 
        super.init();
        Utilitaire utile = new Utilitaire();
        this.classes = new Vector<Class<?>>();
        this.MappingUrls = new HashMap<String,Mapping>();
        this.allClassSingleton = new HashMap<String,Object>();

        String pathToClasses = this.getInitParameter("pathClass");
        String classesPath = this.getServletContext().getRealPath(pathToClasses);

        try{
          this.classes = utile.getAllClasses(classesPath + "\\", classesPath, new Vector<Class<?>>());
          utile.setMappingUrls(this.MappingUrls,this.classes);
          utile.setAllClassSingleton(this.allClassSingleton, this.classes);
      }catch (Exception e) {
              //erreur
      }
  }


  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    res.setContentType( "text/html" );
    try {
        processRequest(res, req);
   } catch (Exception e) {
    out.println("ExceptionGET: " + e.getMessage());
}
}



protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    try {
       processRequest(res, req);
   } catch (Exception e) {
    out.println("ExceptionPOST: " + e.getMessage());
}
}



public void processRequest(HttpServletResponse res, HttpServletRequest req) throws Exception {
    Utilitaire utile = new Utilitaire();
    PrintWriter out = res.getWriter();
    try{
        StringBuffer url = req.getRequestURL();
        String contextUrl = utile.getContextUrl(url).replace("/", "");
        HashMap<String,Mapping> contextInfo = utile.getContextInformation(this.MappingUrls,contextUrl);

       
      
        Class<?> class1 = Class.forName(contextInfo.get(contextUrl).getClassName());
        Object o;
        
        if(utile.checkIfSingleton(class1)){
            o = utile.getClassMatch(this.allClassSingleton,class1);
            utile.resetAttribut(o);
            // out.println(o);
        }else{
            o = class1.getDeclaredConstructor().newInstance();
            // out.println(o);
        }
        

        if(utile.checkAuthFunction(contextInfo, o.getClass())){
            
            String checkProfil = this.getInitParameter("session_nom");
            HttpSession session_ = req.getSession();
            if(session_.getAttribute(checkProfil)!=null){
                Method methodAuth = utile.getMethodCalled(contextInfo, class1);
                
                if(methodAuth.getDeclaredAnnotation(Auth.class).profil().equals(session_.getAttribute(checkProfil))){
                    this.modelView = utile.callFunction(contextInfo,o);
                }else{
                    throw new Exception("Vous n'avez pas acces a cette fonction");
                }
                
            }else{
                throw new Exception("vous n'avez pas connecte");
            }
        }else{
            this.modelView = utile.callFunction(contextInfo,o);
            if(modelView.getSession()!=null){
                this.Allsession = modelView.getSession();
                HttpSession session = req.getSession();
                for(String key : this.Allsession.keySet()){
                    session.setAttribute(key, this.Allsession.get(key));
                }
            }
        }
        
        
        

        Field[] fields = o.getClass().getDeclaredFields();
        Method[] method = o.getClass().getDeclaredMethods(); //toutes les methodes présent
        Method resultat = utile.getMethodWithParam(method, contextUrl);//methode appelée
        String fieldname;
        String value;



        
        
        for(Field f : fields){
            //upload fichier
            if(f.getType().equals("FileUpload")){
                //info sur l'input type file
                Part filepart= req.getPart("file");
                
                if (filepart != null) {
                    String filename = filepart.getSubmittedFileName();
                    byte[] filebyte = new byte[(int) filepart.getSize()];
                    filepart.getInputStream().read(filebyte);
                    FileUpload file_uplaod = new FileUpload(filename,modelView.getPathUpload(),filebyte);
                    file_uplaod.uploadFichier();
                }
            }
            //----------------------------------------------------------------------------------------------
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
    }catch (Exception e) {
        out.println("Exception: " + e.getMessage());
    }
    
}

}
