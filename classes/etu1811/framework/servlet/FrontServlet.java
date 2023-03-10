package etu1811.framework.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import utilitaire.Utilitaire;
import java.util.HashMap;
import etu1811.framework.Mapping;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;

    protected void doGet(HttpServletRequest req,ss HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        try {
            String contextUrl = processRequest(res, req);
            out.println(contextUrl);
        } catch (Exception e) {
            out.println("Exception: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        try {
            String contextUrl = processRequest(res, req);
            out.println(contextUrl);
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
