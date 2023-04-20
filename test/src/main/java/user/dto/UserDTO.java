package user.dto;

import java.util.Date;

public class UserDTO {
	private String user_id;
	private String user_password;
	private String user_name;
	private String user_address;

	private String user_sex;
	private int user_age;
	private Date sign_Date;

	public UserDTO() {
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public int getUser_age() {
		return user_age;
	}

	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}

	public Date getSign_Date() {
		return sign_Date;
	}

	public void setSign_Date(Date sign_Date) {
		this.sign_Date = sign_Date;
	}
	
}
