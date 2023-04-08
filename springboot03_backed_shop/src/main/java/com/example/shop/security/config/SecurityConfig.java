package com.example.shop.security.config;

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

import com.example.shop.members.dao.MembersDAO;
import com.example.shop.security.jwt.JwtAuthenticationFilter;
import com.example.shop.security.jwt.JwtAuthorizationFilter;
import com.example.shop.security.repository.UserRepository;
import com.example.shop.security.service.CorsConfig;

@Configuration
@EnableWebSecurity // SpringSecurityFilterChain에 등록
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private MembersDAO userRepository;

	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private CorsConfig corsConfig;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
/////////////////////////////////////////////////////////////////////////////////// 한쌍
		// 사이트가 이조? 요청을 처리 하지 않겠다? 이동인가요?
		// csrf() : Cross Site Request Forgery로 사이트간 위조 요청으로 정상적인 사용자가 의도치 않은
		// 위조 요청을 보내는 것을 의미한다.
		http.csrf().disable();

		// 기본적으로 로그인폼이 제공되는데, 기본 로그인 폼을 사용하지 않고 자체 로그인폼을 사용할거니까
		// API을 사용하므로 기본으로 제공하는 formLogin() 페이지 끄기
		http.formLogin().disable();

		// chain 형식이 기본적으로 filter로 되어 있는데, 필터는 컨트롤 페이지가 실행되기 전에 실행되어야 한다.
		// 포스트 방식으로 요청하는 것에서 인코딩 작업을 컨트롤에서 안했었음 (필터에서 적용이 되어서)
		// 필터를 사용하려면 필터를 받아서 입맛에 받게 수정할 수 있음
		// httpBasic 방식 대신 JWT를 사용하기 때문에 httpBasic() 끄기
		http.httpBasic().disable();
///////////////////////////////////////////////////////////////////////////////////// 한쌍 끝
		// 세션끄기 : JWT를 사용하기 때문에 세션을 사용하지 않는다.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// 인증사용, Security Filter에 등록, @crossOrigin (인증x)
		http.apply(new MyCustomerFilter());
		
		// 요청에 의한 인가(권한) 검사 시작
//		http.authorizeHttpRequests()
//		.antMatchers("/","images/**","/login","/signup",
//				"board/list/**").permitAll()//로그인 없이 접근 허용한다.
//		.anyRequest().authenticated(); // 그외 모든 요청에 대해서 인증(로그인)이 되어야 허용한다.
		
		// 지후 언니 코드
		 //요청에 의한 인가(권한) 검사 시작
        http.authorizeHttpRequests()
        .antMatchers("/", "/images/**", "/login","/member/signup", 
                   "/board/list/**").permitAll() //로그인 없이 접근 허용한다.
        .anyRequest().authenticated(); //그 외 모든 요청에 대해서 인증(로그인)이 되어야 허용한다.
		
		return http.build();
	}
	
	public class MyCustomerFilter extends AbstractHttpConfigurer<MyCustomerFilter, HttpSecurity>{
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
			
			// @CrossOrigin(인증 x), Security Filter에 등록 인증(ㅇ)
			// 설정 제대로 안하면 200번 에러가 발생한다.
			http.addFilter(corsConfig.corsFilter());
			
			// addFilter() : FilterComparator에 등록되어 있는 Filter들을 활성화할 떄 사용
			// addFilterBefore(), addFilterAfter() : CustomFilter를 등록할 때 사용
			http.addFilter(new JwtAuthenticationFilter(authenticationManager))
			// 인가(권한) 필터 등록
				.addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository ));
		}
	}

} // end class









