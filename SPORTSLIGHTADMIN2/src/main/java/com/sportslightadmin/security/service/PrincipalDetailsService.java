package com.sportslightadmin.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sportslightadmin.adminz.dao.AdminzDAO;
import com.sportslightadmin.adminz.dto.AdminzDTO;



@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminzDAO adminzDAO;
	
	public PrincipalDetailsService() {
		
	}

	// 입력한 이메일을 가지고 와서 처리. 
	@Override
	public UserDetails loadUserByUsername(String adminEmail) throws UsernameNotFoundException {

		AdminzDTO adminEntity = adminzDAO.selectByEmail(adminEmail);
		
		if(adminEntity==null) {
			throw new UsernameNotFoundException(adminEmail);
		}
		
		// 정상처리. adminEntity 리턴
		return new PrincipalDetails(adminEntity);
	}
}















