package admin.bookrequest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import admin.bookmanage.dto.BookmanageDTO;
import admin.bookrequest.dto.BookRequestDTO;
import admin.bookrequest.dto.PageDTO;

public class BookRequestDaoImp implements BookRequestDAO{
	
	private SqlSessionTemplate sqlSession;
	
	public BookRequestDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	//신청도서 총 갯수 구하기
	@Override
	public int count() {
		return sqlSession.selectOne("bookrequest.count");
	}

	// 신청 도서목록 가져오기
	@Override
	public List<BookRequestDTO> requestList(PageDTO pv) {
		return sqlSession.selectList("bookrequest.requestList", pv);
	}
	
	//검색 도서갯수
	@Override
	public int searchCount(String option, String searchword) {
		Map<String, String> search = new HashMap<String, String>();
		search.put("option", option);
		search.put("searchword", searchword);
		return sqlSession.selectOne("bookrequest.searchCount", search);
	}
	
	// 검색 도서목록
	@Override
	public List<BookRequestDTO> searchList(PageDTO pv) {
		return sqlSession.selectList("bookrequest.searchList", pv);
	}
	

	// 신청도서 거절
	@Override
	public void reject(int keynum) {
		sqlSession.selectList("bookrequest.reject", keynum);
		
	}

	// 신청도서 수락
	@Override
	public void accept(int keynum) {
		sqlSession.selectList("bookrequest.accept", keynum);
		
	}
	

 
}
