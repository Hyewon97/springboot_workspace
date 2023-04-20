package admin.bookmanage.service;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import admin.bookmanage.dto.PageDTO;

public interface BookmanageService {
	
	//보유도서 총 갯수
	public int countProcess(); 
	
	//전체 도서목록
	public List<BookmanageDTO> allListProcess(PageDTO pv); 
	
	//검색 도서갯수
	public int searchCountProcess(String option, String searchword); 
	
	//검색 도서목록
	public List<BookmanageDTO> searchListProcess(PageDTO pv); 
	
	//도서 상세정보 가져오기
	public BookmanageDTO bookInfoProcess(int num); 

	//도서 추가
	public void insertProcess(BookmanageDTO dto);

	//도서 삭제
	public void deleteProcess(int num);
	
}
