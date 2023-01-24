package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Fatura;
import Model.Hesap;

public interface Ifatura {

	public ArrayList<Fatura> getList() throws SQLException, ClassNotFoundException;

	public boolean add(int fatura_id) throws SQLException, ClassNotFoundException;

	public boolean delete(int fatura_id) throws SQLException, ClassNotFoundException;
	
}
