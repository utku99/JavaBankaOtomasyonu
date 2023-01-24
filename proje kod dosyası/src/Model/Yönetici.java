package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Interface.Iy�netici;

public class Y�netici extends User implements Iy�netici {

	public Y�netici(int user_id, String �ifre, String isim, String type) {
		super(user_id, �ifre, isim, type);
		// TODO Auto-generated constructor stub
	}

	public Y�netici() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Y�netici> getList() throws SQLException, ClassNotFoundException {
		ArrayList<Y�netici> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		Y�netici obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kullan�c� WHERE type = 'personel'");
			while (rs.next()) {
				obj = new Y�netici(rs.getInt("user_id"), rs.getString("�ifre"), rs.getString("isim"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}

	@Override
	public boolean add(String �ifre, String isim) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO kullan�c�" + "(�ifre,isim,type) VALUES" + "(?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, �ifre);
			preparedStatement.setString(2, isim);
			preparedStatement.setString(3, "personel");
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	@Override
	public boolean update(int user_id, String �ifre, String isim) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE kullan�c� SET �ifre =?,isim=? WHERE user_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, �ifre);
			preparedStatement.setString(2, isim);
			preparedStatement.setInt(3, user_id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(int user_id) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM kullan�c� WHERE user_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}


}
