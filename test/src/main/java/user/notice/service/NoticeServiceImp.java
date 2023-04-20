package user.notice.service;

import java.io.File;
import java.util.List;

import user.notice.dao.NoticeDAO;
import user.notice.dto.NoticeDTO;
import user.notice.dto.PageDTO;

public class NoticeServiceImp implements NoticeService{

	private NoticeDAO userNoticeDao;
	
	public NoticeServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setUserNoticeDao(NoticeDAO userNoticeDao) {
		this.userNoticeDao = userNoticeDao;
	}

	//게시글 총 갯수
	@Override
	public int countProcess() {
		return userNoticeDao.count();
	}
	//게시글목록
	@Override
	public List<NoticeDTO> listProcess(PageDTO pv) {
		return userNoticeDao.list(pv);
	}
	//고정게시글(fix=='Y')
	@Override
	public List<NoticeDTO> toplistProcess() {
		return userNoticeDao.toplist();
	}
	
	//검색갯수
	@Override
	public int searchCountProcess(String option, String searchword) {
		return userNoticeDao.searchCount(option, searchword);
	}
	
	//검색목록
	@Override
	public List<NoticeDTO> searchListProcess(PageDTO pv) {
		return userNoticeDao.searchList(pv);
	}

	//게시글 읽기
	@Override
	public NoticeDTO contentProcess(int num) {
		userNoticeDao.readCount(num); 
		return userNoticeDao.content(num);
	}
	//게시글 읽기 - 이전글,다음글
	@Override
	public NoticeDTO preNextProcess(int num) {
		return userNoticeDao.preNext(num);
	}
	//게시글 읽기 - 첨부파일이름
	@Override
	public String fileSelectprocess(int num) {
		return userNoticeDao.getFile(num);
	}

	///////////////////////////////////////////////////
	//main test - 가장 최근 게시글
	@Override
	public NoticeDTO latestOneProcess() {
		return userNoticeDao.latestOne();
	}

	@Override
	public List<NoticeDTO> latestFiveProcess() {
		return userNoticeDao.latestfive();
	}
}
