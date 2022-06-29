package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

	// CRUD CREATE
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

}
