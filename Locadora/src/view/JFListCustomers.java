package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.bean.Customer;

import model.dao.CustomerDAO;


import javax.swing.JButton;

public class JFListCustomers extends JFrame {

	private JPanel contentPane;
	private JTable JTMovies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFListCustomers frame = new JFListCustomers();
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
	public JFListCustomers() {
		setTitle("Listar Filmes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Listagem de Clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 144, 23);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 597, 306);
		contentPane.add(scrollPane);
		
		JTMovies = new JTable();
		JTMovies.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Email", "Telefone", "CPF"
			}
		));
		scrollPane.setViewportView(JTMovies);
		
		JButton btnCreate = new JButton("Cadastrar Cliente");
		btnCreate.setBounds(20, 379, 134, 23);
		contentPane.add(btnCreate);
		
		JButton btnChange = new JButton("Alterar Cliente");
		btnChange.setBounds(163, 379, 115, 23);
		contentPane.add(btnChange);
		
		JButton btnRemove = new JButton("Excluir Cliente");
		btnRemove.setBounds(288, 379, 144, 23);
		contentPane.add(btnRemove);
		
		readJTable();
		
	}
	
	public void readJTable() {
		DefaultTableModel model = (DefaultTableModel) JTMovies.getModel();
		model.setNumRows(0);
		CustomerDAO dao = new CustomerDAO();
		
		for(Customer c : dao.read()) {
			model.addRow(new Object[] {
					c.getId(),
					c.getName(),
					c.getEmail(),
					c.getPhone(),
					c.getCpf()
			});
		}
		
	}
	
}
