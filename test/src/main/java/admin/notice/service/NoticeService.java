package admin.notice.service;

import java.util.List;

import admin.notice.dto.NoticeDTO;
import admin.notice.dto.PageDTO;

public interface NoticeService {
	
	//게시글 목록 조회
	public int countProcess(); //게시글 총 갯수
	public List<NoticeDTO> listProcess(PageDTO pv); //게시글목록
	
	//게시글 읽기
	public NoticeDTO contentProcess(int num); //게시글 내용
	public String fileSelectprocess(int num); //첨부파일이름
	
	//검색 도서갯수
	public int searchCountProcess(String option, String searchword); 
	//검색 도서목록
	public List<NoticeDTO> searchListProcess(PageDTO pv);
	
	//게시글 작성
	public void insertProcess(NoticeDTO dto);
	
	//게시글 수정(내용불러오기)
	public NoticeDTO updateSelectProcess(int num);
	//게시글 수정처리+첨부파일+사진첨부변경
	public void updateProcess(NoticeDTO dto, String urlpath, String urlpathImg);
	
	//게시글 삭제
	public void deleteProcess(int num, String urlpath, String urlpathImg);
	
}
