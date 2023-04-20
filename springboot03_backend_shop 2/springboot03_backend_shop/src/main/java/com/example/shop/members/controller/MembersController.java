package com.example.shop.members.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.board.dto.BoardDTO;
import com.example.shop.common.file.FileUpload;
import com.example.shop.members.dto.AuthInfo;
import com.example.shop.members.dto.MembersDTO;
import com.example.shop.members.service.MembersService;

import oracle.jdbc.proxy.annotation.Post;

//@CrossOrigin(origins= {"http://localhost:3000"})
@CrossOrigin("*")

@RestController
public class MembersController {
	
	@Autowired
	private MembersService membersService;
	
	// 암호화 설정
	// BCryptPasswordEncoder를 오토 와이어를 사용하려면 어딘가에는 선언을 해야 함. 그래야 오류가 안뜬다. 
	// 환경설정에서 선언이 되어 있어야 한다.
	// 환경설정 할 수 있는 클래스를 만들고, 환경설정을 할거라고 선언을 하면 된다.
	// com.example.shop.security.config > SecurityConfig
	
	@Autowired
	private BCryptPasswordEncoder encodePassword;
	
	
	public MembersController() {
	}
	
	// 회원가입 처리
		@PostMapping("/member/signup")
		public String addMember(@RequestBody MembersDTO membersDTO) {
			
			// membersDTO에 있는 패스워드를 암호화해서 다시 membersDTO에 넣어서 서비스에 보낸다.
			membersDTO.setMemberPass(encodePassword.encode(membersDTO.getMemberPass()));
			AuthInfo authInfo = membersService.addMemberProcess(membersDTO);
			return null;
		}
		
		//회원정보 가져오기
		@GetMapping("/member/editinfo/{memberEmail}")
		public MembersDTO getMember(@PathVariable("memberEmail") String memberEmail) {
			return membersService.updateMemberProcess(memberEmail);
		}
		
		//회원정보 수정 처리
	    @PostMapping("member/update")
		public void updateMember(@RequestBody MembersDTO membersDTO) {
			membersDTO.setMemberPass(encodePassword.encode(membersDTO.getMemberPass()));
			membersService.updateMemberProcess(membersDTO);
		}

		
	
} // end class


















