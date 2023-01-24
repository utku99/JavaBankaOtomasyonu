package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Interface.Iyönetici;

public class Yönetici extends User implements Iyönetici {

	public Yönetici(int user_id, String þifre, String isim, String type) {
		super(user_id, þifre, isim, type);
		// TODO Auto-generated constructor stub
	}

	public Yönetici() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Yönetici> getList() throws SQLException, ClassNotFoundException {
		ArrayList<Yönetici> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		Yönetici obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kullanýcý WHERE type = 'personel'");
			while (rs.next()) {
				obj = new Yönetici(rs.getInt("user_id"), rs.getString("þifre"), rs.getString("isim"),
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
	public boolean add(String þifre, String isim) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO kullanýcý" + "(þifre,isim,type) VALUES" + "(?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, þifre);
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
	public boolean update(int user_id, String þifre, String isim) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE kullanýcý SET þifre =?,isim=? WHERE user_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, þifre);
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
		String query = "DELETE FROM kullanýcý WHERE user_id=?";
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
