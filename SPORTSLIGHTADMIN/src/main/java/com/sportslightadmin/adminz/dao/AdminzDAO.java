package com.sportslightadmin.adminz.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sportslightadmin.adminz.dto.AdminzDTO;

@Mapper
@Repository
public interface AdminzDAO {
	public int insertAdmin(AdminzDTO dto); // 관리자 회원가입
	public AdminzDTO selectByEmail(String adminEmail); // 관리자 선택
	
	public void updateAdmin(AdminzDTO dto); // 관리자 정보 수정
	public void updateByPass(AdminzDTO dto); // 관리자 비밀번호 수정
	public void withdrawAdmin(String adminEmail); // 관리자 회원탈퇴

}
