package print.dao;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import print.dto.PageDTO;

public interface PrintDAO {

	// 전체출력
	public List<BookmanageDTO> printAll(PageDTO pv);

	// 전체 수 조회
	public int countAll();
	
	//검색
	public List<BookmanageDTO> search(PageDTO pv, String query, String option, String cate);
 
	//검색어에 따른 결과 수 확인
	public int count(String query, String option, String cate);
	
	public List<BookmanageDTO> nBook(PageDTO pv);
	public int nBookCount();
	
	public List<BookmanageDTO> pBook();
	public int pBookCount();
	public String isbnToTitle(String isbn);
}
