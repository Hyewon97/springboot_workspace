package user.request.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import user.request.dto.PageDTO;
import user.request.dto.UserRequestDTO;

public class UserRequestDAOImp implements UserRequestDAO {

	private SqlSessionTemplate sqlSession;
	
	public UserRequestDAOImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	//신청도서 총 갯수 구하기
	@Override
	public int count(String user_keynum) {
		return sqlSession.selectOne("userRequest.count", user_keynum);
	}

	// 전체 신청도서목록 가져오기
	@Override
	public List<UserRequestDTO> allList(PageDTO pv) {
		return sqlSession.selectList("userRequest.allList", pv);
	}

	// 신청 추가
	@Override
	public void insert(UserRequestDTO dto) {
		sqlSession.insert("userRequest.insert", dto);
	}

}
