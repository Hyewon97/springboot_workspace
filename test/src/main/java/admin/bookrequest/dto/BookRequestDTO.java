package admin.bookrequest.dto;


public class BookRequestDTO {
	
	private int request_keynum;  //신청번호
	private String isbn;      //isbn
	private String title;      //책제목
	private String author;     //저자리스트
	private String user_id;    //신청 회원 아이디
	private String request_state;  //반영상태
	private String request_text;  //신청사유
	private String request_date;  //신청일
	private String ref_date;  //반영일
	
	public BookRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public BookRequestDTO(int request_keynum, String isbn, String title, String author, String user_id,
			String request_state, String request_text, String request_date, String ref_date) {
		super();
		this.request_keynum = request_keynum;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.user_id = user_id;
		this.request_state = request_state;
		this.request_text = request_text;
		this.request_date = request_date;
		this.ref_date = ref_date;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
