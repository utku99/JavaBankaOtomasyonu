package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Interface.Ihesap;

public class Hesap implements Ihesap {

	private int hesap_id;
	String bakiye, iban, type;
	DBConnection conn = new DBConnection();

	public Hesap(int hesap_id, String bakiye, String iban, String type) {
		super();
		this.hesap_id = hesap_id;
		this.bakiye = bakiye;
		this.iban = iban;
		this.type = type;
	}

	public Hesap() {

	}

	@Override
	public ArrayList<Hesap> getList() throws SQLException, ClassNotFoundException {
		ArrayList<Hesap> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		Hesap obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM hesap");
			while (rs.next()) {
				obj = new Hesap(rs.getInt("hesap_id"), rs.getString("bakiye"), rs.getString("iban"),
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
	public boolean add(int hesap_id, String iban, String type) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO hesap" + "(hesap_id,iban,type) VALUES" + "(?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, hesap_id);
			preparedStatement.setString(2, iban);
			preparedStatement.setString(3, type);
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
	public boolean delete(String iban) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM hesap WHERE iban=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, iban);
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

	public int getHesap_id() {
		return hesap_id;
	}

	public void setHesap_id(int hesap_id) {
		this.hesap_id = hesap_id;
	}

	public String getBakiye() {
		return bakiye;
	}

	public void setBakiye(String bakiye) {
		this.bakiye = bakiye;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
	

}
