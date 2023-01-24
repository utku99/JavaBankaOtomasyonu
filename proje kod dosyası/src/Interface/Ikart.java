package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Cep;
import Model.Kart;

public interface Ikart {

	public ArrayList<Kart> getList() throws SQLException, ClassNotFoundException;

	public boolean add(int kart_id, String kart_numaras�, String �ifre, String type) throws SQLException, ClassNotFoundException;

	public boolean update(String kart_numaras�, String �ifre) throws SQLException, ClassNotFoundException;

	public boolean delete(String kart_numaras�) throws SQLException, ClassNotFoundException;

}
