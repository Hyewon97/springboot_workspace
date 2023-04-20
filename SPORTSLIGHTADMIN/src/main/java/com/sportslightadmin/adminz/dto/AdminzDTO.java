package com.sportslightadmin.adminz.dto;

import org.springframework.stereotype.Component;

import com.sportslightadmin.common.exception.WrongEmailPasswordException;

@Component
public class AdminzDTO {
	private String adminEmail; // 관리자 이메일
	private String adminPass; // 관리자 비밀번호
	private String adminProfile; // 관리자 프로필 닉네임
	private String authRole;

	public AdminzDTO() {

	}

	public String getAuthRole() {
		return authRole;
	}

	public void setAuthRole(String authRole) {
		this.authRole = authRole;
	}

	// 게터, 세터
	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	

	public String getAdminProfile() {
		return adminProfile;
	}

	public void setAdminProfile(String adminProfile) {
		this.adminProfile = adminProfile;
	}

	// 비밀번호 일치하는지 확인
	public boolean matchPassword(String adminPass) { // 입력한 비밀번호
		// db에서 보내준 비밀번호 가 일치하는지, 일치하면 T, 일치하지 않으면 Fa
		return this.adminPass.equals(adminPass);
	}

	public void changepassword(String oldPassword, String newPassword) {
		if (!this.adminPass.equals(oldPassword)) // 테이블에서 가져온 비밀번호랑 입력한 비밀번호를 비교하기
			throw new WrongEmailPasswordException();
		this.adminPass = newPassword; // 새로 입력한 비밀번호 담기
	}

}
