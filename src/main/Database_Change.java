package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import dao.DAO;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("static-access")
public class Database_Change extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final String ReloadTela = null;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Equipamento> equipamentos;
	private Database_Change database;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database_Change frame = new Database_Change();
					frame.setLocationRelativeTo(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Database_Change() {
		
		this.database = this;
		final DAO dao = new DAO();
		try {
			equipamentos = dao.listarEquipamento();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// equipamentos.add(new Equipamento ("ID", "EQUIPAMENTO", "DESCRICAO",
		// "DATAAQUISICAO", "VALOR", "VALIDADE"));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 726);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 1264, 631);
		contentPane.add(scrollPane);
		final ModeloTabela modelotabela = new ModeloTabela(equipamentos);
		table = new JTable();
		table.setModel(modelotabela);

		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Banco de Dados equipamentos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 541, 19);
		contentPane.add(lblNewLabel);

		
		JButton Botao_alterar = new JButton("Alterar");
		Botao_alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
{
				try {
					
					Equipamento equipamentoSelecionado = dao
							.consultarEquipamento(modelotabela.getValueAt(table.getSelectedRow(), 0).toString());
					JCadastro jcadastro = new JCadastro(equipamentoSelecionado, database);
					jcadastro.setLocationRelativeTo(jcadastro);
					jcadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					jcadastro.setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Nenhum item selecionado para alterar");
				}

			}

		});

		Botao_alterar.setBounds(1086, 12, 89, 23);
		contentPane.add(Botao_alterar);

		JButton Botao_excluir = new JButton("Excluir");
		Botao_excluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		JCadastro jcadastro = new JCadastro(null, database);
                try {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        String equipamentoId = modelotabela.getValueAt(selectedRow, 0).toString();
                        dao.excluirEquipamento(equipamentoId);
                        ((ModeloTabela) modelotabela).removeRow(selectedRow);
                        jcadastro.AtualizarDados(database);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum item selecionado para excluir");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
		
		Botao_excluir.setBounds(1185, 12, 89, 23);
		contentPane.add(Botao_excluir);
		
	}

}
