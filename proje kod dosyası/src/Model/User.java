package Model;

import Helper.DBConnection;

public abstract class User {

	private int user_id;
	String þifre, isim, type;
	DBConnection conn = new DBConnection();
	
	public User(int user_id, String þifre, String isim, String type) {
		super();
		this.user_id = user_id;
		this.þifre = þifre;
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

	public String getÞifre() {
		return þifre;
	}

	public void setÞifre(String þifre) {
		this.þifre = þifre;
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
