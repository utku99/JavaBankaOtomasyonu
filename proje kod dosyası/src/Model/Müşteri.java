package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Interface.Imüþteri;

public class Müþteri implements Imüþteri{
	
	private int müþteri_id;
	String isim, tcno, adres, müþteri_numarasý;
	DBConnection conn = new DBConnection();
	
	public Müþteri(int müþteri_id, String isim, String tcno, String adres, String müþteri_numarasý) {
		super();
		this.müþteri_id = müþteri_id;
		this.isim = isim;
		this.tcno = tcno;
		this.adres = adres;
		this.müþteri_numarasý = müþteri_numarasý;
	}
	
	public Müþteri() {
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public ArrayList<Müþteri> getList() throws SQLException, ClassNotFoundException {
		ArrayList<Müþteri> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		Müþteri obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM müþteri");
			while (rs.next()) {
				obj = new Müþteri(rs.getInt("müþteri_id"), rs.getString("isim"), rs.getString("tcno"),rs.getString("adres"),rs.getString("müþteri_numarasý"));
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
	public boolean add(String isim, String tcno, String adres, String müþteri_numarasý) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO müþteri" + "(isim,tcno,adres,müþteri_numarasý) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, adres);
			preparedStatement.setString(4, müþteri_numarasý);
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
	public boolean update(int müþteri_id, String isim, String tcno, String adres, String müþteri_numarasý) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE müþteri SET isim =?,tcno=?,adres =?,müþteri_numarasý=? WHERE müþteri_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, adres);
			preparedStatement.setString(4, müþteri_numarasý);
			preparedStatement.setInt(5, müþteri_id);
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
	public boolean delete(int müþteri_id) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM müþteri WHERE müþteri_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, müþteri_id);
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
	
	
	
	
	
	
	
	public int getMüþteri_id() {
		return müþteri_id;
	}

	public void setMüþteri_id(int müþteri_id) {
		this.müþteri_id = müþteri_id;
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

	public String getMüþteri_numarasý() {
		return müþteri_numarasý;
	}

	public void setMüþteri_numarasý(String müþteri_numarasý) {
		this.müþteri_numarasý = müþteri_numarasý;
	}

	

	
	
	
}
