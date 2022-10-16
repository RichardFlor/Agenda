package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DAO;
import models.JavaBeans;

@WebServlet(urlPatterns = { "/Controllers", "/main", "/insert", "/select", "/update", "/delete" })
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
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar Contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();

		// Teste de recebimento da lista
		// for (int i = 0; i < lista.size(); i++) {
		// System.out.println(lista.get(i).getIdContato());
		// System.out.println(lista.get(i).getNome());
		// System.out.println(lista.get(i).getTelefone());
		// System.out.println(lista.get(i).getEmail());
		// }

		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rs = request.getRequestDispatcher("agenda.jsp");
		rs.forward(request, response);

	}

	// Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// teste de recebimento dos dados do Formulario
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("telefone"));
		// System.out.println(request.getParameter("email"));

		// Setar as variaveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));

		// Invocar o método inserirContato passando o objeto contato
		dao.inserirContato(contato);

		// Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	// Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será editado
		String idContato = request.getParameter("idContato");
		// System.out.println(idContato); // verificar o recebimento do id do contato q
		// sera editado

		// Setar a variavel JavaBeans
		contato.setIdContato(idContato);

		// Executar o método selecionarContato (classe DAO)
		dao.selecionarContato(contato);

		// Teste de recebimento
		// System.out.println(contato.getIdContato());
		// System.out.println(contato.getNome());
		// System.out.println(contato.getTelefone());
		// System.out.println(contato.getEmail());

		// Setar os atributos do formulario com o conteudo JavaBeans
		request.setAttribute("idContato", contato.getIdContato());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("telefone", contato.getTelefone());
		request.setAttribute("email", contato.getEmail());

		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
		//
	}

	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Teste de recebimento de dados do formulario
		// System.out.println(request.getParameter("idContato"));
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("telefone"));
		// System.out.println(request.getParameter("email"));

		// Setar as variaveis JavaBeans
		contato.setIdContato(request.getParameter("idContato"));
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));

		// Executar o metodo alterarContato
		dao.alterarContato(contato);

		// Redirecionar para o documento agenda.jsp (atualizando as alteraçoes)
		response.sendRedirect("main");

	}

	// Remover um contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebimento do id do contato que será excluido (validador.js)
		String idContato = request.getParameter("idContato");
		// System.out.println(idContato);

		// Setar a variavel idContato JavaBeans
		contato.setIdContato(idContato);

		// Executar o metodo deletarCOntato (DAO) passando o objeto contato
		dao.deletarContato(contato);

		// Redirecionar para o documento agenda.jsp (atualizando as alteraçoes)
		response.sendRedirect("main");
	}
}

//teste  

