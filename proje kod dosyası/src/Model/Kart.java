package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Interface.Ikart;

public class Kart implements Ikart {

	private int kart_id;
	String kart_numarasý, þifre, borç, type;
	DBConnection conn = new DBConnection();

	public Kart(int kart_id, String kart_numarasý, String þifre, String borç, String type) {
		super();
		this.kart_id = kart_id;
		this.kart_numarasý = kart_numarasý;
		this.þifre = þifre;
		this.borç = borç;
		this.type = type;
	}

	public Kart() {

	}

	@Override
	public ArrayList<Kart> getList() throws SQLException, ClassNotFoundException {
		ArrayList<Kart> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		Kart obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kart");
			while (rs.next()) {
				obj = new Kart(rs.getInt("kart_id"), rs.getString("kart_numarasý"), rs.getString("þifre"),
						rs.getString("borç"), rs.getString("type"));
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
	public boolean add(int kart_id, String kart_numarasý, String þifre, String type) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO kart (kart_id,kart_numarasý,þifre,type) VALUES (?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, kart_id);
			preparedStatement.setString(2, kart_numarasý);
			preparedStatement.setString(3, þifre);
			preparedStatement.setString(4, type);
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
	public boolean update(String kart_numarasý, String þifre) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement("UPDATE kart SET þifre =? WHERE kart_numarasý=?");
			preparedStatement.setString(1, þifre);
			preparedStatement.setString(2, kart_numarasý);
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
	public boolean delete(String kart_numarasý) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM kart WHERE kart_numarasý=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, kart_numarasý);
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

	public int getKart_id() {
		return kart_id;
	}

	public void setKart_id(int kart_id) {
		this.kart_id = kart_id;
	}

	public String getKart_numarasý() {
		return kart_numarasý;
	}

	public void setKart_numarasý(String kart_numarasý) {
		this.kart_numarasý = kart_numarasý;
	}

	public String getÞifre() {
		return þifre;
	}

	public void setÞifre(String þifre) {
		this.þifre = þifre;
	}

	public String getBorç() {
		return borç;
	}

	public void setBorç(String borç) {
		this.borç = borç;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
