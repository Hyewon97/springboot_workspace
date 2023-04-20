package user.notice.dao;

import java.util.List;

import user.notice.dto.NoticeDTO;
import user.notice.dto.PageDTO;

public interface NoticeDAO {
	
	// 데이터(row) 총 갯수 구하기
	public int count();
	
	// 데이터(row) 목록 SELECT해서 list로 받아오기
	public List<NoticeDTO> list(PageDTO pv);
	public List<NoticeDTO> toplist();
	
	// 검색 갯수 구하기
	public int searchCount(String option, String searchword);
	// 검색 도서목록 가져오기
	public List<NoticeDTO> searchList(PageDTO pv);
	
	// 게시글 읽기
	public NoticeDTO content(int num);
	public void readCount(int num); //조회수증가
	public String getFile(int num); //첨부파일
	public String getImg(int num); //사진첨부
	public NoticeDTO preNext(int num); //이전글 다음글
	
	//main페이지 test - 가장 최근 고정글 1개 가져오기
	public NoticeDTO latestOne();
	
	//main페이지 test - 가장 최근 고정글 1개 가져오기
	public List<NoticeDTO> latestfive();

}
