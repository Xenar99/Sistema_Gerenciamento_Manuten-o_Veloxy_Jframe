package main;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("static-access")
public class Database_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Equipamento> equipamentos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database_View frame = new Database_View();
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
	public Database_View() {
		DAO dao = new DAO();
		try {
			equipamentos = dao.listarEquipamento();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        //equipamentos.add(new Equipamento ("ID", "EQUIPAMENTO", "DESCRICAO", "DATAAQUISICAO", "VALOR", "VALIDADE"));
        
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 1264, 656);
		contentPane.add(scrollPane);
		ModeloTabela modelotabela = new ModeloTabela(equipamentos);
		table = new JTable();
		table.setModel(modelotabela);

		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Banco de Dados equipamentos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(325, 11, 541, 19);
		contentPane.add(lblNewLabel);

	}
		

}
