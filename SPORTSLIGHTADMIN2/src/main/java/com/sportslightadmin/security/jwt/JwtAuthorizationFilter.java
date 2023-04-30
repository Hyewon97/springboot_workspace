package com.sportslightadmin.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sportslightadmin.adminz.dao.AdminzDAO;
import com.sportslightadmin.adminz.dto.AdminzDTO;
import com.sportslightadmin.security.service.PrincipalDetails;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private AdminzDAO userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authManager, AdminzDAO userRepository) {
		super(authManager);
		this.userRepository = userRepository;
	}

	// 권한 또는 인가가 필요한 주소 요청이 있을 때 아래 필터 실행
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("인가가 필요한 주소 요청이 실행되는 메소드 : doFilterInternal()"); // 주석

		// 1. 권한이나 인증이 필요한 요청 전달
		String jwtHeader = request.getHeader("Authorization"); // Header에 들어 있는 Authorization을 호출
		System.out.println("jwtHeader :" + jwtHeader); // 주석

		// 2. Header 확인
		if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}

		// 3. JWT토큰을 검증해서 정상적인 사용자인지, 권한이 맞는지 확인
		String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
		String username = JWT.require(Algorithm.HMAC512("mySecurityCos")).build().verify(jwtToken)
				.getClaim("adminEmail").asString();
		System.out.println("username:" + username); // 주석

		// 서명이 정상적으로 처리되었으면
		if (username != null) {
			// spring security가 수행해주는 권한 처리를 위해 아래와 같이 토큰을 만들어 Authentication객체를 강제로 만들고 세션에
			// 넣어준다.
			AdminzDTO user = userRepository.selectByEmail(username);
			PrincipalDetails principalDetails = new PrincipalDetails(user);

			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,
					principalDetails.getAuthorities());

			// 강제로 시큐리티 세션에 접근해서 데이터 저장.
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}
}// end class
