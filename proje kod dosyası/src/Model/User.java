package Model;

import Helper.DBConnection;

public abstract class User {

	private int user_id;
	String �ifre, isim, type;
	DBConnection conn = new DBConnection();
	
	public User(int user_id, String �ifre, String isim, String type) {
		super();
		this.user_id = user_id;
		this.�ifre = �ifre;
		this.isim = isim;
		this.type = type;
	}
	
	public User(){
		
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String get�ifre() {
		return �ifre;
	}

	public void set�ifre(String �ifre) {
		this.�ifre = �ifre;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
