<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>
<% 

    if(request.getAttribute("erreurAuth")!=null){
        String erreurAuth = (String)request.getAttribute("erreurAuth");
        out.println(erreurAuth);
    }
	
%>