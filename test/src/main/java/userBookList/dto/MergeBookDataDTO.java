package userBookList.dto;

import java.util.Date;

public class MergeBookDataDTO {

	private int book_keynum;
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private String pub_date;
	private String contents;
	private String thumbnail;
	private Date receive_date;
	private String loan_state;
	private String borrow_state;
	private int category_s;
	private String book_category;
	private int borrow_keynum;
	private String user_id;
	private Date borrow_date;
	private Date return_date;
	private Date return_due_date;
	private char return_extend;
	
	public MergeBookDataDTO() {
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

	public Date getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(Date receive_date) {
		this.receive_date = receive_date;
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

	public String getBook_category() {
		return book_category;
	}

	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}

	public int getBorrow_keynum() {
		return borrow_keynum;
	}

	public void setBorrow_keynum(int borrow_keynum) {
		this.borrow_keynum = borrow_keynum;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public Date getReturn_due_date() {
		return return_due_date;
	}

	public void setReturn_due_date(Date return_due_date) {
		this.return_due_date = return_due_date;
	}

	public char getReturn_extend() {
		return return_extend;
	}

	public void setReturn_extend(char return_extend) {
		this.return_extend = return_extend;
	}
	
	
}
