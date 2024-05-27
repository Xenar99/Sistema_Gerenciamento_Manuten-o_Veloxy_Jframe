package dao;

import java.sql.*;

import main.Conexao_Bd;

public class BD {

	private static Connection connection = null;
		
	public static void main(String[] args) {
		 try {
			connection = Conexao_Bd.getInstancia().abrirConexao();
			System.out.println("Base criada com sucesso!");
			Conexao_Bd.getInstancia().fecharConexao();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
}
