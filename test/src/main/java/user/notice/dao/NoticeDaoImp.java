package user.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import user.notice.dto.NoticeDTO;
import user.notice.dto.PageDTO;

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
		return sqlSession.selectOne("usernotice.count");
	}

	@Override
	public List<NoticeDTO> list(PageDTO pv) {
		return sqlSession.selectList("usernotice.list", pv);
	}
	
	@Override
	public List<NoticeDTO> toplist() {
		return sqlSession.selectList("usernotice.toplist");

	}
	
	//검색 도서갯수
	@Override
	public int searchCount(String option, String searchword) {
		Map<String, String> search = new HashMap<String, String>();
		search.put("option", option);
		search.put("searchword", searchword);
		return sqlSession.selectOne("usernotice.searchCount", search);
	}
	
	// 검색 도서목록
	@Override
	public List<NoticeDTO> searchList(PageDTO pv) {
		return sqlSession.selectList("usernotice.searchList", pv);
	}
	
	//게시글 읽기
	@Override
	public NoticeDTO content(int num) {
		return sqlSession.selectOne("usernotice.content", num);
	}
	
	@Override
	public void readCount(int num) {
		sqlSession.update("usernotice.readCount", num);		
	}

	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("usernotice.getFile", num);
	}
	
	@Override
	public String getImg(int num) {
		return sqlSession.selectOne("usernotice.getImg", num);
	}
	
	@Override
	public NoticeDTO preNext(int num) {
		return sqlSession.selectOne("usernotice.preNext", num);
	}
	
	//main페이지test (최근 고정글 1개)
	@Override
	public NoticeDTO latestOne() {
		return sqlSession.selectOne("usernotice.latestOne");
	}
	//main페이지test (최근 게시글 5개)
	@Override
	public List<NoticeDTO> latestfive() {
		return sqlSession.selectList("usernotice.latestFive");
	}

	
 
}
