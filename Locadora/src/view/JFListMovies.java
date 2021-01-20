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

import model.bean.Movie;
import model.dao.MovieDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFListMovies extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable JTMovies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFListMovies frame = new JFListMovies();
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
	public JFListMovies() {
		setTitle("Listar Filmes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Listagem de Filmes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 144, 23);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 597, 306);
		contentPane.add(scrollPane);
		
		JTMovies = new JTable();
		JTMovies.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Titulo", "Categoria", "Tempo"
			}
		));
		scrollPane.setViewportView(JTMovies);
		
		JButton btnCreate = new JButton("Cadastrar Filme");
		btnCreate.setBounds(20, 379, 134, 23);
		contentPane.add(btnCreate);
		
		JButton btnChange = new JButton("Alterar Filme");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = JTMovies.getSelectedRow();
				
				if(selectedRow != -1) {
					
					int id = (int) JTMovies.getValueAt(selectedRow, 0);
					
					JFUpdateMovie af = new JFUpdateMovie(id);
					
					af.setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um filme");
				}
				
				readJTable();
				
			}
		});
		btnChange.setBounds(163, 379, 115, 23);
		contentPane.add(btnChange);
		
		JButton btnRemove = new JButton("Excluir Filme");
		btnRemove.setBounds(288, 379, 107, 23);
		contentPane.add(btnRemove);
		
		readJTable();
		
	}
	
	public void readJTable() {
		DefaultTableModel model = (DefaultTableModel) JTMovies.getModel();
		model.setNumRows(0);
		MovieDAO dao = new MovieDAO();
		
		for(Movie m : dao.read()) {
			model.addRow(new Object[] {
					m.getId(),
					m.getTitle(),
					m.getCategory(),
					m.getLenght()
			});
		}
		
	}
	
}
