package com.example.shop.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.shop.members.dao.MembersDAO;
import com.example.shop.members.dto.MembersDTO;

@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private MembersDAO membersDAO;
	
	public PrincipalDetailsService() {
		
	}

	// 입력한 메일을 가지고 와서 여기서 처리를 해준다. 이메일이 고유값이여서 이걸 가지고 온다.
	@Override
	public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
		//System.out.println("loadUserByUsername : " +memberEmail);
		MembersDTO userEntity = membersDAO.selectByEmail(memberEmail);
		
		if(userEntity==null) {
			throw new UsernameNotFoundException(memberEmail);
		}
		
		// 정상적으로 된다면 userEntity 값을 넘겨서 보내준다.
		return new PrincipalDetails(userEntity);
	}
}















