package com.sportslightadmin.security.jwt;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportslightadmin.adminz.dto.AdminzDTO;
import com.sportslightadmin.security.service.PrincipalDetails;

// Authentication(인증)
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;

	public JwtAuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	// http://localhost:8090/login 요청을 하면 실행되는 함수 // 주석
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFIlter => login 요청 처리를 시작함"); // 주석

		try {
			ObjectMapper om = new ObjectMapper();
			// 값을 읽어와서 저장을 함(저장 위치 User.class// 아이디, 비번, 게터 세터가 있어야 함).
			// user 사진 부분 구현임 ppt 11 부분
			AdminzDTO admin = om.readValue(request.getInputStream(), AdminzDTO.class);
			System.out.printf("관리자Email : %s 관리자Pass:%s\n", admin.getAdminEmail(), admin.getAdminPass()); // 주석

			// 토큰 생성
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					admin.getAdminEmail(), admin.getAdminPass());

			Authentication authentication = authManager.authenticate(authenticationToken);
			System.out.println("authentication : " + authentication.getPrincipal()); // 주석 토큰 확인?

			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.printf("로그인 완료됨(인증) %s %s\n", principalDetails.getUsername(), principalDetails.getPassword()); // 주석

			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// JWT 토큰 생성. 사용자에게 토근 리턴
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("successfulAutentication 실행됨"); // 주석

		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		String jwtToken = JWT.create().withSubject("mycors")
				.withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 60 * 1L))) // 만료시간 3분
				.withClaim("adminProfile", principalDetails.getAdminzDTO().getAdminProfile())// 관리자 이름
				.withClaim("authRole", principalDetails.getAdminzDTO().getAuthRole()) // 관리자 권한
				.withClaim("adminEmail", principalDetails.getAdminzDTO().getAdminEmail()) // 관리자 메일
				.sign(Algorithm.HMAC512("mySecurityCos"));

		System.out.println("jwtTOken" + jwtToken); // 주석

		// response 응답 헤더에 jwt 추가해서 보냄
		response.addHeader("Authorization", "Bearer " + jwtToken);

		final Map<String, Object> body = new HashMap<String, Object>();
		body.put("adminProfile", principalDetails.getAdminzDTO().getAdminProfile());
		body.put("adminEmail", principalDetails.getAdminzDTO().getAdminEmail());
		body.put("authRole", principalDetails.getAdminzDTO().getAuthRole());

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}

	// 인증 실패
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println("unsuccess"); // 주석
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("code", HttpStatus.UNAUTHORIZED.value());
		body.put("error", failed.getMessage());

		new ObjectMapper().writeValue(response.getOutputStream(), body);

	}

}
