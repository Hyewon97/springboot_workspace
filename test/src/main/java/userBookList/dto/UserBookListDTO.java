package userBookList.dto;

import java.util.Date;

public class UserBookListDTO {
	private int borrow_keynum;
	private int book_keynum;
	private int book_category;
	private String user_keynum;
	private Date borrow_date;
	private Date return_date;
	private Date return_due_date;
	private char return_extend;
	private String borrow_state;
	
	public UserBookListDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getBorrow_keynum() {
		return borrow_keynum;
	}

	public void setBorrow_keynum(int borrow_keynum) {
		this.borrow_keynum = borrow_keynum;
	}

	public int getBook_keynum() {
		return book_keynum;
	}

	public void setBook_keynum(int book_keynum) {
		this.book_keynum = book_keynum;
	}

	public int getBook_category() {
		return book_category;
	}

	public void setBook_category(int book_category) {
		this.book_category = book_category;
	}



	public String getUser_keynum() {
		return user_keynum;
	}

	public void setUser_keynum(String user_keynum) {
		this.user_keynum = user_keynum;
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

	public String getBorrow_state() {
		return borrow_state;
	}

	public void setBorrow_state(String borrow_state) {
		this.borrow_state = borrow_state;
	}
}
