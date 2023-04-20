package user.dao;

import java.util.List;

import org.w3c.dom.Element;

import admin.bookmanage.dto.BookmanageDTO;
import user.dto.AuthInfo;
import user.dto.UserDTO;

public interface UserDAO {

	// 유저 추가
	public int insertUser(UserDTO dto);

	// 유저 ID별로 선택
	public UserDTO selectByUserId(AuthInfo authInfo);

	// 중복확인
	public int idcheck(String user_id);

	public UserDTO selectByUserIdString(String user_id);

	public String getKeynum(String id);

	// 주소 수정
	public void updateAddress(UserDTO dto);

	// 비밀번호 수정
	public void updatePass(UserDTO dto);

	// 유저 삭제
	public int checkBook(String user_id);
	
	public void deleteUser(String user_keynum);
	
	
	public List<BookmanageDTO> pbookprint();
	public List<BookmanageDTO> nbookprint();
	
	// 도서마루 추천 api
	public String getTagValue(String tag, Element eElement);

}
