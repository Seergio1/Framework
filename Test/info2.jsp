<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>
<% 
    try{
        if(request.getAttribute("id")!=null){
            int id = Integer.parseInt((String)request.getAttribute("id")); 
            out.println(id);
        }else{
            String nom = (String)request.getAttribute("nom");
            Double prix = Double.parseDouble((String)request.getAttribute("prix"));
            out.println(nom+","+prix);
        }
    }catch(Exception e){
        out.println(e.getMessage());
    }
    
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>view pour un info2</title>
	<link rel="stylesheet" href="">
</head>
<body>
	
	

</body>
</html>