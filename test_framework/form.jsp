<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>view pour un form</title>
	<link rel="stylesheet" href="">
</head>
<body>
	
	<form action="Emp-save.do" method="get">
		<input type="text" name="nom" placeholder="nom">
		<input type="text" name="prix" placeholder="prix">
		<input type="date" name="dateNaissance">
		<input type="submit" value="valider">
	</form>
	<a href="Emp-saveByLink.do?id=1">mot de passe</a>

</body>
</html>