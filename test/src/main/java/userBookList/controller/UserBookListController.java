package userBookList.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bookList.dto.BookListDTO;
import print.dto.PageDTO;
import user.dto.AuthInfo;
import user.dto.UserDTO;
import user.service.UserService;
import userBookList.dto.MergeBookDataDTO;
import userBookList.dto.UserBookListDTO;
import userBookList.service.UserBookListService;

// http://localhost:8090/myapp/my/user
@Controller
public class UserBookListController {

	private UserService userService;
	private UserBookListService userBookListService;
	private PageDTO pdto;
	
	public void setPdto(PageDTO pdto) {
		this.pdto = pdto;
	}
	public UserBookListController() {
		// TODO Auto-generated constructor stub
	}

	public void setUserBookListService(UserBookListService userBookListService) {
		this.userBookListService = userBookListService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// my/my 불러오기
	@RequestMapping(value = "my/record", method = RequestMethod.GET)
	public ModelAndView ShowMyUser(PageDTO pv, ModelAndView mav, HttpSession session, String user_id) {
		if (session.getAttribute("authInfo") == null) {
			mav.setViewName("redirect:/login");
			return mav; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		// dto 를 기반으로 booklist dto에서 정보값을 받아 새로운 리스트 배열 생성 후 mav에 담아 같이 보낸다.

		// 사용자 정보 조회
		UserDTO dto = userService.selectUserProcess(authInfo.getUser_id());
		mav.addObject("userDTO", dto);

		// 현재 대출/예약중인 도서 조회
		List<UserBookListDTO> CurrBookList = userBookListService.CurrBorrowListProcesss((String) session.getAttribute("keynum"));
		System.out.println(CurrBookList.size());
		List<MergeBookDataDTO> cbdto = new ArrayList<MergeBookDataDTO>();
		for (int i = 0; i < CurrBookList.size(); i++) {
			MergeBookDataDTO mdto = new MergeBookDataDTO();
			mdto.setAuthor(userBookListService.BookDataProcess(CurrBookList.get(i).getBook_keynum()).getAuthor());
			mdto.setTitle(userBookListService.BookDataProcess(CurrBookList.get(i).getBook_keynum()).getTitle());
			mdto.setBorrow_state(CurrBookList.get(i).getBorrow_state());
			mdto.setBorrow_date(CurrBookList.get(i).getBorrow_date());
			mdto.setReturn_due_date(CurrBookList.get(i).getReturn_due_date());
			mdto.setReturn_extend(CurrBookList.get(i).getReturn_extend());
			mdto.setBook_keynum(CurrBookList.get(i).getBook_keynum());
			mdto.setBorrow_keynum(CurrBookList.get(i).getBorrow_keynum());
			mdto.setUser_id(CurrBookList.get(i).getUser_keynum());
			cbdto.add(mdto);
			System.out.println(cbdto.get(i).getUser_id());
		}
		mav.addObject("cbdto", cbdto);
		
		
		int totalCount=userBookListService.countall((String) session.getAttribute("keynum"));
		System.out.println(totalCount);
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			mav.addObject("pv", this.pdto); // 전체 수에 따른 pv 추가
			mav.addObject("number", pdto.getNumber());
			System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow());

		}
		// 대출 내역 조회
		List<UserBookListDTO> PastBookList = userBookListService.PastBorrowListProcesss(this.pdto,(String) session.getAttribute("keynum"));
		List<MergeBookDataDTO> pbdto = new ArrayList<MergeBookDataDTO>();

		
		
		for (int i = 0; i < PastBookList.size(); i++) {
			MergeBookDataDTO mdto = new MergeBookDataDTO();
			mdto.setAuthor(userBookListService.BookDataProcess(PastBookList.get(i).getBook_keynum()).getAuthor());
			mdto.setTitle(userBookListService.BookDataProcess(PastBookList.get(i).getBook_keynum()).getTitle());
			mdto.setBorrow_date(PastBookList.get(i).getBorrow_date());
			mdto.setReturn_date(PastBookList.get(i).getReturn_date());
			mdto.setBook_keynum(PastBookList.get(i).getBook_keynum());
			pbdto.add(mdto);
		}
	

		
		
		
		mav.addObject("pbdto", pbdto);
		mav.setViewName("my/record");
		return mav;
	}

