package user.service;

import java.util.List;

import admin.bookmanage.dto.BookmanageDTO;
import user.dto.AuthInfo;
import user.dto.UserDTO;

public interface UserService {

//	회원가입(유저 추가)
	public AuthInfo addUserProcess(UserDTO dto);

//	로그인 처리
	public UserDTO loginProcess(AuthInfo authinfo);

//	DB에서 유저 정보 수정
	public UserDTO updateUserProcess(String user_id);

//	업데이트된 사용자 정보를 현재 세션에 적용
	public AuthInfo updateUserProcess(UserDTO dto);

	// 중복확인
	public int idcheck(String user_id);

	// 아이디로 검색

	public UserDTO selectUserProcess(String user_id);

	//
	public String getKeynum(String id);

	// 주소 수정
	public AuthInfo updateAddressProcess(UserDTO dto);

	// 비밀번호 변경
	public AuthInfo updatePassProcess(UserDTO dto);

	// 유저 삭제
	public int checkBookProcess(String user_id);

	public void deleteUserProcess(String user_keynum);

	public List<BookmanageDTO> pbookprintService();

	public List<BookmanageDTO> nbookprintService();
	// 도서마루 추천 api

	public List<BookmanageDTO> marurecom(String paramAge, String gender);
}
