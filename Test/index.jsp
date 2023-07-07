<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>
<% 

    if(request.getAttribute("erreurAuth")!=null){
        String erreurAuth = (String)request.getAttribute("erreurAuth");
        out.println(erreurAuth);
    }
    if(request.getAttribute("nomProfil")!=null){
        String nomProfil = (String)request.getAttribute("nomProfil");
        out.println(nomProfil);
    }
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>index</title>
</head>
<body>
    
</body>
</html>