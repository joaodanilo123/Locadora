package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DBconnection.ConnectionFactory;
import model.bean.Movie;

public class MovieDAO {
	public void create(Movie m) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO movie (title, synopsis, length, image3d, dubbed, category) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, m.getTitle());
			stmt.setString(2, m.getSynopsis());
			stmt.setInt(3, m.getLenght());
			stmt.setBoolean(4, m.isImage3d());
			stmt.setBoolean(5, m.isDubbed());
			stmt.setString(6, m.getCategory());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Filme salvo com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Cadastrar o Filme: " + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
