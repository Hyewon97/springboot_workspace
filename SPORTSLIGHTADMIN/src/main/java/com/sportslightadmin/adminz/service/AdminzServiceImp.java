package com.sportslightadmin.adminz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportslightadmin.adminz.dao.AdminzDAO;
import com.sportslightadmin.adminz.dto.AdminzDTO;
import com.sportslightadmin.adminz.dto.AuthInfo;
import com.sportslightadmin.adminz.dto.ChangePwdCommand;
import com.sportslightadmin.common.exception.WrongEmailPasswordException;


@Service
public class AdminzServiceImp implements AdminzService{
	
	@Autowired
	private AdminzDAO AdminzDao;
	
	public AdminzServiceImp() {
		
	}

	@Override
	public AuthInfo addAdminProcess(AdminzDTO dto) {
		// 회원가입 후 바로 로그인 처리
		AdminzDao.insertAdmin(dto);
		return new AuthInfo(dto.getAdminEmail(), dto.getAdminProfile(), dto.getAdminPass());
	}

	@Override
	public AuthInfo loginProcess(AdminzDTO dto) {
		AdminzDTO admin= AdminzDao.selectByEmail(dto.getAdminEmail());
		if(admin==null){// 관리자가 아닐 경우
			 System.out.println("관리자 아님"); // 주석
			throw new WrongEmailPasswordException();
		}
		if(!admin.matchPassword(dto.getAdminPass()))
		{
			 System.out.println("비밀번호 틀림"); // 주석
			throw new WrongEmailPasswordException();
		}
		return new AuthInfo(admin.getAdminEmail(), admin.getAdminProfile(), admin.getAdminPass());
	}

	@Override 					
	public AdminzDTO updateAdminProcess(String adminEmail) {
		return AdminzDao.selectByEmail(adminEmail);
	}


	@Override
	public AuthInfo updateAdminProcess(AdminzDTO dto) {
		AdminzDao.updateAdmin (dto);
		AdminzDTO admin= AdminzDao.selectByEmail(dto.getAdminEmail());
		return new AuthInfo(admin.getAdminEmail(), admin.getAdminProfile(), admin.getAdminPass());
	}

	@Override
	public void updatePassProcess(String adminEmail, ChangePwdCommand changePwd) {
		AdminzDTO admin = AdminzDao.selectByEmail(adminEmail);
		if(admin == null)
			throw new WrongEmailPasswordException();
		
		admin.changepassword(changePwd.getCurrentPassword(), changePwd.getNewPassword());
		AdminzDao.updateByPass(admin);
		
	}

	@Override
	public void withdrawProcess(String adminEmail) {
		AdminzDao.withdrawAdmin(adminEmail);
		
	}

}














