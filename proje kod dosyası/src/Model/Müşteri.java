package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Interface.Im��teri;

public class M��teri implements Im��teri{
	
	private int m��teri_id;
	String isim, tcno, adres, m��teri_numaras�;
	DBConnection conn = new DBConnection();
	
	public M��teri(int m��teri_id, String isim, String tcno, String adres, String m��teri_numaras�) {
		super();
		this.m��teri_id = m��teri_id;
		this.isim = isim;
		this.tcno = tcno;
		this.adres = adres;
		this.m��teri_numaras� = m��teri_numaras�;
	}
	
	public M��teri() {
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public ArrayList<M��teri> getList() throws SQLException, ClassNotFoundException {
		ArrayList<M��teri> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		M��teri obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM m��teri");
			while (rs.next()) {
				obj = new M��teri(rs.getInt("m��teri_id"), rs.getString("isim"), rs.getString("tcno"),rs.getString("adres"),rs.getString("m��teri_numaras�"));
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
	public boolean add(String isim, String tcno, String adres, String m��teri_numaras�) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO m��teri" + "(isim,tcno,adres,m��teri_numaras�) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, adres);
			preparedStatement.setString(4, m��teri_numaras�);
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
	public boolean update(int m��teri_id, String isim, String tcno, String adres, String m��teri_numaras�) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE m��teri SET isim =?,tcno=?,adres =?,m��teri_numaras�=? WHERE m��teri_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, adres);
			preparedStatement.setString(4, m��teri_numaras�);
			preparedStatement.setInt(5, m��teri_id);
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
	public boolean delete(int m��teri_id) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM m��teri WHERE m��teri_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, m��teri_id);
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
	
	
	
	
	
	
	
	public int getM��teri_id() {
		return m��teri_id;
	}

	public void setM��teri_id(int m��teri_id) {
		this.m��teri_id = m��teri_id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String getTcno() {
		return tcno;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getM��teri_numaras�() {
		return m��teri_numaras�;
	}

	public void setM��teri_numaras�(String m��teri_numaras�) {
		this.m��teri_numaras� = m��teri_numaras�;
	}

	

	
	
	
}
