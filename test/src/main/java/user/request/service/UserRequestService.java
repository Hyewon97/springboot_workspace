package user.request.service;

import java.util.List;

import user.request.dto.PageDTO;
import user.request.dto.UserRequestDTO;


public interface UserRequestService {

	//신청도서 총 갯수 구하기
	public int countProcess(String user_keynum); 
	
	// 전체 신청도서목록 가져오기
	public List<UserRequestDTO> allListProcess(PageDTO pv); 
	
	// 신청 추가
	public void insertProcess(UserRequestDTO dto);

}
