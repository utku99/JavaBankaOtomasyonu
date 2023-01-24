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
	String kart_numaras�, �ifre, bor�, type;
	DBConnection conn = new DBConnection();

	public Kart(int kart_id, String kart_numaras�, String �ifre, String bor�, String type) {
		super();
		this.kart_id = kart_id;
		this.kart_numaras� = kart_numaras�;
		this.�ifre = �ifre;
		this.bor� = bor�;
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
				obj = new Kart(rs.getInt("kart_id"), rs.getString("kart_numaras�"), rs.getString("�ifre"),
						rs.getString("bor�"), rs.getString("type"));
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
	public boolean add(int kart_id, String kart_numaras�, String �ifre, String type) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO kart (kart_id,kart_numaras�,�ifre,type) VALUES (?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, kart_id);
			preparedStatement.setString(2, kart_numaras�);
			preparedStatement.setString(3, �ifre);
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
	public boolean update(String kart_numaras�, String �ifre) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement("UPDATE kart SET �ifre =? WHERE kart_numaras�=?");
			preparedStatement.setString(1, �ifre);
			preparedStatement.setString(2, kart_numaras�);
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
	public boolean delete(String kart_numaras�) throws SQLException, ClassNotFoundException {
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM kart WHERE kart_numaras�=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, kart_numaras�);
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

	public String getKart_numaras�() {
		return kart_numaras�;
	}

	public void setKart_numaras�(String kart_numaras�) {
		this.kart_numaras� = kart_numaras�;
	}

	public String get�ifre() {
		return �ifre;
	}

	public void set�ifre(String �ifre) {
		this.�ifre = �ifre;
	}

	public String getBor�() {
		return bor�;
	}

	public void setBor�(String bor�) {
		this.bor� = bor�;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
