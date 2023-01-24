package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Interface.Ifatura;

public class Fatura implements Ifatura {

	private int fatura_id;
	String elektrik, su, doðalgaz;
	DBConnection conn = new DBConnection();

	public Fatura(int fatura_id, String elektrik, String su, String doðalgaz) {
		super();
		this.fatura_id = fatura_id;
		this.elektrik = elektrik;
		this.su = su;
		this.doðalgaz = doðalgaz;
	}

	public Fatura() {

	}

	@Override
	public ArrayList<Fatura> getList() throws SQLException, ClassNotFoundException {
		ArrayList<Fatura> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		Fatura obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM fatura");
			while (rs.next()) {
				obj = new Fatura(rs.getInt("fatura_id"), rs.getString("elektrik"), rs.getString("su"),
						rs.getString("doðalgaz"));
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
	public boolean add(int fatura_id) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO fatura" + "(fatura_id) VALUES" + "(?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, fatura_id);
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
	public boolean delete(int fatura_id) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM fatura WHERE fatura_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, fatura_id);
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
	

	public int getFatura_id() {
		return fatura_id;
	}

	public void setFatura_id(int fatura_id) {
		this.fatura_id = fatura_id;
	}

	public String getElektrik() {
		return elektrik;
	}

	public void setElektrik(String elektrik) {
		this.elektrik = elektrik;
	}

	public String getSu() {
		return su;
	}

	public void setSu(String su) {
		this.su = su;
	}

	public String getDoðalgaz() {
		return doðalgaz;
	}

	public void setDoðalgaz(String doðalgaz) {
		this.doðalgaz = doðalgaz;
	}

}
