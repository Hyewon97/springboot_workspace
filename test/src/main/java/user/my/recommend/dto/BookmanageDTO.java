package user.my.recommend.dto;


public class BookmanageDTO {
	
	private int book_keynum;  //도서번호
	private String isbn;      //isbn
	private String title;      //책제목
	private String author;     //저자리스트
	private String publisher;    //도서 출판사
	private String pub_date;       //출판 일자
	private String contents;     //도서 소개
	private String thumbnail;       //도서표지url
	private String receive_date;     //도서 입고일
	private String category_s;   //도서 세부분류기호
	private String book_category;   //도서 카테고리
	private String loan_state; //도서대출가능여부(Y/N)
	private String borrow_state; //도서예약가능여부(Y/N)


	public BookmanageDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getBook_keynum() {
		return book_keynum;
	}
	


	public void setBook_keynum(int book_keynum) {
		this.book_keynum = book_keynum;
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



	public String getPublisher() {
		return publisher;
	}



	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}



	public String getPub_date() {
		return pub_date;
	}



	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}



	public String getContents() {
		return contents;
	}



	public void setContents(String contents) {
		this.contents = contents;
	}



	public String getThumbnail() {
		return thumbnail;
	}



	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}



	public String getReceive_date() {
		return receive_date;
	}



	public void setReceive_date(String receive_date) {
		this.receive_date = receive_date;
	}



	public String getBook_category() {
		return book_category;
	}



	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}



	public String getLoan_state() {
		return loan_state;
	}



	public void setLoan_state(String loan_state) {
		this.loan_state = loan_state;
	}



	public String getBorrow_state() {
		return borrow_state;
	}



	public void setBorrow_state(String borrow_state) {
		this.borrow_state = borrow_state;
	}

	public String getCategory_s() {
		return category_s;
	}

	public void setCategory_s(String category_s) {
		this.category_s = category_s;
	}

	


}
