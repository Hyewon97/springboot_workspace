package admin.notice.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import admin.notice.dao.NoticeDAO;
import admin.notice.file.FileUpload;

//다운로드 창을 띄우기 위한 뷰 페이지
public class NoticeDownLoadView extends AbstractView{
	private NoticeDAO noticeDao;
	
	public NoticeDownLoadView() {
		// TODO Auto-generated constructor stub
	}
	
	public void setNoticeDao(NoticeDAO noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		int num = Integer.parseInt(request.getParameter("num"));
		String saveDirectory = FileUpload.urlPath(request);  //root파일주소 가져올수있을듯?
		//System.out.println("주소 : "+saveDirectory);
		
		String upload = noticeDao.getFile(num);
		String fileName = upload.substring(upload.indexOf("_")+1);
		//System.out.println("파일이름 : "+fileName);
		
		//첨부파일명이 한글일 때 인코딩 작업
		String str = URLEncoder.encode(fileName, "UTF-8");
		//원본파일명에 공백이 있는 경우 '+'기호로 바뀌기 때문에 공백으로 다시 변경작업
		str = str.replaceAll("\\+", "%20");
		
		//컨텐츠 타입이 다양할 때 처리할 수 있도록 
		response.setContentType("application/octet-stream");
		//다운로드 창에 보여줄 파일명을 지정
		response.setHeader("Content-Disposition", "attachment;filename=" + str +";");
	
		//서버에 저장된 파일을 읽어와서 클라이언트에 출력
		FileCopyUtils.copy(new FileInputStream(new File(saveDirectory,upload)), response.getOutputStream());
	}//renderMergedOutputModel()
	
}//class BoardDownLoadView
