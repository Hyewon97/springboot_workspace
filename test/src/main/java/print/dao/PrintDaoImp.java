package print.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import admin.bookmanage.dto.BookmanageDTO;
import print.dto.PageDTO;

public class PrintDaoImp implements PrintDAO {
	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BookmanageDTO> printAll(PageDTO pv) {
		// TODO Auto-generated method stub
		System.out.println("dao 실행");
		return sqlSession.selectList("print.printbookAll", pv);
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("print.count");
	}



	@Override
	public List<BookmanageDTO> search(PageDTO pv, String query, String option, String cate) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("page", pv);
		if(query.equals("")) {
			paramMap.put("query", null);
		}else {
			paramMap.put("query", query);
		}
		
		paramMap.put("option", option);
		if(cate.equals("a")) {
			paramMap.put("cate", null);
		}else {
			paramMap.put("cate", cate);
		}

		System.out.println(paramMap.get("option"));
		System.out.println(paramMap.get("query"));
		System.out.println(paramMap.get("cate"));
		
		return sqlSession.selectList("print.search",paramMap);
	}
	@Override
	public int count(String query, String option, String cate) {
		Map<String,String> paramMap = new HashMap<>();

		if(query.equals("")) {
			paramMap.put("query", null);
		}else {
			paramMap.put("query", query);
		}
		
		paramMap.put("option", option);
		if(cate.equals("a")) {
			paramMap.put("cate", null);
		}else {
			paramMap.put("cate", cate);
		}
	
		System.out.println(paramMap.get("option"));
		System.out.println(paramMap.get("query"));
		System.out.println(paramMap.get("cate"));
		
		return sqlSession.selectOne("print.searchcount",paramMap);
	}
	
	@Override
	public List<BookmanageDTO> nBook(PageDTO pv) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("print.nBook",pv);
	}
	
	@Override
	public int nBookCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("print.nBookCount");
	}
	@Override
	public List<BookmanageDTO> pBook() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("print.pBook");
	}
	@Override
	public int pBookCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("print.pBookCount");
	}
	@Override
	public String isbnToTitle(String isbn) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("print.searchByIsbn",isbn);
	}
	
}