	// 대출 신청
	@RequestMapping(value = "books/loan", method = RequestMethod.POST)
	public ModelAndView Borrow(HttpSession session, BookListDTO bdto, ModelAndView mav,
			@RequestParam("book_keynum") String isbn) {
		if (session.getAttribute("authInfo") == null) {
			mav.setViewName("redirect:/login");
			return mav; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		UserBookListDTO udto = new UserBookListDTO();

		String LL = bdto.getLoan_state();
		System.out.println(LL);
		if (LL.equals("N")) {
			BookListDTO nbdto = userBookListService.TestProcess();
			mav.addObject("bdto", nbdto);
			String msg = "대출 실패! 대출 가능 상태가 아닙니다.";
			session.setAttribute("popupMessage", msg);
			mav.setViewName("redirect:/books/view?page=" + isbn);
			return mav;
		}

		System.out.println(authInfo.getUser_id());
		// 대출 신청 프로세스
		udto.setUser_keynum((String) session.getAttribute("keynum"));
		udto.setBook_keynum(bdto.getBook_keynum());
		udto.setBook_category(bdto.getCategory_s());

		boolean chk = userBookListService.BorrowProcess(udto);

		System.out.println(chk);
		// 대출 성공시 프로세스
		if (chk == true) {
			String msg = "대출 성공!";
			session.setAttribute("popupMessage", msg);
			BookListDTO nbdto = userBookListService.TestProcess();
			mav.addObject("bldto", nbdto);
			mav.setViewName("redirect:/books/view?page=" + isbn);
		} else {
			String msg = "대출 실패! 최대 대출/예약 권수를 초과합니다.";
			session.setAttribute("popupMessage", msg);
			// 대출 실패시 프로세스
			BookListDTO nbdto = userBookListService.TestProcess();
			mav.addObject("bldto", nbdto);
			mav.setViewName("redirect:/books/view?page=" + isbn);
		}
		return mav;

	}

	// 대출 신청
//		@RequestMapping(value = "books/loan", method = RequestMethod.POST)
//		public String Borrow(HttpSession session, BookListDTO bdto, Model mod) {
//			AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
//			UserBookListDTO udto = new UserBookListDTO();
//			
//			String LL = bdto.getLoan_state();
//			if(LL == "N") {
//				
//			}
//			
//			//대출 신청 프로세스
//			udto.setUser_id(authInfo.getUser_id());
//			udto.setBook_keynum(bdto.getBook_keynum());
//			udto.setBook_category(bdto.getCategory_s());
//			
//			boolean chk = userBookListService.BorrowProcess(udto);
//			//대출 성공시 프로세스
//			if(chk == true) {	
//				BookListDTO nbdto = userBookListService.TestProcess();
//				mod.addAttribute("bdto", nbdto);
//				mod.addAttribute("script", "alert('대출 성공!');");
//			}else {
//				//대출 실패시 프로세스
//				BookListDTO nbdto = userBookListService.TestProcess();
//				mod.addAttribute("bdto", nbdto);
//				mod.addAttribute("script", "alert('대출 실패! 최대 대출 권수를 초과합니다.');");
//			}
//			return "books/view";
//			
//		}

	//

	// 대출 연장
	@RequestMapping(value = "my/extend", method = RequestMethod.POST)
	public String ReturnExtend(ModelAndView mav, int borrow_keynum, int book_keynum, HttpSession session) {
		if (session.getAttribute("authInfo") == null) {

			return "redirect:/login"; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		userBookListService.ExtendReturnProcess(borrow_keynum, book_keynum);
		System.out.println("연장 완료");
		String keynum=(String) session.getAttribute("keynum");
		System.out.println(keynum);
		List<UserBookListDTO> UserBookList = userBookListService.CurrBorrowListProcesss(keynum);
		mav.addObject("UserBookList", UserBookList);
	
		mav.setViewName("my/record");
		return "redirect:/my/record";
	}

	// 반납 요청
	@RequestMapping(value = "my/return", method = RequestMethod.POST)
	public String Return(ModelAndView mav, int borrow_keynum, HttpSession session) {
		if (session.getAttribute("authInfo") == null) {

			return "redirect:/login"; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		// 도서 반납 프로세스 -
		UserBookListDTO udto = userBookListService.ReturnProcess(borrow_keynum);
		System.out.println("반납 완료");

		// 예약자가 있는지 확인
		if (udto == null) {
			return "redirect:/my/record";
		}
		// 예약자 정보로 신규 대출 후 기존 예약 취소
		String keynum = (String) session.getAttribute("keynum");

		userBookListService.BorrowProcess(udto);
		userBookListService.CancelReserveProcess(udto.getBorrow_keynum());

		List<UserBookListDTO> UserBookList = userBookListService.CurrBorrowListProcesss(keynum);
		mav.addObject("UserBookList", UserBookList);
	
		mav.setViewName("my/record");
		return "redirect:/my/record";
	}

	// 예약 신청
	// 예약 신청
	@RequestMapping(value = "books/borrow", method = RequestMethod.POST)
	public ModelAndView Reserve(ModelAndView mav, HttpSession session, BookListDTO bdto,
			@RequestParam("book_keynum") String isbn) {
		if (session.getAttribute("authInfo") == null) {
			mav.setViewName("redirect:/login");
			return mav; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		UserBookListDTO udto = new UserBookListDTO();
		Date DD = userBookListService.ReturnDueDateProcess(bdto.getBook_keynum());

		session.setAttribute("popupShown", false);

		System.out.println(bdto.getBook_keynum());

		String BB = bdto.getBorrow_state();
		if (BB.equals("N")) {
			BookListDTO nbdto = userBookListService.TestProcess();
			String msg = "예약 실패! 예약 가능 상태가 아닙니다.";
			session.setAttribute("popupMessage", msg);
			mav.setViewName("redirect:/books/view");
			return mav;
		}
		udto.setUser_keynum((String) session.getAttribute("keynum"));
		udto.setBook_keynum(bdto.getBook_keynum());
		udto.setBook_category(bdto.getCategory_s());
		udto.setBorrow_date(DD);
		// 적함성 검사 프로세스
		int chk = userBookListService.ReserveProcess(udto);
		if (chk == 3) {
			String msg = "예약 성공!";
			session.setAttribute("popupMessage", msg);
			// 예약 성공시 프로세스
			BookListDTO nbdto = userBookListService.TestProcess();
			mav.addObject("bldto", nbdto);
			mav.setViewName("redirect:/books/view?page=" + isbn);
			return mav;
		} else if (chk == 1) {
			String msg = "예약 실패! 최대 대출/예약 권수를 초과합니다.";
			session.setAttribute("popupMessage", msg);
			BookListDTO nbdto = userBookListService.TestProcess();
			mav.addObject("bldto", nbdto);
			mav.setViewName("redirect:/books/view?page=" + isbn);
			return mav;
		} else if (chk == 2) {
			String msg = "예약 실패! 이미 대출하고 계시는 책입니다.";
			session.setAttribute("popupMessage", msg);
			BookListDTO nbdto = userBookListService.TestProcess();
			mav.addObject("bldto", nbdto);
			mav.setViewName("redirect:/books/view?page=" + isbn);
			return mav;
		} else {
			String msg = "로그인을 하십시오.";
			session.setAttribute("popupMessage", msg);
			BookListDTO nbdto = userBookListService.TestProcess();
			mav.addObject("bldto", nbdto);
			mav.setViewName("redirect:/books/view");
			return mav;
		}
	}

	// 예약 취소
	@RequestMapping(value = "my/cancelReserve", method = RequestMethod.POST)
	public String CancelReserve(ModelAndView mav, int borrow_keynum, HttpSession session) {
		if (session.getAttribute("authInfo") == null) {
			
			return "redirect:/login"; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		userBookListService.CancelReserveProcess(borrow_keynum);
		System.out.println("예약 취소 완료");
		List<UserBookListDTO> UserBookList = userBookListService
				.CurrBorrowListProcesss((String) session.getAttribute("keynum"));
		mav.addObject("UserBookList", UserBookList);

		mav.setViewName("my/record");
		return "redirect:/my/record";
	}

	// 테스트 페이지 보기

}
