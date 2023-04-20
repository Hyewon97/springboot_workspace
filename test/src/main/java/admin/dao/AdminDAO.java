package admin.dao;

import java.util.List;

import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;
import bookList.dto.BookListDTO;
import bookList.dto.BookReviewDTO;
import print.dto.PageDTO;
import user.dto.UserDTO;



public interface AdminDAO {
	
	// 관리자 추가
	public int addAdmin(AdminDTO dto);
	
	// 로그인
	public AdminDTO selectByAdminId(adminAuthInfo auth);
	
	//관리자 수정(비밀번호 변경)
	public int updateAdmin(String newpass, String admin_id);
	
	
    //아이디 중복체크
	public int idcheck(String adminId);
	
	//관리자 프린트 전체 출력
	public List<AdminDTO> printAdmin(PageDTO pv);
	
	//사용자 프린트 전체 출력
	public List<UserDTO> printUser(PageDTO pv);
	
	//유저 삭제
	public void deleteUserData(String user_id);
	
	//관리자 탈퇴 
	public void deleteAdminData(String admin_id);
	
	//유저검색  pv 는 페이지네이션을 위한 것이고, query와 option은 where 문과 if 태그에  사용
	public List<UserDTO> searchUser(PageDTO pv, String query, String option);
	
	//관리자검색  pv 는 페이지네이션을 위한 것이고, query와 option은 where 문과 if 태그에  사용
	public List<AdminDTO> searchAdmin(PageDTO pv, String query, String option);
	
	//검색요청에 일치하치하는 사용자수 pv 값 채우기 위해
	public int countUser(String query, String option);
	
	//검색요청에 일치하치하는 관리자수 pv 값 채우기 위해
	public int countAdmin(String query, String option);
	
	//pv값을 채우기 위한 사용자 전체수
	public int countUserAll();
	
	//pv값을 채우기 위한 관리자 전체수
	public int countAdminAll();
	
	public int checkstate(String user_id);
	
	
	public int countrev();
	public List<BookReviewDTO> printrev(PageDTO pv);
	public void delete(int review_keynum);
	
	
	
	
	
	public int countsearchrev(String option,String query);
	public List<BookReviewDTO> printsearchrev(PageDTO pv,String option,String query);
}
