package admin.notice.service;

import java.io.File;
import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import admin.notice.dao.NoticeDAO;
import admin.notice.dto.NoticeDTO;
import admin.notice.dto.PageDTO;

public class NoticeServiceImp implements NoticeService{

	private NoticeDAO noticeDao;
	
	public NoticeServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setNoticeDao(NoticeDAO noticeDao) {
		this.noticeDao = noticeDao;
	}

	//게시글 총 갯수
	@Override
	public int countProcess() {
		return noticeDao.count();
	}
	//게시글목록
	@Override
	public List<NoticeDTO> listProcess(PageDTO pv) {
		return noticeDao.list(pv);
	}
	
	//검색갯수
	@Override
	public int searchCountProcess(String option, String searchword) {
		return noticeDao.searchCount(option, searchword);
	}
	
	//검색목록
	@Override
	public List<NoticeDTO> searchListProcess(PageDTO pv) {
		return noticeDao.searchList(pv);
	}


	//게시글 읽기
	@Override
	public NoticeDTO contentProcess(int num) {
		return noticeDao.content(num);
	}

	//게시글 읽기 - 첨부파일이름
	@Override
	public String fileSelectprocess(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	//게시글 작성
	@Override
	public void insertProcess(NoticeDTO dto) {
		noticeDao.save(dto);
	}

	//게시글 수정(내용불러오기)
	@Override
	public NoticeDTO updateSelectProcess(int num) {
		return noticeDao.content(num);
	}

	//게시글 수정처리+첨부파일+사진첨부변경
	@Override
	public void updateProcess(NoticeDTO dto, String urlpath, String urlpathImg) {
		
		String filename = dto.getUpload_file();
		//수정할 첨부파일이 있으면 / DB에서 기존 첨부파일이 있는지 먼저 확인하기
		if(filename != null) {
			String path = noticeDao.getFile(dto.getNum());

			//기존 첨부파일이 있으면
			if(path != null) {
				File file = new File(urlpath, path);
				file.delete();
			}
		}
		
		String imgname = dto.getUpload_img();
		//수정할 사진이 있으면 / DB에서 기존 사진이 있는지 먼저 확인하기
		if(imgname != null) {
			String imgpath = noticeDao.getImg(dto.getNum());
			//기존 사진이 있으면
			if(imgpath != null) {
				File img = new File(urlpathImg, imgpath);
				img.delete();
			}
		}
		
		noticeDao.update(dto);
	}

	//게시글 삭제
	@Override
	public void deleteProcess(int num, String urlpath, String urlpathImg) {
		//첨부파일
		String path = noticeDao.getFile(num);
		if(path!=null) {
			File file = new File(urlpath, path);
			file.delete();
		}
		//사진첨부
		String imgpath = noticeDao.getImg(num);
		if(imgpath!=null) {
			File img = new File(urlpathImg, imgpath);
			img.delete();
		}
		noticeDao.delete(num);		
	}

}
