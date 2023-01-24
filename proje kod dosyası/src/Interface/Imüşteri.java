package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Müþteri;

public interface Imüþteri {
	
	public ArrayList<Müþteri> getList() throws SQLException, ClassNotFoundException;
	
	public boolean add(String isim, String tcno, String adres, String müþteri_numarasý) throws SQLException, ClassNotFoundException;

	public boolean update(int müþteri_id, String isim, String tcno, String adres, String müþteri_numarasý) throws SQLException, ClassNotFoundException;
	
	public boolean delete(int müþteri_id) throws SQLException, ClassNotFoundException;

}
