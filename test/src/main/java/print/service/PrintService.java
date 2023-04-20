package print.service;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import print.dto.PageDTO;

public interface PrintService {
	
	// 전체 리스트
	public List<BookmanageDTO> printAll(PageDTO pv);
	
	// 전체 수 
	public int countAll();
	
	//검색 결과 리스트
	public List<BookmanageDTO> searchService(PageDTO pv,String query,String option, String cate);
	
	//검색 결과 수
	public int countService(String query,String option, String cate);
	
	public List<BookmanageDTO> nBookService(PageDTO pv);
	public int nBookCountService();
	
	public List<BookmanageDTO> pBookService();
	public int pBookCountService();
	public String isbnTotitleService(String isbn);
}
