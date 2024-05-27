package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.Conexao_Bd;
import main.Equipamento;

public class DAO {
	private static PreparedStatement preparedStatment = null;
	private static ResultSet resultSet = null;

	private static String CADASTRAR_EQUIPAMENTO = "INSERT INTO EQUIPAMENTO "
			+ "(ID, EQUIPAMENTO, DESCRICAO, DATAAQUISICAO, VALOR, DATAVALIDADE)" + "VALUES(NULL, ?,?,?,?,?) ";

	private static String CONSULTAR_EQUIPAMENTO = "SELECT * FROM EQUIPAMENTO " + " WHERE ID = ? ";

	private static String ALTERAR_EQUIPAMENTO = "UPDATE EQUIPAMENTO SET "
			+ " EQUIPAMENTO = ?, DESCRICAO = ?, DATAAQUISICAO = ?,  VALOR = ?, DATAVALIDADE = ?" + " WHERE ID = ? ";

	private static String EXCLUIR_EQUIPAMENTO = "DELETE FROM EQUIPAMENTO " + " WHERE ID = ? ";

	private static String LISTAR_EQUIPAMENTO = "SELECT * FROM EQUIPAMENTO " + " WHERE 1=1 ";

	public DAO() {

	}

	public void cadastrarEquipamento(Equipamento nomeEquipamento) throws ClassNotFoundException, SQLException {

		Connection connection = Conexao_Bd.getInstancia().abrirConexao();

		String query = CADASTRAR_EQUIPAMENTO;
		try {

			preparedStatment = connection.prepareStatement(query);

			int i = 1;

			//preparedStatment.setString(i++, nomeEquipamento.getID());
			preparedStatment.setString(i++, nomeEquipamento.getEquipamento());
			preparedStatment.setString(i++, nomeEquipamento.getDescricao());
			preparedStatment.setString(i++, nomeEquipamento.getDataAquisicao());
			preparedStatment.setString(i++, nomeEquipamento.getValor());
			preparedStatment.setString(i++, nomeEquipamento.getDataValidade());

			preparedStatment.execute();
			connection.commit();

			JOptionPane.showMessageDialog(null, "Equipamento cadastrado com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

	}

	public Equipamento consultarEquipamento(String iD) throws Exception {

		Connection connection = Conexao_Bd.getInstancia().abrirConexao();
		Equipamento equipamento = null;
		String query = CONSULTAR_EQUIPAMENTO;

		try {

			preparedStatment = connection.prepareStatement(query);

			int i = 1;

			preparedStatment.setString(i++, iD);
			resultSet = preparedStatment.executeQuery();

			while (resultSet.next()) {
				// public Equipamento(String iD, String equipamento, String descricao, String
				// dataAquisicao, String valor,
				// String dataValidade)
				equipamento = new Equipamento(resultSet.getString("ID"), resultSet.getString("equipamento"),
						resultSet.getString("descricao"), resultSet.getString("dataAquisicao"),
						resultSet.getString("valor"), resultSet.getString("dataValidade"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if (equipamento == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar este equipamento!", null,
					JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não foi possível encontrar este equipamento!");
		}

		return equipamento;
	}
	
	public void alterarEquipamento(String id, Equipamento nomeEquipamento) throws ClassNotFoundException, SQLException {

		Connection connection = Conexao_Bd.getInstancia().abrirConexao();

		String query = ALTERAR_EQUIPAMENTO;
		try {

			preparedStatment = connection.prepareStatement(query);

			int i = 1;

			preparedStatment.setString(i++, nomeEquipamento.getEquipamento());
			preparedStatment.setString(i++, nomeEquipamento.getDescricao());
			preparedStatment.setString(i++, nomeEquipamento.getDataAquisicao());
			preparedStatment.setString(i++, nomeEquipamento.getValor());
			preparedStatment.setString(i++, nomeEquipamento.getDataValidade());
			preparedStatment.setString(i++, id);

			preparedStatment.execute();
			connection.commit();

			JOptionPane.showMessageDialog(null, "Equipamento alterado com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
	}

	public void excluirEquipamento(String id) throws ClassNotFoundException, SQLException {
		
		Connection connection = Conexao_Bd.getInstancia().abrirConexao();

		String query = EXCLUIR_EQUIPAMENTO;
		
		try {
		
			preparedStatment = connection.prepareStatement(query);

			int i = 1;

			preparedStatment.setString(i++, id);

			preparedStatment.execute();
			connection.commit();

			JOptionPane.showMessageDialog(null, "Equipamento excluído com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fecharConexao();
		}

	}


	public ArrayList<Equipamento> listarEquipamento() throws Exception {

		Connection connection = Conexao_Bd.getInstancia().abrirConexao();
		ArrayList<Equipamento> equipamento = new ArrayList<Equipamento>();
		String query = LISTAR_EQUIPAMENTO;

		try {

			preparedStatment = connection.prepareStatement(query);

			resultSet = preparedStatment.executeQuery();

			while (resultSet.next()) {
				// public Equipamento(String iD, String equipamento, String descricao, String
				// dataAquisicao, String valor,
				// String dataValidade)
				equipamento.add(new Equipamento(resultSet.getString("ID"), resultSet.getString("equipamento"),
						resultSet.getString("descricao"), resultSet.getString("dataAquisicao"),
						resultSet.getString("valor"), resultSet.getString("dataValidade")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if (equipamento.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não há equipamentos cadastrados!", null, JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há equipamentos cadastrados!");
		}

		return equipamento;
	}

	private void fecharConexao() {
		// TODO Auto-generated method stub

		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatment != null) {
				preparedStatment.close();
			}

			Conexao_Bd.getInstancia().fecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
