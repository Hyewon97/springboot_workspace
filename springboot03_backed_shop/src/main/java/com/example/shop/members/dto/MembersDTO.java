package com.example.shop.members.dto;

import org.springframework.stereotype.Component;

import com.example.shop.common.exception.WrongEmailPasswordException;

@Component
public class MembersDTO {
	private String memberEmail; // 이메일
	private String memberPass; // 비밀번호
	private String memberName; // 이름
	private String memberPhone; // 전하번호
	private int memberType; // 회원구분 일반회원 1, 관리자 2
	private boolean rememberEmail; // 자동 로그인
	private String authRole;

	public MembersDTO() {
		
	}
	
	

	public String getAuthRole() {
		return authRole;
	}



	public void setAuthRole(String authRole) {
		this.authRole = authRole;
	}



	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPass() {
		return memberPass;
	}

	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public int getMemberType() {
		return memberType;
	}

	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}

	public boolean isRememberEmail() {
		return rememberEmail;
	}

	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}
	
	public boolean matchPassword(String memberPass) { //입력한 비밀번호
		// db에서 보내준 비밀번호				가 일치하는지, 일치하면 T, 일치하지 않으면 Fa
		return this.memberPass.equals(memberPass);
	}
	
	public void changepassword(String oldPassword, String newPassword) {
		if(!this.memberPass.equals(oldPassword)) // 테이블에서 가져온 비밀번호랑 입력한 비밀번호를 비교한다... 본인 인증 절차
			throw new WrongEmailPasswordException();
		this.memberPass=newPassword; // 새로 입력한 비밀번호를 담는다.
	}
	
}









