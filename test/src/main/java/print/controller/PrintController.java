package print.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import admin.bookmanage.dto.BookmanageDTO;
import print.dto.PageDTO;
import print.service.PrintService;

// http://localhost:8090/myapp/books
@Controller
public class PrintController {
	PrintService printService;
	private PageDTO pdto;

	public void setPrintService(PrintService printService) {
		this.printService = printService;
	}

	public void setPdto(PageDTO pdto) {
		this.pdto = pdto;
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ModelAndView printbook(PageDTO pv, ModelAndView mav) {

		int totalCount = printService.countAll(); // 전체 결과수

		System.out.println(totalCount);
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			mav.addObject("pv", this.pdto); // pv 추가

		}

		List<BookmanageDTO> printdtos = printService.printAll(this.pdto); // 전체 리스트 추가
		System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow());
		mav.addObject("printdtos", printdtos);

		mav.setViewName("books");
		return mav;

	}

	@RequestMapping(value = "/books/search", method = RequestMethod.GET)
	public String searchbook(PageDTO pv, Model model, @RequestParam("search_item") String option,
			@RequestParam("query") String query, @RequestParam("categories") String cate) {
		// query 는 검색어, cate 는 카테고리 값 option 은 옵션값

		System.out.println("option" + option);
		System.out.println("query" + query);
		System.out.println("cate" + cate); // 리퀘스트 파람 결과값 출력
		List<BookmanageDTO> search_result = null; // 리스트 생성
		String itt="";
		if(option.equals("search_isbn")) {
			itt=printService.isbnTotitleService(query);			
		}
		if (query.equals("")) { // 확인
			System.out.println("쿼리값빔");
		}
		int totalCount = printService.countService(query, option, cate); // 페이지네이션을 위한 결과 개수 가져오기
		System.out.println(totalCount);
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			model.addAttribute("searchResultPv", this.pdto);// 검색 결과에 맞춘 pv 추가

		}
		search_result = printService.searchService(this.pdto, query, option, cate); // 리스트 값 변경
		System.out.println(search_result.size());
		model.addAttribute("search_result", search_result); // 모델에 값추가
		model.addAttribute("query", query); // 검색어
		model.addAttribute("option", option); // 옵션
		model.addAttribute("cate", cate); // 카테고리
		model.addAttribute("count", totalCount); // 검색결과수
		model.addAttribute("itt",itt);
		return "books";

	}

	@RequestMapping(value = "/books/new")
	public String newBook(Model model, PageDTO pv) {
		int totalCount = printService.nBookCountService(); // 전체 결과수

		System.out.println(totalCount);
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			model.addAttribute("pv",this.pdto); // pv 추가

		}

		List<BookmanageDTO> nbooklist= printService.nBookService(this.pdto); // 전체 리스트 추가
		System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow());
		System.out.println(nbooklist.size());
		model.addAttribute("nbooklist",nbooklist);
		return "books/new";
	}

	@RequestMapping(value = "/books/popular")
	public String popularBook(Model model) {
		List<BookmanageDTO> pbooklist=printService.pBookService();
		System.out.println(pbooklist.size());
		model.addAttribute("pbooklist",pbooklist);
		return "books/popular";
	}

}
