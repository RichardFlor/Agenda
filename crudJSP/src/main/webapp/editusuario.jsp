<%@page import="com.crudJSP.dao.UsuarioDao"%>
<jsp:useBean id="usuario" class="com.crudJSP.bean.Usuario"></jsp:useBean>
<jsp:setProperty property="*" name="usuario"/>

<%
	int i = UsuarioDao.updateUsuario(usuario);
	response.sendRedirect("viewusuarios.jsp");
%>