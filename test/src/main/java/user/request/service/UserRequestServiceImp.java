package user.request.service;

import java.util.List;

import user.request.dao.UserRequestDAO;
import user.request.dto.PageDTO;
import user.request.dto.UserRequestDTO;

public class UserRequestServiceImp implements UserRequestService{
	
	private UserRequestDAO userRequestDao;
	
	public UserRequestServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setUserRequestDao(UserRequestDAO userRequestDao) {
		this.userRequestDao = userRequestDao;
	}

	@Override
	public int countProcess(String user_keynum) {
		return userRequestDao.count(user_keynum);
	}

	@Override
	public List<UserRequestDTO> allListProcess(PageDTO pv) {
		return userRequestDao.allList(pv);
	}

	@Override
	public void insertProcess(UserRequestDTO dto) {
		userRequestDao.insert(dto);
	}

}
