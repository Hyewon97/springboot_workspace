package user.notice.service;

import java.util.List;

import user.notice.dto.NoticeDTO;
import user.notice.dto.PageDTO;

public interface NoticeService {
	
	//게시글 목록 조회
	public int countProcess(); //게시글 총 갯수
	public List<NoticeDTO> listProcess(PageDTO pv); //게시글목록
	public List<NoticeDTO> toplistProcess(); //고정게시글(fix=='Y')
	
	//검색 도서갯수
	public int searchCountProcess(String option, String searchword); 
	//검색 도서목록
	public List<NoticeDTO> searchListProcess(PageDTO pv);
	
	//게시글 읽기
	public NoticeDTO contentProcess(int num); //게시글 내용
	public NoticeDTO preNextProcess(int num); //이전글, 다음글
	public String fileSelectprocess(int num); //첨부파일이름

	//main test - 가장 최근 게시글
	public NoticeDTO latestOneProcess(); 
	
	//main test - 가장 최근 게시글
	public List<NoticeDTO> latestFiveProcess(); 
	
}
