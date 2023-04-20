package admin.bookmanage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import admin.bookmanage.dto.BookmanageDTO;
import admin.bookmanage.dto.PageDTO;

public class BookmanageDaoImp implements BookmanageDAO{
	
	private SqlSessionTemplate sqlSession;
	
	public BookmanageDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	//보유도서 총 갯수 구하기
	@Override
	public int count() {
		return sqlSession.selectOne("bookmanage.count");
	}

	// 전체 도서목록 가져오기
	@Override
	public List<BookmanageDTO> allList(PageDTO pv) {
		return sqlSession.selectList("bookmanage.allList", pv);
	}
	
	//검색 도서갯수
	@Override
	public int searchCount(String option, String searchword) {
		Map<String, String> search = new HashMap<String, String>();
		search.put("option", option);
		search.put("searchword", searchword);
		return sqlSession.selectOne("bookmanage.searchCount", search);
	}
	
	// 검색 도서목록
	@Override
	public List<BookmanageDTO> searchList(PageDTO pv) {
		return sqlSession.selectList("bookmanage.searchList", pv);
	}
	
	//도서 상세 정보 가져오기
	@Override
	public BookmanageDTO bookInfo(int num) {
		return sqlSession.selectOne("bookmanage.bookinfo", num);
	}
	
	// 도서 추가
	@Override
	public void insert(BookmanageDTO dto) {
		sqlSession.insert("bookmanage.insert", dto);	
	}

	//도서 삭제
	@Override
	public void delete(int num) {
		sqlSession.delete("bookmanage.delete", num);	
	}

 
}
