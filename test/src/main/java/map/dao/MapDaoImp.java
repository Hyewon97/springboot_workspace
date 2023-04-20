package map.dao;

import org.apache.ibatis.session.SqlSession;



public class MapDaoImp implements MapDAO {

	private SqlSession sqlSession;
	

	public SqlSession getSqlSession() {
		return sqlSession;
	}


	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	@Override
	public String getAddr(String user_id) {
		// TODO Auto-generated method stub
		System.out.println("dao실행");
		return sqlSession.selectOne("map.getAddress", user_id);
	}
	
}
