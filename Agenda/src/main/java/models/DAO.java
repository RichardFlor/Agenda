package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Modulo de conexao **/
	// Pârametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/agenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "bcd127";

	// Método de conexão
	private Connection conectar() {
		Connection conexao = null;
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	 /**----------CRUD CREATE---------**/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into tblContatos(nome, telefone, email) values (?,?,?)";
		try {
			// Abrir a conexão com o Banco de dados
			Connection conexao = conectar();

			// Preparar a query para execução no banco de dados
			PreparedStatement pst = conexao.prepareStatement(create);

			// Substituir os parametros (?) pelo conteudo das variaveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());

			// Executar a query
			pst.executeUpdate();

			// Encerrar a conexão com o banco de dados
			conexao.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	 /**----------CRUD READ----------**/
	public ArrayList<JavaBeans> listarContatos() {
		// Criando objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "select * from tblContatos order by nome";
		try {
			// Abrir a conexão com o Banco de dados
			Connection conexao = conectar();

			// Preparar a query para execução no banco de dados
			PreparedStatement pst = conexao.prepareStatement(read);

			// objeto para executar a query
			ResultSet rs = pst.executeQuery();

			// Exibir resultado usando laço de repetção enquanto houver contatos
			while (rs.next()) {
				// Variaveis de apoio que recebem os dados do banco
				String idContato = rs.getString(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);

				// Populando o ArrayList
				contatos.add(new JavaBeans(idContato, nome, telefone, email));
			}
			conexao.close();
			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**----------CRUD UPADATE----------**/
	//Selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from tblContatos where idContato = ?";
		try {
			//Iniciando a conexão com o banco de daods
			Connection conexao = conectar();
			
			//Preparar a query para ser executada no banco de dados 
			PreparedStatement pst = conexao.prepareStatement(read2);
			
			//Substituição do "?" pelo id do contato
			pst.setString(1, contato.getIdContato());
			
			//Trazer as informações do banco deste contato para exibir no formulário de edição
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				
				//Setar as variaveis JavaBeans
				contato.setIdContato(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}

}
