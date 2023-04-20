package admin.service;

import java.util.List;

import admin.dao.AdminDAO;
import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;
import bookList.dto.BookReviewDTO;
import print.dto.PageDTO;
import user.dto.UserDTO;

public interface AdminService {
	//	회원가입(관리자 추가)
	public adminAuthInfo addAdminProcess(AdminDTO dto);
	
	//	로그인 처리
	public AdminDTO loginProcess(adminAuthInfo dto);
	
	// 중복확인
	public int idcheck(String adminId);
	
	// 관리자 전체 출력
	public List<AdminDTO> printAdminService(PageDTO pv);
	
	// 사용자 전체 출력
	public List<UserDTO> printUserService(PageDTO pv);
	
	// 사용자 삭제
	public void deleteUserService(String id);
	
	// 관리자 탈퇴
	public void deleteAdminService(String adminId);
	
	// 검색요청에 따른 결과 수 
	public int countUserService(String query, String option);
	
	// 검색요청에 따른 결과 수
	public int countAdminService(String query, String option);
	
	//검색 요청에 일치하는 관리자 출력
	public List<AdminDTO> searchAdminService(PageDTO pv, String query,String option);
	
	//검색 요청에 일치하는 사용자
	public List<UserDTO> searchUserService(PageDTO pv, String query,String option);
	
	//비밀번호 변경
	public int changePassService(String newpass,String admin_id);
	
	//전체 사용자수
	public int countUserAllService();
	
	//전체 관리자수
	public int countAdminAllService();
	
	
	public int checkstateService(String user_id);
	
	
	public int countrevServie();
	public List<BookReviewDTO> printrevService(PageDTO pv);
	public void deleteadminreviewService(int review_keynum);
	
	
	public int countrevsearchServie(String option,String query);
	public List<BookReviewDTO> printrevsearchService(PageDTO pv,String option,String query);
	
}
