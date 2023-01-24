package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Cep;
import Model.Kart;

public interface Ikart {

	public ArrayList<Kart> getList() throws SQLException, ClassNotFoundException;

	public boolean add(int kart_id, String kart_numarasý, String þifre, String type) throws SQLException, ClassNotFoundException;

	public boolean update(String kart_numarasý, String þifre) throws SQLException, ClassNotFoundException;

	public boolean delete(String kart_numarasý) throws SQLException, ClassNotFoundException;

}
