package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Y�netici;

public interface Iy�netici {
	
	public ArrayList<Y�netici> getList() throws SQLException, ClassNotFoundException;

	public boolean add(String �ifre, String isim) throws SQLException, ClassNotFoundException;

	public boolean update(int user_id, String �ifre, String isim) throws SQLException, ClassNotFoundException;

	public boolean delete(int user_id) throws SQLException, ClassNotFoundException;

	
}
