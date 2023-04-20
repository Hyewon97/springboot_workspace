package com.sportslightadmin.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sportslightadmin.adminz.dto.AdminzDTO;

public class PrincipalDetails implements UserDetails {

	private AdminzDTO adminzDTO;

	public PrincipalDetails() {

	}

	public PrincipalDetails(AdminzDTO adminzDTO) {
		this.adminzDTO = adminzDTO;
	}

	public AdminzDTO getAdminzDTO() {
		return adminzDTO;
	}

	// 권한 목록 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// 콜렉션으로 리턴
		Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
		collect.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return adminzDTO.getAuthRole();
			}

		});

		return collect;
	}

	@Override
	public String getPassword() {
		return adminzDTO.getAdminPass(); // 관리자 비밀번호 가져오기
	}

	@Override
	public String getUsername() {
		return adminzDTO.getAdminEmail();
	}

	// 계정만료 여부 리턴 - true(만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정의 잠김여부 리턴 - true(잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호의 잠김여부 리턴 - true(잠기지 않음)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정의 활성화 여부 리턴 - true(활성화 됨)
	@Override
	public boolean isEnabled() {
		return true;
	}

}
