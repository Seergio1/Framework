<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>
<% 

    if(request.getAttribute("isAdmin")!=null){
        int isAdmin = Integer.parseInt((String)request.getAttribute("isAdmin")); ;
        if(isAdmin == 0){
            response.sendRedirect("Emp-login2.do");
        }else{
            response.sendRedirect("Emp-login.do");
        }
    }else{
        response.sendRedirect("formLogin.jsp");
    }
	
%>