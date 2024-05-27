package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main_Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Window frame = new Main_Window();
					frame.setLocationRelativeTo(null);
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
	public Main_Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 676, 419);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Consultar Banco de dados");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database_View databaseview = new Database_View();
				databaseview.setLocationRelativeTo(databaseview);
				databaseview.setVisible(true);
			}
		});
		btnNewButton.setBounds(244, 309, 191, 77);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Olá, seja bem vindo (a)");
		lblNewLabel.setBounds(236, 11, 199, 21);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel);
		
		JButton btnCadastrarEquipamento = new JButton("Cadastrar equipamento");
		btnCadastrarEquipamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastro jcadastro = new JCadastro(null, null);
				jcadastro.setLocationRelativeTo(jcadastro);
				jcadastro.setVisible(true);
			}
		});
		btnCadastrarEquipamento.setBounds(10, 309, 191, 77);
		panel.add(btnCadastrarEquipamento);
		
		JButton btnNewButton_2 = new JButton("Alterar cadastros");
		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Database_Change databasechange = new Database_Change();
				databasechange.setLocationRelativeTo(databasechange);
				databasechange.setVisible(true);
			}
			
		});
	
		btnNewButton_2.addActionListener(null);
		btnNewButton_2.setBounds(475, 309, 191, 77);
		panel.add(btnNewButton_2);
	}
}
