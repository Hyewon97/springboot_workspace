package admin.bookrequest.dao;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import admin.bookrequest.dto.BookRequestDTO;
import admin.bookrequest.dto.PageDTO;


public interface BookRequestDAO {
	
	// 신청도서 총 갯수
	public int count();

	// 신청 도서목록 가져오기
	public List<BookRequestDTO> requestList(PageDTO pv);
	
	// 검색 총 갯수 구하기
	public int searchCount(String option, String searchword);
	
	// 검색 목록 가져오기
	public List<BookRequestDTO> searchList(PageDTO pv);
	
	// 신청도서 거절
	public void reject(int keynum);

	// 신청도서 수락
	public void accept(int keynum);

}
