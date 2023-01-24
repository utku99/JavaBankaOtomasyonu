package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Hesap;

public interface Ihesap {

	public ArrayList<Hesap> getList() throws SQLException, ClassNotFoundException;

	public boolean add(int hesap_id, String iban, String type) throws SQLException, ClassNotFoundException;

	public boolean delete(String iban) throws SQLException, ClassNotFoundException;

}
