
<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>view pour upload</title>
	<link rel="stylesheet" href="">
</head>
<body>
	
	<form action="Telechargement-uploadFile.do" method="post" enctype="multipart/form-data">
        <input type="file"  name="file">
        <input type="submit" value="ok">
    </form>

</body>
</html>