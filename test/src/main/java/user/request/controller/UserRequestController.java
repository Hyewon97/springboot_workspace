package user.request.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import admin.bookmanage.dto.BookmanageDTO;
import user.dto.AuthInfo;
import user.request.dto.PageDTO;
import user.request.dto.UserRequestDTO;
import user.request.service.UserRequestService;

@Controller
public class UserRequestController {
	
	private UserRequestService userRequestService;
	private UserRequestDTO udto;
	private PageDTO pdto;
	
	public UserRequestController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setUserRequestService(UserRequestService userRequestService) {
		this.userRequestService = userRequestService;
	}
	
	//request페이지
	@RequestMapping(value = "/request/request")
	public String request(HttpSession session) {
		if (session.getAttribute("adminauthInfo") != null) {
			return "redirect:/";
		}
		return "request/request";
	}

	//새로운 도서 추가기능
	@RequestMapping(value="request/request/requestInsert", method=RequestMethod.POST)
	public String writeProExcute(HttpServletRequest req, HttpSession session) {
		String isbn = req.getParameter("sIsbn");
		String title = req.getParameter("sTitle");
		String author = req.getParameter("sAuthor");
		String request_text = req.getParameter("reason");
		System.out.println(title);
		
		String user_keynum = (String)session.getAttribute("keynum");
		
		System.out.println("request 추가 keynum 1 : "+user_keynum);
		this.udto = new UserRequestDTO(isbn, title, author, user_keynum, request_text);
		System.out.println("request 추가 keynum 2 : "+udto.getUser_keynum());

		userRequestService.insertProcess(this.udto);

		return "request/request";
	}
	
	//신청도서 목록
	@RequestMapping(value = "/request/list")
	public ModelAndView request(HttpSession session, PageDTO pv, ModelAndView mav) {

		String user_keynum = (String)session.getAttribute("keynum");
		System.out.println("request list 접속 keynum : "+user_keynum);
		
		int totalCount = userRequestService.countProcess(user_keynum);
		mav.addObject("totalCount", totalCount);	
		System.out.println("총갯수 : "+totalCount);
		
		if(totalCount>=1) {
			if(pv.getCurrentPage()==0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount, user_keynum);
			mav.addObject("pv", this.pdto);
			System.out.println("조회할 userkeynum : "+pdto.getUser_keynum());
			mav.addObject("Number", pdto.getNumber());
			mav.addObject("aList", userRequestService.allListProcess(this.pdto));
		}
		
		
		mav.setViewName("request/list");
		return mav;
	}
	
}//end class
