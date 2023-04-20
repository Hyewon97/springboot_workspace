package bookList.dto;

import java.util.Date;

public class BookReviewDTO {
	private int review_keynum;
	private String isbn;
	private String title;
	private int star_num;
	private String review_contents;
	private Date review_date;
	private int book_keynum;
	private String user_keynum;
	private String user_id;
	public BookReviewDTO() {
	}

	public int getReview_keynum() {
		return review_keynum;
	}

	public void setReview_keynum(int review_keynum) {
		this.review_keynum = review_keynum;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getStar_num() {
		return star_num;
	}

	public void setStar_num(int star_num) {
		this.star_num = star_num;
	}

	public String getReview_contents() {
		return review_contents;
	}

	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

	public int getBook_keynum() {
		return book_keynum;
	}

	public void setBook_keynum(int book_keynum) {
		this.book_keynum = book_keynum;
	}

	public String getUser_keynum() {
		return user_keynum;
	}

	public void setUser_keynum(String user_keynum) {
		this.user_keynum = user_keynum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
