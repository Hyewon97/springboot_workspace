package user.request.dao;

import java.util.List;

import user.request.dto.PageDTO;
import user.request.dto.UserRequestDTO;

public interface UserRequestDAO {

		//신청도서 총 갯수 구하기
		public int count(String user_keynum);
		
		// 전체 신청도서목록 가져오기
		public List<UserRequestDTO> allList(PageDTO pv);

		// 신청 추가
		public void insert(UserRequestDTO dto);

}
