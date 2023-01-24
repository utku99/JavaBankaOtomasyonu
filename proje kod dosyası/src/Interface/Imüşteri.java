package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.M��teri;

public interface Im��teri {
	
	public ArrayList<M��teri> getList() throws SQLException, ClassNotFoundException;
	
	public boolean add(String isim, String tcno, String adres, String m��teri_numaras�) throws SQLException, ClassNotFoundException;

	public boolean update(int m��teri_id, String isim, String tcno, String adres, String m��teri_numaras�) throws SQLException, ClassNotFoundException;
	
	public boolean delete(int m��teri_id) throws SQLException, ClassNotFoundException;

}
