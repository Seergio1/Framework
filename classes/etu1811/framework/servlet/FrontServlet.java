package etu1811.framework.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import utilities.*;
import java.util.HashMap;
import etu1811.framework.Mapping;
import java.util.Vector;
import java.lang.reflect.Method;
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

        try {
         Utilitaire utile = new Utilitaire();
         String contextUrl = processRequest(res, req).replace("/","");
         HashMap<String,Mapping> contextInfo = utile.getContextInformation(this.MappingUrls,contextUrl);
         for (String key : contextInfo.keySet()) {
            out.println(key);
            out.println("Classe => " + contextInfo.get(key).getClassName());
            out.println("Fonction => " + contextInfo.get(key).getMethod());
            out.println("");
        }

        






    } catch (Exception e) {
        out.println("Exception: " + e.getMessage());
    }
}

protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
        try {
         Utilitaire utile = new Utilitaire();
         String contextUrl = processRequest(res, req).replace("/","");
         HashMap<String,Mapping> contextInfo = utile.getContextInformation(this.MappingUrls,contextUrl);
         for (String key : contextInfo.keySet()) {
            out.println(key);
            out.println("Classe : " + contextInfo.get(key).getClassName());
            out.println("fonction : " + contextInfo.get(key).getMethod());
            out.println("");
        }

    } catch (Exception e) {
        out.println("Exception: " + e.getMessage());
    }
}


public String processRequest(HttpServletResponse res, HttpServletRequest req) throws Exception {
    Utilitaire utile = new Utilitaire();
    PrintWriter out = res.getWriter();
    String result = "";
        try{
            StringBuffer url = req.getRequestURL(); //url iray manonontolo
            result = utile.getContextUrl(url);
        }catch (Exception e) {
            out.println("Exception: " + e.getMessage());
        }
        return result;
    }

}
