package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Yönetici;

public interface Iyönetici {
	
	public ArrayList<Yönetici> getList() throws SQLException, ClassNotFoundException;

	public boolean add(String þifre, String isim) throws SQLException, ClassNotFoundException;

	public boolean update(int user_id, String þifre, String isim) throws SQLException, ClassNotFoundException;

	public boolean delete(int user_id) throws SQLException, ClassNotFoundException;

	
}
