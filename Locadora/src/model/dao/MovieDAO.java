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
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return movies;
	
	}
	
	public Movie read(int movieId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Movie m = new Movie();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM movie WHERE id=? LIMIT 1");
			stmt.setInt(1, movieId);
			rs = stmt.executeQuery();
			
			if(rs != null && rs.next()) {
				m.setId(rs.getInt("id"));
				m.setTitle(rs.getString("title"));
				m.setLenght(rs.getInt("length"));
				m.setDubbed(rs.getBoolean("dubbed"));
				m.setCategory(rs.getString("category"));
				m.setImage3d(rs.getBoolean("image3d"));
				m.setSynopsis(rs.getString("synopsis"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return m;
	}
	
	public void update(Movie m) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE movie SET title=?, length=?, dubbed=?, category=?, image3d=?, synopsis=? WHERE id=?");
			
			stmt.setString(1, m.getTitle());
			stmt.setString(6, m.getSynopsis());
			stmt.setInt(2, m.getLenght());
			stmt.setBoolean(5, m.isImage3d());
			stmt.setBoolean(3, m.isDubbed());
			stmt.setString(4, m.getCategory());
			stmt.setInt(7, m.getId());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Mudanças salvas com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + e);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}
	
	public void remove(int movieId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM movie WHERE id=?");
			
			stmt.setInt(1, movieId);
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Filme excluído com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + e);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}
	
}
