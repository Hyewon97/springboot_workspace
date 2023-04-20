package admin.notice.dao;

import java.util.List;

import admin.notice.dto.NoticeDTO;
import admin.notice.dto.PageDTO;

public interface NoticeDAO {
	
	// 데이터(row) 총 갯수 구하기
	public int count();
	
	// 데이터(row) 목록 SELECT해서 list로 받아오기
	public List<NoticeDTO> list(PageDTO pv);
	
	// 게시글 읽기
	public NoticeDTO content(int num);
	public String getFile(int num); //첨부파일
	public String getImg(int num); //사진첨부
	
	// 검색 갯수 구하기
	public int searchCount(String option, String searchword);
	// 검색 도서목록 가져오기
	public List<NoticeDTO> searchList(PageDTO pv);

	// 게시글 작성
	public void save(NoticeDTO dto);
	
	// 게시글 수정
	public void update(NoticeDTO dto);
	
	// 게시글 삭제
	public void delete(int num);
}
