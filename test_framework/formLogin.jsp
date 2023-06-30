<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>view pour un form login</title>
	<link rel="stylesheet" href="">
</head>
<body>
	
	<form action="Emp-traitLogin.do" method="get">
		
		<select name="isAdmin">
			<option value="0">Client</option>
			<option value="1">Admin</option>
		</select>
		<input type="submit" value="Se connecter">
	</form>

</body>
</html>