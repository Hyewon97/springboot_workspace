package user.my.recommend.dto;

public class UserRecommendDTO {

	private String user_name;
	private String user_sex;
	private int user_age;
	private String isbn;
	private String title;
	
	public UserRecommendDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserRecommendDTO(String isbn, String title) {
		super();
		this.isbn = isbn;
		this.title = title;
	}
	
	public UserRecommendDTO(String user_name, String user_sex, int user_age) {
		super();
		this.user_name = user_name;
		this.user_sex = user_sex;
		this.user_age = user_age;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUser_age() {
		return user_age;
	}

	public void setUser_age(int user_age) {
		this.user_age = user_age;
	} 
	
	
}
