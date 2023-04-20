package userBookList.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;

import admin.bookmanage.dto.BookmanageDTO;
import bookList.dto.BookListDTO;
import print.dto.PageDTO;
import userBookList.dto.UserBookListDTO;
import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import user.dto.AuthInfo;
import user.dto.UserDTO;

public class UserBookListDaoImp implements UserBookListDAO {

	private SqlSession sqlSession;

	public UserBookListDaoImp() {
		// TODO Auto-generated constructor stub
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 현재 대출/예약중인 도서 조회
	@Override
	public List<UserBookListDTO> CurrBorrowList(String user_keynum) {
		return sqlSession.selectList("userBookList.currBorrowList", user_keynum);
	}

	// 대출 내역 조회
	@Override
	public List<UserBookListDTO> PastBorrowList(PageDTO pv,String user_keynum) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("pv", pv);
		mapa.put("user_keynum", user_keynum);
		
		return sqlSession.selectList("userBookList.pastBorrowList", mapa);
	}

	// 반납일자 조회
	@Override
	public Date ReturnDueDate(int book_keynum) {
		return sqlSession.selectOne("userBookList.CurrLoanByBk", book_keynum);
	};

	// book_keynum별 booklist 가져오기
	public BookListDTO BookData(int book_keynum) {
		return sqlSession.selectOne("userBookList.bookData", book_keynum);
	}

	// 예약자 존재 확인
	@Override
	public String ReserveExistence(int borrow_keynum) {
		return sqlSession.selectOne("userBookList.WhenReserveExist", borrow_keynum);
	}

	// 입력 적합성 검사
	@Override
	public int checkUserBooklistCount(UserBookListDTO udto) {
		return sqlSession.selectOne("userBookList.checkCount", udto);
	}

	// 대출 신청
	@Override
	public void Borrow(UserBookListDTO udto) {

		sqlSession.insert("userBookList.borrow", udto);
		sqlSession.update("userBookList.onBorrow", udto);
	}

	// 대출 연장
	@Override
	public void ReturnExtend(int borrow_keynum, int book_keynum) {
		sqlSession.update("userBookList.returnExtend", borrow_keynum);
		sqlSession.update("userBookList.returnExtendReserve", book_keynum);
	}

	// 반납 요청
	@Override
	public UserBookListDTO Return(int borrow_keynum) {
		sqlSession.update("userBookList.return", borrow_keynum);
		sqlSession.update("userBookList.onReturn", borrow_keynum);
		// 예약자 정보 가져오기
		return sqlSession.selectOne("userBookList.selectReserveExist",
				sqlSession.selectOne("userBookList.selectBookKeynum", borrow_keynum));
	}

	// 예약 타당성 조사
	@Override
	public UserBookListDTO ReserveChk(UserBookListDTO udto) {
		return sqlSession.selectOne("userBookList.selectBorrowExist", udto);
	}

	// 예약 신청
	@Override
	public void Reserve(UserBookListDTO udto) {
		sqlSession.insert("userBookList.reserve", udto);
		sqlSession.update("userBookList.onReserve", udto);
	}

	// 예약 취소
	@Override
	public void CancelReserve(int borrow_keynum) {
		sqlSession.update("userBookList.onCancelReserve", borrow_keynum);
		sqlSession.delete("userBookList.cancelReserve", borrow_keynum);
	}

	// 테스트 기능
	@Override
	public BookListDTO Test() {
		return sqlSession.selectOne("userBookList.bookinfo");
	}
	@Override
	public int countpast(String keynum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("userBookList.countall",keynum);
	}


}
