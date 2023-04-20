package admin.bookmanage.dao;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import admin.bookmanage.dto.PageDTO;


public interface BookmanageDAO {
	
	// 보유도서 총 갯수 구하기
	public int count();
	
	// 전체 도서목록 가져오기
	public List<BookmanageDTO> allList(PageDTO pv);
	
	// 검색 갯수 구하기
	public int searchCount(String option, String searchword);
	
	// 검색 도서목록 가져오기
	public List<BookmanageDTO> searchList(PageDTO pv);
	
	// 도서 상세정보 가져오기
	public BookmanageDTO bookInfo(int num);

	// 도서 추가
	public void insert(BookmanageDTO dto);

	// 도서 삭제
	public void delete(int num);
}
