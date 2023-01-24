package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Interface.Icep;

public class Cep implements Icep{

	private int cep_id;
	String numara, bakiye;
	DBConnection conn = new DBConnection();
	
	public Cep(int cep_id, String numara, String bakiye) {
		super();
		this.cep_id = cep_id;
		this.numara = numara;
		this.bakiye = bakiye;
	}
	
	public Cep() {

	}
	
	
	
	
	
	@Override
	public ArrayList<Cep> getList() throws SQLException, ClassNotFoundException {
		ArrayList<Cep> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		Cep obj;
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM cep");
			while (rs.next()) {
				obj = new Cep(rs.getInt("cep_id"), rs.getString("numara"), rs.getString("bakiye"));
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
	public boolean add(int cep_id, String numara) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO cep" + "(cep_id,numara) VALUES" + "(?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, cep_id);
			preparedStatement.setString(2, numara);
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
	public boolean update(int cep_id, String numara) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE cep SET numara =? WHERE cep_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, numara);
			preparedStatement.setInt(2, cep_id);
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
	public boolean delete(int cep_id) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM cep WHERE cep_id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, cep_id);
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







	public int getCep_id() {
		return cep_id;
	}
	public void setCep_id(int cep_id) {
		this.cep_id = cep_id;
	}
	public String getNumara() {
		return numara;
	}
	public void setNumara(String numara) {
		this.numara = numara;
	}
	public String getBakiye() {
		return bakiye;
	}
	public void setBakiye(String bakiye) {
		this.bakiye = bakiye;
	}
	
	
	

}
