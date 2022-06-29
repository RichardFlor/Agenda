package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	/** Modulo de conexao **/
	//Pârametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/agenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "bcd127";
	
	//Método de conexão
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
	
}
