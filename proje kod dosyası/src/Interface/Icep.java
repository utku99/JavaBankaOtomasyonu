package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Cep;
import Model.Fatura;

public interface Icep {

	public ArrayList<Cep> getList() throws SQLException, ClassNotFoundException;

	public boolean add(int cep_id, String numara) throws SQLException, ClassNotFoundException;
	
	public boolean update(int cep_id, String numara) throws SQLException, ClassNotFoundException;

	public boolean delete(int cep_id) throws SQLException, ClassNotFoundException;
}
