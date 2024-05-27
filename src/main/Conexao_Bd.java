package main;

import java.sql.*;

public class Conexao_Bd {

	private static Conexao_Bd instancia;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/bdequipamentos";
	private static Connection conexao;

    private Conexao_Bd(){
    	
    }
   
  
	public static Conexao_Bd getInstancia() {
	   if (instancia == null) {
		   instancia = new Conexao_Bd();
	}
	   return instancia;   
}
	
	public Connection abrirConexao() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		conexao = DriverManager.getConnection (BD);
		try {
			conexao.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println ("Erro ao conectar com o banco de dados:"+e.getMessage());
		}
		return conexao;
	}
	
	public void fecharConexao() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			System.out.println ("Erro ao fechar o banco de dados:"+e.getMessage());
		} finally {
			conexao = null;
		}
	}
}
