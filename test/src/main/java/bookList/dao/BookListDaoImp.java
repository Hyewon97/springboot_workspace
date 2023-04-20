package bookList.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import bookList.dto.BookListDTO;
import bookList.dto.BookReviewDTO;
import bookList.dto.PageDTO;

public class BookListDaoImp implements BookListDAO{
	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public BookListDTO selectBook(String isbn) {
		return sqlSession.selectOne("bookList.isbn", isbn);
	}
	
	// 총 후기 수
	@Override
	public int count(String isbn) {
		return sqlSession.selectOne("bookList.count",isbn);
	}

	@Override
	public int revCheck(String keynum, String book_keynum) {
		Map<String, String> dto = new HashMap<String, String>();
		dto.put("keynum", keynum);
		dto.put("book_keynum",book_keynum);
		
		return sqlSession.selectOne("bookList.revCheck", dto);
	}
	
	// 후기 출력
	@Override
	public List<BookReviewDTO> reviewList(PageDTO pv,String isbn) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("isbn", isbn);
		mapa.put("pv", pv);
		return sqlSession.selectList("bookList.reviewList", mapa);
	}

	// 후기 저장
	@Override
	public void save(String keynum,BookReviewDTO dto) {
		Map<String, Object> mapa= new HashMap<String, Object>();
		mapa.put("keynum",keynum);
		mapa.put("dto",dto);
		sqlSession.insert("bookList.addReview", mapa);
	}

	// 후기 수정
	@Override
	public void update(BookReviewDTO dto) {
		sqlSession.selectList("bookList.updateReview", dto);
	}

	// 후기 삭제
	@Override
	public void delete(int num) {
		sqlSession.delete("bookList.deleteReview", num);
	}
	@Override
	public String getKeynum(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("bookList.getKeynum",id);
	}
	@Override
	public String getId(String keynum) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("bookList.getId",keynum);
	}
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("bookList.count");
	}
	


}
