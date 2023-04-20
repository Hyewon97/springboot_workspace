package admin.service;

import java.util.List;

import admin.dao.AdminDAO;
import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;
import bookList.dto.BookReviewDTO;
import print.dto.PageDTO;
import user.dto.UserDTO;

//관리자 관련 서비스: 회원가입,	로그인, 정보수정, 관리자 탈퇴. DB에 정보를 insert, select, update, delete 한다.
//AuthInfo: 회원의 정보값을 세션에 저장 하기 위해 사용.
public class AdminServiceImp implements AdminService {

	private AdminDAO adminDao;

	public AdminServiceImp() {

	}

	// adminServiceBean을 생성하고 properties에서 호출.
	public void setAdminDao(AdminDAO adminDao) {
		this.adminDao = adminDao;
	}

	// 관리자 추가
	@Override
	public adminAuthInfo addAdminProcess(AdminDTO dto) {
		adminDao.addAdmin(dto);
		return new adminAuthInfo(dto.getadmin_id(), dto.getadmin_password());
	}

	// 관리자 로그인
	@Override
	public AdminDTO loginProcess(adminAuthInfo login) {
		AdminDTO admin = adminDao.selectByAdminId(login);
		System.out.println("서비스실행");

		return admin;
		// TODO Auto-generated method stub

	}


	// 관리자 탈퇴

	@Override
	public int idcheck(String adminId) {
		// TODO Auto-generated method stub
		System.out.println("서비스 실행됨");
		return adminDao.idcheck(adminId);
	}

	@Override
	public List<AdminDTO> printAdminService(PageDTO pv) {
		// TODO Auto-generated method stub
		return adminDao.printAdmin(pv);
	}

	@Override
	public List<UserDTO> printUserService(PageDTO pv) {
		// TODO Auto-generated method stub
		return adminDao.printUser(pv);
	}

	@Override
	public void deleteUserService(String id) {
		// TODO Auto-generated method stub
		System.out.println("service");
		adminDao.deleteUserData(id);
	}

	@Override
	public void deleteAdminService(String adminId) {
		// TODO Auto-generated method stub
		System.out.println("ds");
		adminDao.deleteAdminData(adminId);
	}
	
	@Override
	public int changePassService(String newpass, String admin_id) {
		// TODO Auto-generated method stub
		return adminDao.updateAdmin(newpass, admin_id);
	}
	@Override
	public int countAdminService(String query, String option) {
		// TODO Auto-generated method stub
		return adminDao.countAdmin(query, option);
	}@Override
	public int countUserService(String query, String option) {
		// TODO Auto-generated method stub
		return adminDao.countUser(query, option);
	}@Override
	public List<AdminDTO> searchAdminService(PageDTO pv, String query, String option) {
		// TODO Auto-generated method stub
		System.out.println("서비스"+query+"_"+option);
		return adminDao.searchAdmin(pv, query, option);
	}@Override
	public List<UserDTO> searchUserService(PageDTO pv, String query, String option) {
		// TODO Auto-generated method stub
		System.out.println("서비스"+query+"_"+option);
		return adminDao.searchUser(pv, query, option);
	}
	@Override
	public int countAdminAllService() {
		// TODO Auto-generated method stub
		return adminDao.countAdminAll();
	}@Override
	public int countUserAllService() {
		// TODO Auto-generated method stub
		return adminDao.countUserAll();
	}
	@Override
	public int checkstateService(String user_id) {
		// TODO Auto-generated method stub
		return adminDao.checkstate(user_id);
	}
	@Override
	public int countrevServie() {
		// TODO Auto-generated method stub
		return adminDao.countrev();
	}
	@Override
	public List<BookReviewDTO> printrevService(PageDTO pv) {
		// TODO Auto-generated method stub
		return adminDao.printrev(pv);
	}
	@Override
	public void deleteadminreviewService(int review_keynum) {
		// TODO Auto-generated method stub
		adminDao.delete(review_keynum);
	}
	@Override
	public int countrevsearchServie(String option,String query) {
		// TODO Auto-generated method stub
		return adminDao.countsearchrev(option,query);
	}
	
	
	@Override
	public List<BookReviewDTO> printrevsearchService(PageDTO pv,String option,String query) {
		// TODO Auto-generated method stub
		return adminDao.printsearchrev(pv, option, query);
	}
}
