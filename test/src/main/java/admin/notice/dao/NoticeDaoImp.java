package admin.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import admin.bookmanage.dto.BookmanageDTO;
import admin.notice.dto.NoticeDTO;
import admin.notice.dto.PageDTO;

public class NoticeDaoImp implements NoticeDAO{
	
	private SqlSessionTemplate sqlSession;
	
	public NoticeDaoImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	//게시글 목록
	@Override
	public int count() {
		return sqlSession.selectOne("adminnotice.count");
	}

	@Override
	public List<NoticeDTO> list(PageDTO pv) {
		return sqlSession.selectList("adminnotice.list", pv);
	}
	
	//게시글 읽기
	@Override
	public NoticeDTO content(int num) {
		return sqlSession.selectOne("adminnotice.content", num);
	}

	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("adminnotice.getFile", num);
	}
	
	@Override
	public String getImg(int num) {
		return sqlSession.selectOne("adminnotice.getImg", num);
	}
	
	//검색 도서갯수
	@Override
	public int searchCount(String option, String searchword) {
		Map<String, String> search = new HashMap<String, String>();
		search.put("option", option);
		search.put("searchword", searchword);
		return sqlSession.selectOne("adminnotice.searchCount", search);
	}
	
	// 검색 도서목록
	@Override
	public List<NoticeDTO> searchList(PageDTO pv) {
		return sqlSession.selectList("adminnotice.searchList", pv);
	}


	// 게시글 작성
	@Override
	public void save(NoticeDTO dto) {
		sqlSession.insert("adminnotice.save", dto);
		
	}

	//게시글 수정
	@Override
	public void update(NoticeDTO dto) {
		sqlSession.update("adminnotice.update", dto);
		
	}

	//게시글 삭제
	@Override
	public void delete(int num) {
		sqlSession.delete("adminnotice.delete", num);
		
	}

 
}
