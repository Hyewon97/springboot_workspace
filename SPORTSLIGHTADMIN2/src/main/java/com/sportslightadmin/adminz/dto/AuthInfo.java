package com.sportslightadmin.adminz.dto;

// 로그인 성공 후 인증 상태 정보를 세션에 보관할 때 사용
public class AuthInfo {
	
	private String adminEmail;
	private String adminProfile;
	private String adminPass;
	
	public AuthInfo() {
		
	}

	
	public AuthInfo(String adminEmail, String adminPass) {
		super();
		this.adminEmail = adminEmail;
		this.adminPass = adminPass;
	}
	
	public AuthInfo(String adminEmail, String adminProfile, String adminPass) {
		super();
		this.adminEmail = adminEmail;
		this.adminProfile = adminProfile;
		this.adminPass = adminPass;
	}


	public String getAdminEmail() {
		return adminEmail;
	}


	public String getAdminProfile() {
		return adminProfile;
	}


	public String getAdminPass() {
		return adminPass;
	}
	
	

}
