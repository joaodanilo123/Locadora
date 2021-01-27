package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.bean.Customer;

import model.dao.CustomerDAO;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionEvent;

public class JFListCustomers extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jtCustomers;

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
		addWindowFocusListener((WindowFocusListener) new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				readJTable();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		setTitle("Listar Filmes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		jtCustomers = new JTable();
		jtCustomers.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Email", "Telefone", "CPF"
			}
		));
		scrollPane.setViewportView(jtCustomers);
		
		JButton btnCreate = new JButton("Cadastrar Cliente");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFRegisterCustomer rg = new JFRegisterCustomer();
				rg.setVisible(true);
				
				readJTable();
			}
		});
		btnCreate.setBounds(20, 379, 134, 23);
		contentPane.add(btnCreate);
		
		JButton btnChange = new JButton("Alterar Cliente");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jtCustomers.getSelectedRow();
				
				if(selectedRow != -1) {
					
					int id = (int) jtCustomers.getValueAt(selectedRow, 0);
					
					JFUpdateCustomer af = new JFUpdateCustomer(id);
					
					af.setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um cliente");
				}
				
				readJTable();	
				
			}
		});
		btnChange.setBounds(163, 379, 115, 23);
		contentPane.add(btnChange);
		
		JButton btnRemove = new JButton("Excluir Cliente");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jtCustomers.getSelectedRow();
				
				if(selectedRow != -1) {
					
					int id = (int) jtCustomers.getValueAt(selectedRow, 0);
					CustomerDAO dao = new CustomerDAO();
					dao.remove(id);
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um cliente");
				}
				
				readJTable();	
				
			}
		});
		btnRemove.setBounds(288, 379, 144, 23);
		contentPane.add(btnRemove);
		
		readJTable();
		
	}
	
	public void readJTable() {
		DefaultTableModel model = (DefaultTableModel) jtCustomers.getModel();
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
