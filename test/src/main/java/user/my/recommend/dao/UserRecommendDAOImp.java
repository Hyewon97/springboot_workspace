package user.my.recommend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import user.my.recommend.dto.BookmanageDTO;
import user.my.recommend.dto.UserRecommendDTO;

public class UserRecommendDAOImp implements UserRecommendDAO {

	private SqlSessionTemplate sqlSession;
	
	public UserRecommendDAOImp() {
		// TODO Auto-generated constructor stub
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//유저이름
	@Override
	public UserRecommendDTO userInfo(String user_keynum) {
		return sqlSession.selectOne("userRecommend.userInfo", user_keynum);
	}
	
	//유저의 반납도서 여부 
	@Override
	public int borrowCount(String user_keynum) {
		return sqlSession.selectOne("userRecommend.borrowCount", user_keynum);
	}
	
	//마지막으로 반납한 도서
	@Override
	public UserRecommendDTO lastBook(String user_id) {
		return sqlSession.selectOne("userRecommend.lastBook", user_id);
	}

	//추천도서에 대한 정보
	@Override
	public BookmanageDTO bookInfo(String isbn, String user_keynum) {
		Map<String, String> bookinfo = new HashMap<String, String>();
		bookinfo.put("isbn", isbn);
		bookinfo.put("user_keynum", user_keynum);
		return sqlSession.selectOne("userRecommend.bookInfo", bookinfo);
	}



}
