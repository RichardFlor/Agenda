<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edi��o de Usu�rio</title>
</head>
<body>

	<%@page import="com.crudJSP.bean.Usuario, com.crudJSP.dao.UsuarioDao"%>

	<%
	String id = request.getParameter("id");
	Usuario usuario = UsuarioDao.getRegistroById(Integer.parseInt(id));
	%>
	
	<h1>Edi��o de usu�rio</h1>
	
	<form action="" method="post">
		<input type="text" name="id" value="<%=usuario.getId()%>"/>
		<table>
			<tr>
				
			</tr>
		</table>
	
	</form>

</body>
</html>