package com.sportslightadmin.members.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;



@Component
public class MembersDTO {
	private String memberEmail; // 사용자 이메일
	private String memberPass; // 사용자 비밀번호
	private String memberProfile; // 사용자 프로필 이름
	private Date memberReg; // 사용자 가입 일자
	private char memberLocked; // 사용자 잠금 여부
	private char memberLeave; // 사용자 탈퇴 여부
	

	public MembersDTO() {
		
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


	public String getMemberProfile() {
		return memberProfile;
	}


	public void setMemberProfile(String memberProfile) {
		this.memberProfile = memberProfile;
	}


	public Date getMemberReg() {
		return memberReg;
	}


	public void setMemberReg(Date memberReg) {
		this.memberReg = memberReg;
	}


	public char getMemberLocked() {
		return memberLocked;
	}


	public void setMemberLocked(char memberLocked) {
		this.memberLocked = memberLocked;
	}


	public char getMemberLeave() {
		return memberLeave;
	}


	public void setMemberLeave(char memberLeave) {
		this.memberLeave = memberLeave;
	}

	
	
	
}









