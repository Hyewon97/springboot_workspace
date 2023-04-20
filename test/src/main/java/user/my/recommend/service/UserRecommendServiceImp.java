package user.my.recommend.service;

import java.util.List;

import user.my.recommend.dao.UserRecommendDAO;
import user.my.recommend.dto.BookmanageDTO;
import user.my.recommend.dto.UserRecommendDTO;

public class UserRecommendServiceImp implements UserRecommendService {
	
	private UserRecommendDAO reDAO;
	
	public UserRecommendServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setReDAO(UserRecommendDAO reDAO) {
		this.reDAO = reDAO;
	}

	//유저이름
	public UserRecommendDTO userInfoProcess(String user_keynum) {
		return reDAO.userInfo(user_keynum);
	}
	
	//유저의 반납도서 여부 
	@Override
	public int borrowCountProcess(String user_keynum) {
		return reDAO.borrowCount(user_keynum);
	}

	//마지막으로 반납한 도서 isbn, title
	@Override
	public UserRecommendDTO lastBookProcess(String user_id) {
		return reDAO.lastBook(user_id);
	}

	//추천도서에 대한 정보
	@Override
	public BookmanageDTO bookInfoProcess(String isbn, String user_keynum) {
		return reDAO.bookInfo(isbn, user_keynum);
	}

}
