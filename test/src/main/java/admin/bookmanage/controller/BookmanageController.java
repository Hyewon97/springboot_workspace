package admin.bookmanage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import admin.bookmanage.dto.BookmanageDTO;
import admin.bookmanage.dto.PageDTO;
import admin.bookmanage.search.SeojiSearch;
import admin.bookmanage.service.BookmanageService;


// http://localhost:8090/myapp/admin/books
// http://localhost:8090/myapp/admin/bookupdate


@Controller
public class BookmanageController {
	
	private BookmanageService bookmanageService;
	private BookmanageDTO bdto;
	private PageDTO pdto;
	private SeojiSearch seojiSearch;

	public BookmanageController() {
		// TODO Auto-generated constructor stub
	}

	public void setBookmanageService(BookmanageService bookmanageService) {
		this.bookmanageService = bookmanageService;
	}
	
	public void setSeojiSearch(SeojiSearch seojiSearch) {
		this.seojiSearch = seojiSearch;
	}
	
//	@RequestMapping(value = "/admin/books")
//	public String books() {
//		return "admin/books";
//	}
	
	//도서관리 메인 페이지 (전체 도서목록 조회)
	@RequestMapping(value="/admin/books")
	public ModelAndView bookmanage(PageDTO pv, ModelAndView mav) {
		int totalCount = bookmanageService.countProcess(); // 전체 도서 수 반환
		mav.addObject("totalCount", totalCount);	// 전체 도서 수 add 
		
		if(totalCount>=1) {
			if(pv.getCurrentPage()==0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			mav.addObject("pv", this.pdto);  // 페이지네이션을 위한 pv 추가
			mav.addObject("Number", pdto.getNumber());  // number 추가
		}
		mav.addObject("aList", bookmanageService.allListProcess(this.pdto)); // 전체 리스트 추가
		
		mav.setViewName("admin/books");
		return mav;
	}
	
	//소장도서검색
	@RequestMapping(value = "/admin/books/searchBooks", method = RequestMethod.GET)
	   public String searchadmin(Model model, PageDTO pv, @RequestParam("option") String option,
	         @RequestParam("searchword") String searchword) {
		
	      List<BookmanageDTO> dtos = null;
	      
		  System.out.println(option);
		  System.out.println(searchword);
		  
	      // 페이지네이션을 위한 결과 개수 가져오기
	      int totalCount = bookmanageService.searchCountProcess(option, searchword);  // 검색 결과 수 
	      model.addAttribute("totalCount", totalCount); // 검색 결과 수 추가
	      System.out.println("totalCount : "+totalCount); 

	      if(totalCount ==0) {
	    	  model.addAttribute("zero","검색 결과가 없습니다");   //0일 때
	      }
	      
	      if (totalCount >= 1) {
	         if (pv.getCurrentPage() == 0) 
	            pv.setCurrentPage(1);
	         this.pdto = new PageDTO(pv.getCurrentPage(), totalCount, searchword, option);
	         model.addAttribute("searchPv", this.pdto);  // pv 추가 
	         model.addAttribute("Number", pdto.getNumber()); // number; 
	         System.out.println("현재페이지 :"+ pdto.getCurrentPage());
	          System.out.println("총 레코드수 :"+ pdto.getTotalCount());
	          System.out.println("총 페이지수 :"+ pdto.getTotalPage());
	          System.out.println("시작 레코드 번호 :"+ pdto.getStartRow());
	          System.out.println("끝 레코드 번호 :"+ pdto.getEndRow());
	          System.out.println("한 블록의 시작 페이지 번호 :"+ pdto.getStartPage());
	          System.out.println("한 블록의 끝 페이지 번호 :"+ pdto.getEndPage());
	          System.out.println("리스트에서 출력번호(rownum) :"+ pdto.getNumber());
	          System.out.println("---------------------------------");

	      }
	      dtos = bookmanageService.searchListProcess(this.pdto);
	      model.addAttribute("aList", dtos);  // 검색 결과 리스트 추가
	      model.addAttribute("query",searchword); // 검색어 추가
	      model.addAttribute("option",option); // 검색 설정 추가
	      return "admin/books";
	   }
	
	
//	//도서 상세정보
//	@RequestMapping("/admin/bookinfo")
//	public ModelAndView viewExcute(@ModelAttribute("pv") PageDTO pv, int num, ModelAndView mav) {
//		BookmanageDTO bdto = bookmanageService.bookInfoProcess(num);
//		
//		mav.addObject("dto", bdto); //게시글내용
//		mav.addObject("currentPage", pv.getCurrentPage());
//		mav.setViewName("/admin/bookinfo");
//		return mav;
//	}	
	
	//도서 삭제
	@RequestMapping("/admin/books/delete")
	public String deleteExecute(int num, Integer currentPage, RedirectAttributes ratt) {

		//System.out.println(num);
		ratt.addAttribute("currentPage", currentPage); 
		bookmanageService.deleteProcess(num); //삭제
		return "redirect:/admin/books";
	}
	
//	//새로운 도서 추가페이지로 이동 (새로운 도서 검색 및 추가-카카오도서)
//	@RequestMapping(value="/admin/bookupdate", method=RequestMethod.GET)
//	public String bookupdate() {
//		return "/admin/bookupdate5";
//	}	
		
	
	//새로운 도서 추가기능
	@RequestMapping(value="/admin/books/bookupdate", method=RequestMethod.POST)
	public String writeProExcute(HttpServletRequest req) {
		String isbn = req.getParameter("sIsbn");
		String title = req.getParameter("sTitle");
		String author = req.getParameter("sAuthor");
		String publisher = req.getParameter("sPublisher");
		String pub_date = req.getParameter("sPub_date");
		String contents = req.getParameter("sContents");
		String thumbnail = req.getParameter("sThumbnail");

		String category_s = seojiSearch.SearchCategory(isbn); //isbn으로 도서분류번호 구하기 
		//System.out.println("도서분류번호 : "+ category_s);
		
		this.bdto = new BookmanageDTO(isbn, title, author, publisher, pub_date, contents, thumbnail, category_s);
		
		int count = Integer.parseInt(req.getParameter("sCount"));
		//System.out.println("등록수량 : "+count);
		for(int i=1; i<=count; i++) {
			bookmanageService.insertProcess(this.bdto); //추가
		}

		return "redirect:/admin/books";
	}
	

	
}
