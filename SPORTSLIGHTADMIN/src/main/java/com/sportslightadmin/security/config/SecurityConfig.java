package com.sportslightadmin.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sportslightadmin.adminz.dao.AdminzDAO;
import com.sportslightadmin.security.jwt.JwtAuthenticationFilter;
import com.sportslightadmin.security.jwt.JwtAuthorizationFilter;
import com.sportslightadmin.security.repository.UserRepository;
import com.sportslightadmin.security.service.CorsConfig;

@Configuration
@EnableWebSecurity // SpringSecurityFilterChain에 등록
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

	@Autowired
	private AdminzDAO userRepository;

	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CorsConfig corsConfig;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 위조 요청 보내기
		http.csrf().disable();

		// 기본 제공되는 forLogin() 페이지 비활성화
		http.formLogin().disable();

		// JWT 사용하기 위해 httpBasic 비활성화
		http.httpBasic().disable();

		// 세션 비활성화
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// 인증사용, Security Filter에 등록
		http.apply(new MyCustomerFilter());

		// 요청에 의한 인가 검사 시작
		http.authorizeHttpRequests().antMatchers("/", "/images/**", "/login", "/admin/signup", "/board/list/**")
				.permitAll() // 로그인 없이 접근 허용
				.anyRequest().authenticated(); // 그 외 모든 요청에 대해서 인증(로그인)이 되어야 허용

		return http.build();
	}

	public class MyCustomerFilter extends AbstractHttpConfigurer<MyCustomerFilter, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

			// @CrossOrigin(인증 x), Security Filter에 등록 인증(ㅇ)
			http.addFilter(corsConfig.corsFilter());

			// addFilter() : FilterComparator에 등록되어 있는 Filter들을 활성화할 떄 사용
			// addFilterBefore(), addFilterAfter() : CustomFilter를 등록할 때 사용
			http.addFilter(new JwtAuthenticationFilter(authenticationManager))
					// 인가(권한) 필터 등록
					.addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
		}
	}

} // end class
