package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Customer> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Customer> customers = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM customer");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Customer m = new Customer();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setCpf(rs.getString("cpf"));
				
				customers.add(m);
				
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar as informações: " + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return customers;
	
	}
	
	public Customer read(int custumerId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer m = new Customer();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM customer WHERE id=?");
			stmt.setInt(1 , custumerId);

			rs = stmt.executeQuery();
			
			if(rs != null && rs.next()){
			
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setCpf(rs.getString("cpf"));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar as informações: " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return m;
		
		
	}
	
	public void update(Customer c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE customer SET name=?, phone=?, email=?, cpf=? WHERE id=?");
			
			stmt.setString(1, c.getName());
			stmt.setString(2,  c.getPhone());
			stmt.setString(3,  c.getEmail());
			stmt.setString(4,  c.getCpf());
			stmt.setInt(5, c.getId());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Mudanças salvas com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + e);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}

	public void remove(int customerId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM customer WHERE id=?");
			
			stmt.setInt(1, customerId);
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + e);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}