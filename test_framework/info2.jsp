<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>
<% 
    if(request.getAttribute("id")!=null){
        int id = Integer.parseInt((String)request.getAttribute("id")); 
        out.println(id);
    }else{
        String nom = (String)request.getParameter("nom");
        out.println(nom);
    }
    
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>template pour un form2</title>
	<link rel="stylesheet" href="">
</head>
<body>
	
	

</body>
</html>