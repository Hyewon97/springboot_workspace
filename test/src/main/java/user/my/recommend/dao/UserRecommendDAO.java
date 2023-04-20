package user.my.recommend.dao;

import user.my.recommend.dto.BookmanageDTO;
import user.my.recommend.dto.UserRecommendDTO;

public interface UserRecommendDAO {

	//유저이름, 성별, 연령
	public UserRecommendDTO userInfo(String user_keynum);
	
	//유저의 반납도서 여부 
	public int borrowCount(String user_keynum);
	
	//마지막으로 반납한 도서
	public UserRecommendDTO lastBook(String user_id);	
	
	//추천도서에 대한 정보
	public BookmanageDTO bookInfo(String isbn, String user_keynum);	
}
