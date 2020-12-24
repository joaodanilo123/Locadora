package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DBconnection.ConnectionFactory;
import model.bean.Customer;

public class CustomerDAO {
	public void create(Customer c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO customer (name, cpf, email, phone) VALUES (?, ?, ?, ?)";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getCpf());
			stmt.setString(3, c.getEmail());
			stmt.setString(4, c.getPhone());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar o cliente: " + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}