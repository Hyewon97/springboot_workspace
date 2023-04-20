package bookList.dto;

import java.util.Date;

public class BookListDTO {
	private int book_keynum; //도서번호
	private String isbn; // ISBN
	private String title; // 제목
	private String author; // 저자
	private String publisher; // 도서 출판사
	private String pub_date; // 출판일자
	private String contents; // 도서 소개
	private String thumbnail; // 도서 표지 URL
	private Date receive_date; // 입고일
	private int category_s; // 도서 세부분류
	private String book_category; // 도서 분류번호
	private String loan_state; // 대출상태
	private String borrow_state; // 예약상태
	
	public BookListDTO() {
		
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

	public Date getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(Date receive_date) {
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

	public int getCategory_s() {
		return category_s;
	}

	public void setCategory_s(int category_s) {
		this.category_s = category_s;
	}
	
	
}
