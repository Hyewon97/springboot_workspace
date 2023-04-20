package userBookList.service;

import java.util.Date;
import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import bookList.dto.BookListDTO;
import print.dto.PageDTO;
import userBookList.dto.UserBookListDTO;

public interface UserBookListService {

	// 현재 대출/예약중인 도서 조회
	public List<UserBookListDTO> CurrBorrowListProcesss(String user_keynum);

	// 대출 내역 조회
	public List<UserBookListDTO> PastBorrowListProcesss(PageDTO pv, String user_keynum);

	// 반납일자 조회
	public Date ReturnDueDateProcess(int book_keynum);

	// book_keynum별 booklist 가져오기
	public BookListDTO BookDataProcess(int book_keynum);

	// 예약자 존재 확인
	public String ReserveExistenceProcess(int borrow_keynum);

	// 대출 신청
	public boolean BorrowProcess(UserBookListDTO udto);

	// 대출 연장
	public void ExtendReturnProcess(int borrow_keynum, int book_keynum);

	// 반납 요청
	public UserBookListDTO ReturnProcess(int borrow_keynum);

	// 예약 신청
	public int ReserveProcess(UserBookListDTO udto);

	// 예약 취소
	public void CancelReserveProcess(int borrow_keynum);

	// 테스트 기능
	public BookListDTO TestProcess();
	public int countall(String keynum);
	
}
