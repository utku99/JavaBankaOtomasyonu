package Helper;
import java.sql.*;
public class DBConnection {
	Connection c=null;

	public Connection connDb() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			this.c=DriverManager.getConnection("jdbc:derby:C:\\Users\\pc\\Desktop\\UTKU_AKSOY_1306180007_NYPproje\\proje kod dosyasý\\Db");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
