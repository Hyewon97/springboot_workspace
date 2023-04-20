package admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;
import bookList.dto.BookReviewDTO;
import print.dto.PageDTO;
import user.dto.UserDTO;

public class AdminDaoImp implements AdminDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int addAdmin(AdminDTO dto) {
		return sqlSession.insert("admin.addAdmin", dto);
	}

	@Override
	public AdminDTO selectByAdminId(adminAuthInfo auth) {
		return sqlSession.selectOne("admin.selectByadmin_id", auth);
	}

	@Override
	public int updateAdmin(String newpass, String admin_id) {
		// TODO Auto-generated method stub
		Map<String, String> paramMap = new HashMap<>();
        paramMap.put("admin_id", admin_id);
        paramMap.put("newpass", newpass);
		return sqlSession.update("admin.changePass",paramMap);
	}

	@Override
	public int idcheck(String adminId) {
		// TODO Auto-generated method stub
		System.out.println("dao imp 실행됨");
		return sqlSession.selectOne("admin.idcheck", adminId);
	}

	@Override
	public List<AdminDTO> printAdmin(PageDTO pv) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("admin.printAdminAll",pv);
	}

	@Override
	public List<UserDTO> printUser(PageDTO pv) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("admin.printUserAll",pv);
	}

	@Override
	public void deleteUserData(String user_id) {
		// TODO Auto-generated method stub
		sqlSession.delete("admin.deleteUser", user_id);

	}

	@Override
	public void deleteAdminData(String admin_id) {
		// TODO Auto-generated method stub
		System.out.println("dao");
		sqlSession.delete("admin.deleteAdmin", admin_id);

	}
	
	
	
	
	@Override
	public int countAdmin(String query, String option) {
		Map<String,String> paramMap = new HashMap<>();

		if(query.equals("")) {
			paramMap.put("query", null);
		}else {
			paramMap.put("query", query);
		}
		paramMap.put("option", option);
		
		
		return sqlSession.selectOne("admin.countadmin",paramMap);
	}@Override
	public int countUser(String query, String option) {
		// TODO Auto-generated method stub
		Map<String,String> paramMap = new HashMap<>();

		if(query.equals("")) {
			paramMap.put("query", null);
		}else {
			paramMap.put("query", query);
		}
		paramMap.put("option", option);
		
		
		return sqlSession.selectOne("admin.countuser",paramMap);
	}
	@Override
	public List<AdminDTO> searchAdmin(PageDTO pv, String query, String option) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("page", pv);
		if(query.equals("")) {
			paramMap.put("query", null);
		}else {
			paramMap.put("query", query);
		}
		paramMap.put("option", option);
		System.out.println("dao"+query+"_"+option);
		
		return sqlSession.selectList("admin.searchadmin",paramMap);
		
	}
	@Override
	public List<UserDTO> searchUser(PageDTO pv, String query, String option) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("page", pv);
		if(query.equals("")) {
			paramMap.put("query", null);
		}else {
			paramMap.put("query", query);
		}
		paramMap.put("option", option);
		System.out.println("dao"+query+"_"+option);
		
		return sqlSession.selectList("admin.searchuser",paramMap);
	}
	@Override
	public int countAdminAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.countAdminAll");
	}
	@Override
	public int countUserAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.countUserAll");
	}
	
	@Override
	public int checkstate(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("checkstate",user_id);
	}
	@Override
	public int countrev() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.countrev");
	}
	
	@Override
	public List<BookReviewDTO> printrev(PageDTO pv) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("admin.printRev",pv);
	}
	
	@Override
	public void delete(int review_keynum) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("admin.deleteadminReview",review_keynum);
	}
	
	@Override
	public int countsearchrev(String option,String query) {
		// TODO Auto-generated method stub
		Map<String, String> mapa=new HashMap<String, String>();
		mapa.put("option", option);
		
		if(query.equals(""))
		{
			mapa.put("query","");
		}else{
			mapa.put("query", query);
		}
		
		return sqlSession.selectOne("admin.SearchReviewCount",mapa);
	}
	
	@Override
	public List<BookReviewDTO> printsearchrev(PageDTO pv,String option,String query) {
		// TODO Auto-generated method stub
		
		Map<String, Object> mapa=new HashMap<String, Object>();
		mapa.put("pv",pv);
		mapa.put("option", option);
		
		if(query.equals(""))
		{
		mapa.put("query","");
		}else{
			mapa.put("query", query);
		}
		
		return sqlSession.selectList("admin.SearchReview",mapa);

	}
	

}
