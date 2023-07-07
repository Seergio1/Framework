<%@ page language="java" contentType="text/html"
	import="java.util.*"
	pageEncoding="UTF-8"
%>
<% 
	Vector<String> allData = (Vector<String>)request.getAttribute("allData");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>view pour un info</title>
	<link rel="stylesheet" href="">
</head>
<body>
	
	<div class="box_info" style="width: 200px;height: 250px;display: flex;flex-direction: column; position: absolute;left: 50%;top: 50%;transform: translate(-50%,-50%);border-radius: 10px; border: 1px solid black;justify-content: center;align-items: center;">
		<div class="item_info" style="display: flex;width: 100%;">
			<div class="label" style="width:50%;display: flex;justify-content: left;align-items: center;">Nom</div>
			<div class="data" style="width:50%;display: flex;justify-content: left;align-items: center;"><%out.println(allData.get(0));%></div>
		</div>
		<div class="item_info" style="display: flex;width: 100%;">
			<div class="label" style="width:50%;display: flex;justify-content: left;align-items: center;">Matricule</div>
			<div class="data" style="width:50%;display: flex;justify-content: left;align-items: center;"><%out.println(allData.get(1));%></div>
		</div>
		<div class="item_info" style="display: flex;width: 100%;">
			<div class="label" style="width:50%;display: flex;justify-content: left;align-items: center;">N°</div>
			<div class="data" style="width:50%;display: flex;justify-content: left;align-items: center;"><%out.println(allData.get(2));%></div>
		</div>
	</div>

</body>
</html>