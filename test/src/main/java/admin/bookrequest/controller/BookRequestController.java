package admin.bookrequest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import admin.bookrequest.dto.BookRequestDTO;
import admin.bookrequest.dto.PageDTO;
import admin.bookmanage.dto.BookmanageDTO;
import admin.bookmanage.search.SeojiSearch;
import admin.bookmanage.service.BookmanageService;
import admin.bookrequest.service.BookRequestService;


// http://localhost:8090/myapp/admin/request
// http://localhost:8090/myapp/admin/bookupdate


@Controller
public class BookRequestController {
	
	private BookRequestService bookrequestService;
	private BookmanageService bookmanageService;
	private BookmanageDTO mdto;
	private BookRequestDTO rdto;
	private PageDTO pdto;
	private SeojiSearch seojiSearch;

	public BookRequestController() {
		// TODO Auto-generated constructor stub
	}

	public void setBookrequestService(BookRequestService bookrequestService) {
		this.bookrequestService = bookrequestService;
	}
	
	public void setBookmanageService(BookmanageService bookmanageService) {
		this.bookmanageService = bookmanageService;
	}
	
	public void setSeojiSearch(SeojiSearch seojiSearch) {
		this.seojiSearch = seojiSearch;
	}

	//도서신청 페이지 (전체 신청목록 조회)
	@RequestMapping(value="/admin/request")
	public ModelAndView bookrequest(PageDTO pv, ModelAndView mav) {
		int totalCount = bookrequestService.countProcess();
		mav.addObject("totalCount", totalCount);	 //개수 
		//System.out.println("총갯수 : "+totalCount);
		
		if(totalCount>=1) {
			if(pv.getCurrentPage()==0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount); 
			mav.addObject("pv", this.pdto); // pv 추가 
			mav.addObject("aList", bookrequestService.requestListProcess(this.pdto)); // 신청 목록 전체 추가
			
			mav.addObject("Number", pdto.getNumber()); //번호 추가
		}
		
		
		mav.setViewName("admin/request");
		return mav;
	}
	
	@RequestMapping(value = "/admin/request/searchRequest", method = RequestMethod.GET)
	   public String searchadmin(Model model, PageDTO pv, @RequestParam("option") String option,
	         @RequestParam("searchword") String searchword) {
		
	      List<BookRequestDTO> dtos = null;
	      
		  System.out.println(option);
		  System.out.println(searchword);
		  
	      // 페이지네이션을 위한 결과 개수 가져오기
	      int totalCount = bookrequestService.searchCountProcess(option, searchword); //신청 목록에서 검색 결과 조회
	      model.addAttribute("totalCount", totalCount); // 검색 결과 수 추가
	      System.out.println("totalCount : "+totalCount);

	      if(totalCount ==0) {
	    	  model.addAttribute("zero","검색 결과가 없습니다");
	      }
	      
	      if (totalCount >= 1) {
	         if (pv.getCurrentPage() == 0) 
	            pv.setCurrentPage(1);
	         this.pdto = new PageDTO(pv.getCurrentPage(), totalCount, searchword, option);
	         model.addAttribute("searchPv", this.pdto);  //검색 결과 수에 맞춘 pv 추가
	         model.addAttribute("Number", pdto.getNumber()); //번호 추가

		      dtos = bookrequestService.searchListProcess(this.pdto); 
		      model.addAttribute("aList", dtos); // 검색 결과 리스트 추가
	      }
	      model.addAttribute("query",searchword); // 검색어 추가
	      model.addAttribute("option",option);// 검색옵션 추가
	      return "admin/request";
	   }
	

	//새로운 도서 추가기능
	@RequestMapping(value="/admin/request/accept", method=RequestMethod.POST)
	public String acceptProExcute(int request_keynum, HttpServletRequest req) {
		//System.out.println(request_keynum); 
		bookrequestService.acceptSelectProcess(request_keynum);  //신청 처리 
		
		String isbn = req.getParameter("sIsbn");
		String title = req.getParameter("sTitle");
		String author = req.getParameter("sAuthor");
		String publisher = req.getParameter("sPublisher");
		String pub_date = req.getParameter("sPub_date");
		String contents = req.getParameter("sContents");
		String thumbnail = req.getParameter("sThumbnail");

		String category_s = seojiSearch.SearchCategory(isbn); //isbn으로 도서분류번호 구하기 
		//System.out.println("도서분류번호 : "+ category_s);
		
		this.mdto = new BookmanageDTO(isbn, title, author, publisher, pub_date, contents, thumbnail, category_s); // 신청 목록에서 선택한 값 dto 로 만듬
		
		int count = Integer.parseInt(req.getParameter("sCount")); 
		//System.out.println("등록수량 : "+count);
		for(int i=1; i<=count; i++) {
			bookmanageService.insertProcess(this.mdto);  // 등록 수량 만큼 신청도서 추가
		}

		return "redirect:/admin/request";
	}
	
	//신청도서 반려
	@RequestMapping(value="/admin/request/reject", method=RequestMethod.POST)
	public String rejectProExcute(int request_keynum, HttpServletRequest req) {
		//System.out.println(request_keynum);
		bookrequestService.rejectSelectProcess(request_keynum);  // 거절
		
		return "redirect:/admin/request";
	}
}
