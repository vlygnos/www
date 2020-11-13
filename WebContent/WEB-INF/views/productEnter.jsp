<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add product</title>
</head>
<body>
	<div align="center">
	
		<h1>ADD THE PRODUCT</h1>
		<form action="<%=request.getContextPath() %>/ProductServlet" method="post">
			<table style="with:80%">
				<tr>
				<td>Name</td>
				<td><input type="text" name="name"/></td>
				</tr>
				<tr>
				<td>Barcode</td>
				<td><input type="text" name="barcode"/></td>
				</tr>
				<tr>
				<td>Colour</td>
				<td><input type="text" name="colour"/></td>
				</tr>
				<tr>
				<td>Description</td>
				<td><input type="text" name="description"/></td>
				</tr>
			</table>
			<input type="submit" value="Submit"/>
		</form>
	</div>
</body>
</html>