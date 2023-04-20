package user.request.dto;


public class UserRequestDTO {

	private int request_keynum;
	private String isbn;
	private String title;
	private String author;
	private String user_keynum;
	private String request_state; //반영상태(null:미처리, y:수락, n:반려)
	private String request_text; //신청사유
	private String request_date;  //신청일
	private String ref_date; //반영일
	
	public UserRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserRequestDTO(String isbn, String title, String author, String user_keynum, String request_text) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.user_keynum = user_keynum;
		this.request_text = request_text;
	}

	public int getRequest_keynum() {
		return request_keynum;
	}

	public void setRequest_keynum(int request_keynum) {
		this.request_keynum = request_keynum;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUser_keynum() {
		return user_keynum;
	}

	public void setUser_keynum(String user_keynum) {
		this.user_keynum = user_keynum;
	}

	public String getRequest_state() {
		return request_state;
	}

	public void setRequest_state(String request_state) {
		this.request_state = request_state;
	}

	public String getRequest_text() {
		return request_text;
	}

	public void setRequest_text(String request_text) {
		this.request_text = request_text;
	}

	public String getRequest_date() {
		return request_date;
	}

	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}

	public String getRef_date() {
		return ref_date;
	}

	public void setRef_date(String ref_date) {
		this.ref_date = ref_date;
	}

	
	
	
}
