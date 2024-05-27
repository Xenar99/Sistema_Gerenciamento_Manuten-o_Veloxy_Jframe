package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import dao.DAO;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeEquipamento;
	private JFormattedTextField txt_dataaquisicao;
	private JTextField valorequip;
	private JFormattedTextField txt_datavalidade;
	private JTextField descEquipamento;
	protected Object jcadastro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null,null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JCadastro(final Equipamento equipamentoSelecionado, final Database_Change database) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 657, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(33, 11, 570, 321);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Equipamento:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(51, 73, 126, 30);
		panel.add(lblNewLabel);

		nomeEquipamento = new JTextField();
		nomeEquipamento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		nomeEquipamento.setBounds(179, 76, 354, 30);
		panel.add(nomeEquipamento);
		nomeEquipamento.setColumns(10);
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDescrio.setBounds(51, 114, 126, 30);
		panel.add(lblDescrio);

		JLabel lblDataDaAquisio = new JLabel("Data da aquisição:");
		lblDataDaAquisio.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDataDaAquisio.setBounds(51, 155, 184, 30);
		panel.add(lblDataDaAquisio);

		JLabel lblValorr = new JLabel("Valor (R$):");
		lblValorr.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblValorr.setBounds(51, 196, 101, 30);
		panel.add(lblValorr);

		JLabel lblValidade = new JLabel("Validade:");
		lblValidade.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblValidade.setBounds(51, 237, 89, 30);
		panel.add(lblValidade);

		try {
			MaskFormatter mask1 = new MaskFormatter("##/##/####");

			txt_dataaquisicao = new JFormattedTextField(mask1);
			txt_dataaquisicao.setHorizontalAlignment(SwingConstants.CENTER);
			txt_dataaquisicao.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txt_dataaquisicao.setBounds(221, 158, 132, 30);
			panel.add(txt_dataaquisicao);

			txt_datavalidade = new JFormattedTextField(mask1);
			txt_datavalidade.setHorizontalAlignment(SwingConstants.CENTER);
			txt_datavalidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txt_datavalidade.setBounds(138, 237, 126, 30);
			panel.add(txt_datavalidade);

			NumberFormat doubleFormat = NumberFormat.getNumberInstance();
			doubleFormat.setMinimumFractionDigits(2);
			NumberFormatter formatter = new NumberFormatter(doubleFormat);
			formatter.setValueClass(Double.class);
			formatter.setAllowsInvalid(false);
			formatter.setCommitsOnValidEdit(true);

			valorequip = new JFormattedTextField(formatter);
			valorequip.setColumns(10);
			valorequip.setFont(new Font("Tahoma", Font.PLAIN, 17));
			valorequip.setBounds(153, 196, 157, 30);
			panel.add(valorequip);

			descEquipamento = new JTextField();
			descEquipamento.setFont(new Font("Tahoma", Font.PLAIN, 17));
			descEquipamento.setColumns(10);
			descEquipamento.setBounds(153, 114, 354, 30);
			panel.add(descEquipamento);

		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto!", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
		JButton button_Cadastro = new JButton(equipamentoSelecionado == null ? "Cadastrar" : "Alterar");
		button_Cadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String resultado1 = nomeEquipamento.getText();
				String resultado2 = descEquipamento.getText();
				String resultado3 = txt_dataaquisicao.getText();
				String resultado4 = valorequip != null ? valorequip.getText() : "";
				String resultado5 = txt_datavalidade.getText();

				if (resultado1.isEmpty() || resultado2.isEmpty() || resultado3.isEmpty() || resultado4.isEmpty()
						|| resultado5.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Um ou mais campos não estão preenchidos corretamente!",
							"Erro!", JOptionPane.ERROR_MESSAGE);
				}

				else {
					// String iD, String equipamento, String descricao, String dataAquisicao, String
					// valor,
					// String dataValidade)
					DAO dao = new DAO();
					Equipamento newequipamento = new Equipamento(null, resultado1, resultado2, resultado3, resultado4,
							resultado5);

					if (equipamentoSelecionado == null) {
						try {
							dao.cadastrarEquipamento(newequipamento);

						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							dao.alterarEquipamento(equipamentoSelecionado.getID(), newequipamento);
							AtualizarDados(database);

						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}

			}

		});

		button_Cadastro.setBounds(384, 205, 149, 76);
		panel.add(button_Cadastro);

		if (equipamentoSelecionado != null) {
			preencherCampos(equipamentoSelecionado);
		}

	}

	private void preencherCampos(Equipamento equipamentoSelecionado) {

		nomeEquipamento.setText(equipamentoSelecionado.getEquipamento());
		descEquipamento.setText(equipamentoSelecionado.getDescricao());
		txt_dataaquisicao.setText(equipamentoSelecionado.getDataAquisicao());
		valorequip.setText(equipamentoSelecionado.getValor());
		txt_datavalidade.setText(equipamentoSelecionado.getDataValidade());
	}

	void AtualizarDados(Database_Change database) {
		database.dispose();
		dispose();
		database = new Database_Change();
        database.setLocationRelativeTo(database);
        database.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        database.setVisible(true);
	}

}

