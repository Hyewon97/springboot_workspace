package bookList.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bookList.dto.BookListDTO;
import bookList.dto.BookReviewDTO;
import bookList.dto.PageDTO;
import bookList.service.BookListService;

//http://localhost:8090/myapp/books/view

@Controller
public class BookListController {

	private BookListService bookListService;
	private PageDTO pdto;
	private BookListDTO bldto;

	public BookListController() {
		// TODO Auto-generated constructor stub
	}

	public void setBookListService(BookListService bookListService) {
		this.bookListService = bookListService;
	}

	// 후기 리스트 출력과정
	@RequestMapping(value = "/books/view", method = RequestMethod.GET)
	public String bookInfo(PageDTO pv, Model model, @RequestParam("page") String isbn) {
		int totalReviews = bookListService.countProcess(isbn);
		if (totalReviews >= 1) {
			if (pv.getCurrentPage() == 0) {
				pv.setCurrentPage(1);
			}
		}

		this.bldto = bookListService.selectBookProcess(isbn);
		this.pdto = new PageDTO(pv.getCurrentPage(), totalReviews);

		List<BookReviewDTO> revList = bookListService.reviewListProcess(this.pdto, isbn);
		
		model.addAttribute("bldto", this.bldto);
		model.addAttribute("pv", this.pdto);
		model.addAttribute("revList", revList);
		return "books/view";
	}

	@RequestMapping(value = "/books/writeRev", method = RequestMethod.POST)
	public String writeRevExecute(@RequestParam("isbn") String isbn, bookList.dto.BookReviewDTO dto,
			HttpServletResponse response, HttpSession session) {
		if (session.getAttribute("authInfo") == null) {
			return "redirect:/login"; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		
		dto.setIsbn(isbn);
		String id = dto.getUser_id();
		System.out.println(id);

		String keynum = (String) session.getAttribute("keynum");
		int book_keynum = dto.getBook_keynum();
		System.out.println(keynum);
		System.out.println(book_keynum);

		int revs = bookListService.revCheckProcess(String.valueOf(keynum), String.valueOf(book_keynum));
		System.out.println(revs);
		if (revs > 0) {
			String msg = "후기 등록 실패! 이미 해당 도서에 대한 후기를 남기셨습니다.";
			session.setAttribute("popupMessage", msg);
			return "redirect:/books/view?page=" + String.valueOf(book_keynum);
		}
		System.out.println(isbn);

		String msg = "후기를 등록하였습니다.";
		session.setAttribute("popupMessage", msg);
		bookListService.saveProcess(keynum, dto);
		return "redirect:/books/view?page=" + String.valueOf(book_keynum);
	}

	@RequestMapping(value = "/books/update", method = RequestMethod.GET)
	public String updateRev(@RequestParam("page")String page,BookReviewDTO dto, HttpSession session){
		if (session.getAttribute("authInfo") == null) {
			return "redirect:/login"; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		
		bookListService.updateProcess(dto);
	
		return "redirect:/books/view?page="+page;
	}

	@RequestMapping(value = "/books/delete", method = RequestMethod.GET)
	public String deleteRev(@RequestParam("review_keynum") int review_keynum, HttpSession session,@RequestParam("page") String page) {
		
		if (session.getAttribute("authInfo") == null) {
			return "redirect:/login"; // 관리자가 로그인이 되어있다면 회원가입 불가
		}
		
		bookListService.deleteProcess(review_keynum);
		return "redirect:/books/view?page="+page;
	}

}
