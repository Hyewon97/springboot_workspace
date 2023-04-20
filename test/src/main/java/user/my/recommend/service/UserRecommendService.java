package user.my.recommend.service;

import user.my.recommend.dto.BookmanageDTO;
import user.my.recommend.dto.UserRecommendDTO;

public interface UserRecommendService {

	//유저이름 가져오기
	public UserRecommendDTO userInfoProcess(String user_keynum); 
	
	//유저의 반납도서 여부 
	public int borrowCountProcess(String user_keynum); 
	
	//마지막 도서 isbn, 제목 가져오기
	public UserRecommendDTO lastBookProcess(String user_keynum); 
	
	//마지막 도서 isbn, 제목 가져오기
	public BookmanageDTO bookInfoProcess(String isbn, String user_keynum); 
}
