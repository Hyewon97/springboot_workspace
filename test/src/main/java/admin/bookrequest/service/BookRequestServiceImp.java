package admin.bookrequest.service;

import java.util.List;

import admin.bookmanage.dao.BookmanageDAO;
import admin.bookmanage.dto.BookmanageDTO;
import admin.bookrequest.dao.BookRequestDAO;
import admin.bookrequest.dto.BookRequestDTO;
import admin.bookrequest.dto.PageDTO;
import admin.notice.dto.NoticeDTO;


public class BookRequestServiceImp implements BookRequestService{

	private BookRequestDAO bookrequestDao;
	private BookmanageDAO bookmanageDao;
	
	public BookRequestServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setBookrequestDao(BookRequestDAO bookrequestDao) {
		this.bookrequestDao = bookrequestDao;
	}

	public void setBookmanageDao(BookmanageDAO bookmanageDao) {
		this.bookmanageDao = bookmanageDao;
	}
	
	//보유도서 총 갯수
	@Override
	public int countProcess() {
		return bookrequestDao.count();
	}

	//신청 도서목록
	@Override
	public List<BookRequestDTO> requestListProcess(PageDTO pv) {
		return bookrequestDao.requestList(pv);
	}
	
	//검색갯수
	@Override
	public int searchCountProcess(String option, String searchword) {
		return bookrequestDao.searchCount(option, searchword);
	}
	
	//검색목록
	@Override
	public List<BookRequestDTO> searchListProcess(PageDTO pv) {
		return bookrequestDao.searchList(pv);
	}

	@Override
	public void rejectSelectProcess(int keynum) {
		bookrequestDao.reject(keynum);
		
	}

	@Override
	public void acceptSelectProcess(int keynum) {
		bookrequestDao.accept(keynum);
	}

	
}