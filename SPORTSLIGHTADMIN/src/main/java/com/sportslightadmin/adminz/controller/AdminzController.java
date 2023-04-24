package com.sportslightadmin.adminz.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportslightadmin.adminz.dto.AdminzDTO;
import com.sportslightadmin.adminz.dto.AuthInfo;
import com.sportslightadmin.adminz.service.AdminzService;

import oracle.jdbc.proxy.annotation.Post;

@CrossOrigin("*")

@RestController
public class AdminzController {

	@Autowired
	private AdminzService adminzService;

	// 암호화 설정
	// BCryptPasswordEncoder를 오토 와이어를 사용하려면 어딘가에는 선언을 해야 함. 그래야 오류가 안뜬다.
	// 환경설정에서 선언이 되어 있어야 한다.
	// 환경설정 할 수 있는 클래스를 만들고, 환경설정을 할거라고 선언을 하면 된다.
	// com.example.shop.security.config > SecurityConfig
	@Autowired
	private BCryptPasswordEncoder encodePassword;

	public AdminzController() {
	}

	// 관리자 회원가입 처리
	@PostMapping("/admin/signup")
	public String addAdmin(@RequestBody AdminzDTO adminzDTO) {

		// adminzDTO에 있는 암호를 암호화해서 다시 adminzDTO에 넣어 서비스에 보냄
		adminzDTO.setAdminPass(encodePassword.encode(adminzDTO.getAdminPass()));
		AuthInfo authInfo = adminzService.addAdminProcess(adminzDTO);
		return null;
	}

	// 관리자 정보 가져오기
	@GetMapping("/admin/update/{adminEmail}")
	public AdminzDTO getAdmin(@PathVariable("adminEmail") String adminEmail) {
		return adminzService.updateAdminProcess(adminEmail);
	}

	// 관리자 정보 수정
	@PostMapping("/admin/update")
	public void updateAdmin(@RequestBody AdminzDTO adminzDTO) {
		adminzDTO.setAdminPass(encodePassword.encode(adminzDTO.getAdminPass()));
		adminzService.updateAdminProcess(adminzDTO);
	}
	
	// 관리자 탈퇴
	@DeleteMapping("/admin/withdraw/{adminEmail}")
	public void withdrawExecute(@PathVariable("adminEmail") String adminEmail) {
		adminzService.withdrawProcess(adminEmail);
	}

}
// end class











