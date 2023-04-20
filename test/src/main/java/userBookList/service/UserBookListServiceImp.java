package userBookList.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.json.simple.JSONObject;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;

import java.util.Arrays;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import admin.bookmanage.dto.BookmanageDTO;
import bookList.dto.BookListDTO;
import print.dto.PageDTO;
import user.dao.UserDAO;
import userBookList.dao.UserBookListDAO;
import userBookList.dto.UserBookListDTO;

public class UserBookListServiceImp implements UserBookListService {

	private UserBookListDAO userBookListDao;
	

	public UserBookListServiceImp() {
		// TODO Auto-generated constructor stub
	}



	public void setUserBookListDao(UserBookListDAO userBookListDao) {
		this.userBookListDao = userBookListDao;
	}

	// 현재 대출/예약중인 도서 조회
	@Override
	public List<UserBookListDTO> CurrBorrowListProcesss(String user_keynum) {
		return userBookListDao.CurrBorrowList(user_keynum);
	}

	// 대출 내역 조회
	@Override
	public List<UserBookListDTO> PastBorrowListProcesss(PageDTO pv, String user_keynum) {
		return userBookListDao.PastBorrowList(pv, user_keynum);
	}

	// 반납일자 조회
	@Override
	public Date ReturnDueDateProcess(int book_keynum) {
		return userBookListDao.ReturnDueDate(book_keynum);
	};

	// book_keynum별 booklist 가져오기
	public BookListDTO BookDataProcess(int book_keynum) {
		return userBookListDao.BookData(book_keynum);
	}

	// 예약자 존재 확인
	@Override
	public String ReserveExistenceProcess(int borrow_keynum) {
		return userBookListDao.ReserveExistence(borrow_keynum);
	};

	// 대출 신청
	@Override
	public boolean BorrowProcess(UserBookListDTO udto) {
		int count = userBookListDao.checkUserBooklistCount(udto);
		// 4권 이상 대출/ 예약시 거절
		if (count >= 4) {
			return false;
		}
		userBookListDao.Borrow(udto);
		return true;
	}

	// 대출 연장
	@Override
	public void ExtendReturnProcess(int borrow_keynum, int book_keynum) {
		userBookListDao.ReturnExtend(borrow_keynum, book_keynum);
	}

	// 반납 요청
	@Override
	public UserBookListDTO ReturnProcess(int borrow_keynum) {
		return userBookListDao.Return(borrow_keynum);
	}

	// 예약 신청
	@Override
	public int ReserveProcess(UserBookListDTO udto) {
		int count = userBookListDao.checkUserBooklistCount(udto);
		System.out.println(udto.getUser_keynum());
		// 4권 이상 대출/예약시 거절
		if (count >= 4) {
			return 1;
		}
		// 대출중인 책은 예약 불가
		else if (userBookListDao.ReserveChk(udto) != null) {
			return 2;
		}
		// 예약 성공

		userBookListDao.Reserve(udto);
		return 3;
	}

	// 예약 취소
	@Override
	public void CancelReserveProcess(int borrow_keynum) {
		userBookListDao.CancelReserve(borrow_keynum);
	}

	// 테스트 기능
	@Override
	public BookListDTO TestProcess() {
		return userBookListDao.Test();
	}
	@Override
	public int countall(String keynum) {
		// TODO Auto-generated method stub
		return userBookListDao.countpast(keynum);
	}



}
