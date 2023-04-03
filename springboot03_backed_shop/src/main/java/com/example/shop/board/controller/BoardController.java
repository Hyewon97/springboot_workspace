package com.example.shop.board.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shop.board.dto.BoardDTO;
import com.example.shop.board.dto.PageDTO;
import com.example.shop.board.service.BoardService;
import com.example.shop.common.file.FileUpload;

@RestController
public class BoardController {
	
	@Autowired 	// 기존에는 세터를 해줬는데 @Autowired를 하면 자동으로 해줘서 안써줘도 됨
	private BoardService boardService;
	
	// 스프링 프레임워크 밸류
	@Value("${spring.servlet.multipart.location}")  // 첨부파일을 이 위치에 저장하겠다.... c 드라이브 안에 download>temp 폴더 위치
	private String filePath;
	
	@Autowired
	private PageDTO pdto;
	
	private int currentPage;
	
	public BoardController() {
	}
	
	
	// http://localhost:8090/board/list/1
	
	@GetMapping("/board/list/{currentPage}")                                          // 페이지를 호출할 때, 
	public Map<String, Object> listExecute(@PathVariable("currentPage") int currentPage, PageDTO pv) {
		Map<String, Object> map = new HashMap<>(); // 뭐가 같으면? 생성할 때 안해줘도 된다.
		int totalRecord = boardService.countProcess();
		if (totalRecord >= 1) { // 이 조건에 만족하는 애가 있으면 페이징 처리를 해야한다.
//			if (pv.getCurrentPage() == 0)
				this.currentPage=currentPage; // currenPage에 1이라는 값을 넣어준다.
//			else
//				this.currentPage = pv.getCurrentPage();
			
			this.pdto = new PageDTO(this.currentPage, totalRecord);
			
			map.put("aList", boardService.listProcess(this.pdto));
			map.put("pv", this.pdto);

		}
		return map;
	} // end listExecute()
	
	//////////////////////////////////////////////////////////
	
	// 저장버튼 눌렀을때 여기서 처리를 해준다.
	// 첨부파일이 있으면 아래 리퀘스트 바디(@RequestBody)가 있으면 안된다.
	
	// @RequestBody : json => 자바객체
	// @ResponseBody : 자바객체 => json
	// @PathVariable : /board/list/:num 		=> /board/list/{num}
	// @RequestParam : /board/list?num=value 	=> /board/list?num=1	=> /board/list 
	// multipart/form-data : @RequestBody 선언 없이 그냥 받음 BoradDTo dto
	
	
	@PostMapping("/board/write")
	public String writeProExcute(BoardDTO dto, PageDTO pv, HttpServletRequest req, HttpSession session) {
		// 1. 첨부파일 처리하기, 현재 멀티파일에 값이 있는지 없는지 확인하기. 첨부파일 유뮤 체크
		MultipartFile file = dto.getFilename();
		

		// 첨부파일이 비어있지 않으면.. 파일 첨부가 있으면
		if (!file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, filePath);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}

		dto.setIp(req.getRemoteAddr()); // 클라이언트에 ip주소를 저장한다.

		// 본문 값드을 나오기 위해서.. 이거 안적으면 null로 저장이 된다. 아니 근데 sql에서는 본문 값이 저장되긴 하던데???
		// write로 넘어오면.. 뭐가 안맞다고.. 뭐가 안맞죠
		//AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
     	//dto.setMemberEmail(authInfo.getMemberEmail());
		
		boardService.insertProcess(dto); // 제목글에 대해서
		
		
		// 답변글일때 현재 페이지 값을 넘겨준다.
		// 답변글이면
		if(dto.getRef()!=0) {
//			ratt.addAttribute("currentPage",pv.getCurrentPage());
			return String.valueOf(pv.getCurrentPage());
		}else {
			// 제목글이면
			return String.valueOf(1);
		}
		
		//return "redirect:/board/list.do";
	}
	
	//////////////////////////////////

} // end class
