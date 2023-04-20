package admin.borrow.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import admin.borrow.dto.BorrowDTO;
import print.dto.PageDTO;

public class BorrowDaoImp implements BorrowDAO {
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BorrowDTO> printBorrow(PageDTO pv) {
		// TODO Auto-generated method stub
		System.out.println(sqlSession.selectList("printBorrow", pv));
		return sqlSession.selectList("printBorrow", pv);
	}

	@Override
	public int countBorrow() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("countBorrow");
	}

	@Override
	public int countSearchBorrow(String option, String query) {
		// TODO Auto-generated method stub
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("option", option);
		if (query.equals("")) {
			mapa.put("query", null);
		} else {
			mapa.put("query", query);
		}

		return sqlSession.selectOne("countsearchBorrow", mapa);
	}

	@Override
	public List<BorrowDTO> printSearchBorrow(PageDTO pv, String option, String query) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("pv", pv);
		mapa.put("option", option);
		if (query.equals("")) {
			mapa.put("query", null);
		} else {
			mapa.put("query", query);
		}

		return sqlSession.selectList("printSearchBorrow",mapa);
	}
}
