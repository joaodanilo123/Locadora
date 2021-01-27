package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.bean.Movie;
import model.dao.MovieDAO;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFUpdateMovie extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtCategory;
	
	private static int id;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFUpdateMovie frame = new JFUpdateMovie(id);
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
	public JFUpdateMovie(int id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 667, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Alterar Filme");
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		MovieDAO mdao = new MovieDAO();
		Movie m = mdao.read(id);
		
		JLabel lblId = new JLabel("lbl_id");
		
		JLabel lblNewLabel = new JLabel("Titulo");
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Sinopse");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblNewLabel_2 = new JLabel("Categoria");
		
		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tempo em minutos:");
		
		JSpinner spinnerLength = new JSpinner();
		
		JLabel lblNewLabel_4 = new JLabel("Imagem:");
		
		JRadioButton rdbtn3D = new JRadioButton("3D");
		
		JRadioButton rdbtn2D = new JRadioButton("2D");
		
		ButtonGroup image = new ButtonGroup();
		image.add(rdbtn3D);
		image.add(rdbtn2D);
		
		JLabel lblNewLabel_5 = new JLabel("Audio:");
		
		JRadioButton rdbtnDub = new JRadioButton("Dublado");
		
		JRadioButton rdbtnSub = new JRadioButton("Legendado");
		
		ButtonGroup audio = new ButtonGroup();
		audio.add(rdbtnDub);
		audio.add(rdbtnSub);
		
		JTextArea txtSynopsis = new JTextArea();
		txtSynopsis.setLineWrap(true);
		
		
		lblId.setText(String.valueOf(m.getId()));
		txtTitle.setText(m.getTitle());
		txtSynopsis.setText(m.getSynopsis());
		txtCategory.setText(m.getCategory());
		spinnerLength.setValue(m.getLenght());
		
		if(m.isImage3d()) {
			rdbtn3D.setSelected(true);
		} else {
			rdbtn2D.setSelected(true);
		}
		
		if(m.isDubbed()) {
			rdbtnDub.setSelected(true);
		} else {
			rdbtnSub.setSelected(true);
		}
		
		JButton btnUpdate = new JButton("Alterar");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movie m = new Movie();
				MovieDAO dao = new MovieDAO();
				
				m.setId(Integer.parseInt(lblId.getText()));
				m.setTitle(txtTitle.getText());
				m.setSynopsis(txtSynopsis.getText());
				m.setCategory(txtCategory.getText());
				m.setLenght(Integer.parseInt(spinnerLength.getValue().toString()));
				if(rdbtn3D.isSelected()) {
					m.setImage3d(true);
				} else {
					m.setImage3d(false);
				}
				
				if(rdbtnDub.isSelected()) {
					m.setDubbed(true);
				} else {
					m.setDubbed(false);
				}
				
				dao.update(m);
				dispose();
			}
		});
		
		JButton btnClear = new JButton("Limpar");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTitle.setText("");
				txtSynopsis.setText("");
				txtCategory.setText("");
				spinnerLength.setValue(0);
			}
		});
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("ID:");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCategory, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
						.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(title)
							.addGap(48)
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblId))
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(scrollPane)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerLength, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(65)
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtn3D)
									.addGap(81)
									.addComponent(lblNewLabel_5))
								.addComponent(rdbtn2D))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnSub)
								.addComponent(rdbtnDub)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnUpdate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClear)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(title)
						.addComponent(lblNewLabel_6)
						.addComponent(lblId))
					.addGap(30)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(spinnerLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtn3D)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_5)
						.addComponent(rdbtnDub))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnSub)
						.addComponent(rdbtn2D))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate)
						.addComponent(btnClear)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(txtSynopsis);
		contentPane.setLayout(gl_contentPane);
	}
}
