<%@ page import="com.crudJSP.dao.UsuarioDao"%>

<jsp:useBean id="u" class="com.crudJSP.bean.Usuario"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
	UsuarioDao.deletarUsuario(u);
	response.sendRedirect("viewusuarios.jsp");
%>