package admin.bookrequest.service;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import admin.bookrequest.dto.BookRequestDTO;
import admin.bookrequest.dto.PageDTO;
import admin.notice.dto.NoticeDTO;

public interface BookRequestService {
	
	//신청도서 총 갯수
	public int countProcess(); 
	
	//신청 도서목록
	public List<BookRequestDTO> requestListProcess(PageDTO pv); 
	
	//검색 도서갯수
	public int searchCountProcess(String option, String searchword); 
	
	//검색 도서목록
	public List<BookRequestDTO> searchListProcess(PageDTO pv);
	
	//신청도서 거절
	public void rejectSelectProcess(int keynum);
	
	//신청도서 수락
	public void acceptSelectProcess(int keynum);
	
	
	
}
