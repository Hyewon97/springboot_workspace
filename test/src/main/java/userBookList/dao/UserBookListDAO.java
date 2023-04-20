package userBookList.dao;

import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import user.dto.AuthInfo;
import user.dto.UserDTO;
import admin.bookmanage.dto.BookmanageDTO;
import bookList.dto.BookListDTO;
import print.dto.PageDTO;
import userBookList.dto.UserBookListDTO;

public interface UserBookListDAO {

	// 현재 대출/예약중인 도서 조회
	public List<UserBookListDTO> CurrBorrowList(String user_keynum);

	// 대출 내역 조회
	public List<UserBookListDTO> PastBorrowList(PageDTO pv ,String user_keynum);

	// 반납일자 조회
	public Date ReturnDueDate(int book_keynum);

	// book_keynum별 booklist 가져오기
	public BookListDTO BookData(int book_keynum);

	// 예약자 존재 확인
	public String ReserveExistence(int borrow_keynum);

	// 입력 적합성 검사
	public int checkUserBooklistCount(UserBookListDTO udto);

	// 대출 신청
	public void Borrow(UserBookListDTO udto);

	// 대출 연장
	public void ReturnExtend(int borrow_keynum, int book_keynum);

	// 반납 요청
	public UserBookListDTO Return(int borrow_keynum);

	// 예약 타당성 조사
	public UserBookListDTO ReserveChk(UserBookListDTO udto);

	// 예약 신청
	public void Reserve(UserBookListDTO udto);

	// 예약 취소
	public void CancelReserve(int borrow_keynum);

	// 테스트 기능
	public BookListDTO Test();
	
	public int countpast(String keynum);


}
