package user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import admin.bookmanage.dto.BookmanageDTO;
import user.dto.AuthInfo;
import user.dto.UserDTO;

public class UserDaoImp implements UserDAO {
	private SqlSession sqlSession;

	public UserDaoImp() {
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertUser(UserDTO dto) {
		return sqlSession.insert("user.addUser", dto);
	}

	@Override
	public UserDTO selectByUserId(AuthInfo authinfo) {
		return sqlSession.selectOne("user.selectByUserId", authinfo);
	}

	@Override
	public int idcheck(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.idcheck", user_id);
	}

	@Override
	public UserDTO selectByUserIdString(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.selectByUserIdString", user_id);
	}

	@Override
	public String getKeynum(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.getKeynum", user_id);
	}

	// 주소 수정
	@Override
	public void updateAddress(UserDTO dto) {
		sqlSession.update("user.updateAddress", dto);

	}

	// 비밀번호 수정
	@Override
	public void updatePass(UserDTO dto) {
		sqlSession.update("user.updatePassword", dto);

	}

	// 유저 삭제
    @Override
    public void deleteUser(String user_keynum) {
      sqlSession.update("user.deleteUser", user_keynum);
    }

    // 
	@Override
	public int checkBook(String user_id) {
		
	return sqlSession.selectOne("user.checkBook",user_id);
	}
    @Override
    public List<BookmanageDTO> nbookprint() {
    	// TODO Auto-generated method stub
    	return sqlSession.selectList("user.nbookprint");
    }@Override
    public List<BookmanageDTO> pbookprint() {
    	// TODO Auto-generated method stub
    	return sqlSession.selectList("user.pbookprint");
    }
    
	@Override
	public String getTagValue(String tag, Element eElement) {
		// TODO Auto-generated method stub
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		if (nValue == null)
			return null;

		return nValue.getNodeValue();
	}

}
