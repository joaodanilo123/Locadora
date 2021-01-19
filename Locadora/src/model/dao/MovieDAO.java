package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Movie> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Movie> movies = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM movie");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Movie m = new Movie();
				m.setId(rs.getInt("id"));
				m.setTitle(rs.getString("title"));
				m.setLenght(rs.getInt("length"));
				m.setDubbed(rs.getBoolean("dubbed"));
				m.setCategory(rs.getString("category"));
				m.setImage3d(rs.getBoolean("image3d"));
				m.setSynopsis(rs.getString("synopsis"));
				
				movies.add(m);
				
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar as informações: " + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return movies;
	
	}
}
