package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import models.DAO;
import models.JavaBeans;

@WebServlet(urlPatterns = { "/Controllers", "/main", "/insert" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			contatos(request, response);

		} else if (action.equals("/insert")) {
			novoContato(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}

	// Listar Contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		
		//Teste de recebimento da lista
			//		for (int i = 0; i < lista.size(); i++) {
			//			System.out.println(lista.get(i).getIdContato());
			//			System.out.println(lista.get(i).getNome());
			//			System.out.println(lista.get(i).getTelefone());
			//			System.out.println(lista.get(i).getEmail());
			//		}
		
	}

	//Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//teste de recebimento dos dados do Formulario
		//System.out.println(request.getParameter("nome"));
		//System.out.println(request.getParameter("telefone"));
		//System.out.println(request.getParameter("email"));
		
		//Setar as variaveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		
		//Invocar o método inserirContato passando o objeto contato
		dao.inserirContato(contato);
		
		//Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}
}
