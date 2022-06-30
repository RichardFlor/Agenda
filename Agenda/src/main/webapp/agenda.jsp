<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="models.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
    
    
%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/telefone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo Contato</a>
	<table id="tabela">
		<thead>
		
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i < lista.size(); i++) { %>
				<tr>
					<td><%=lista.get(i).getIdContato() %></td>
					<td><%=lista.get(i).getNome() %></td>
					<td><%=lista.get(i).getTelefone() %></td>
					<td><%=lista.get(i).getEmail() %></td>
					<td><a href="select?idContato=<%=lista.get(i).getIdContato()%>" class="Botao1">Editar</a></td>
				</tr>
			<%}%>
		</tbody>
	</table>
</body>
</html>