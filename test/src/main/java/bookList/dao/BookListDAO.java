package bookList.dao;

import java.util.List;

import bookList.dto.BookListDTO;
import bookList.dto.BookReviewDTO;
import bookList.dto.PageDTO;

public interface BookListDAO {
	public BookListDTO selectBook(String isbn);
	
	// 총 후기 갯수(row)구하기
	public int count(String isbn);
	
	// 데이터(row)목록 Select, list로 받아오기
	public List<BookReviewDTO> reviewList(PageDTO pv , String isbn);
	
	// 해당 도서에 후기가 존재하는지 확인
	public int revCheck(String keynum, String book_keynum);
	
	// 후기 작성
	public void save(String keynum,BookReviewDTO dto);
	
	// 후기 수정
	public void update(BookReviewDTO dto);
	
	// 후기 삭제
	public void delete(int num);
	
	// 유저 키넘
	public String getKeynum(String id);
	
	// 유저 아이디
	public String getId(String keynum);
	public int count();

}
