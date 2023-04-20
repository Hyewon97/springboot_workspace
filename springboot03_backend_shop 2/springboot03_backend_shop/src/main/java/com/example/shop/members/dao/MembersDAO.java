package com.example.shop.members.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.shop.members.dto.MembersDTO;

@Mapper
@Repository
public interface MembersDAO {
	public int insertMember(MembersDTO dto);
	public MembersDTO selectByEmail(String memberEmail);
	
	public void updateMember(MembersDTO dto); // 회원정보 수정
	public void updateByPass(MembersDTO dto); // 비밀번호를 위해서?

}
