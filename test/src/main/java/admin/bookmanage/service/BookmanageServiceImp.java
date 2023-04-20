package admin.bookmanage.service;

import java.util.List;

import admin.bookmanage.dao.BookmanageDAO;
import admin.bookmanage.dto.BookmanageDTO;
import admin.bookmanage.dto.PageDTO;


public class BookmanageServiceImp implements BookmanageService{

	private BookmanageDAO bookmanageDao;
	
	public BookmanageServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setBookmanageDao(BookmanageDAO bookmanageDao) {
		this.bookmanageDao = bookmanageDao;
	}

	//보유도서 총 갯수
	@Override
	public int countProcess() {
		return bookmanageDao.count();
	}
	
	//전체 도서목록
	@Override
	public List<BookmanageDTO> allListProcess(PageDTO pv) {
		return bookmanageDao.allList(pv);
	}
	
	//검색갯수
	@Override
	public int searchCountProcess(String option, String searchword) {
		return bookmanageDao.searchCount(option, searchword);
	}
	
	//검색목록
	@Override
	public List<BookmanageDTO> searchListProcess(PageDTO pv) {
		return bookmanageDao.searchList(pv);
	}

	//도서 상세정보 가져오기
	@Override
	public BookmanageDTO bookInfoProcess(int num) {
		return bookmanageDao.bookInfo(num);
	}

	//도서 추가
	@Override
	public void insertProcess(BookmanageDTO dto) {
		bookmanageDao.insert(dto);
	}

	//게시글 삭제
	@Override
	public void deleteProcess(int num) {
		
		bookmanageDao.delete(num);		
	}
}
