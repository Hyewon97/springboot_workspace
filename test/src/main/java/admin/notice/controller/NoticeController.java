package admin.notice.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import admin.bookmanage.dto.BookmanageDTO;
import admin.dto.AdminDTO;
import admin.notice.dto.NoticeDTO;
import admin.notice.dto.PageDTO;
import admin.notice.file.FileUpload;
import admin.notice.service.NoticeService;

// http://localhost:8090/myapp/admin/notice

@Controller
public class NoticeController {

	private NoticeService noticeService;
	private PageDTO pdto;

	public NoticeController() {
		// TODO Auto-generated constructor stub
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@RequestMapping(value="/admin/notice")
	public ModelAndView listExecute(PageDTO pv, ModelAndView mav, HttpSession session) {
		
		String admin_keynum = (String) session.getAttribute("adminKeynum");
		System.out.println("admin_keynum :" + admin_keynum);
		
		int totalCount = noticeService.countProcess();
		mav.addObject("totalCount2", totalCount);
		System.out.println(totalCount);
		
		if(totalCount>=1) {
			if(pv.getCurrentPage()==0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			mav.addObject("pv", this.pdto);
			mav.addObject("Number", pdto.getNumber());
			mav.addObject("aList", noticeService.listProcess(this.pdto));
		} else {
			mav.addObject("totalCount", totalCount);
		}
		
		
		mav.setViewName("admin/notice");
		return mav;
	}
	
	//게시글검색
		@RequestMapping(value = "/admin/notice/search", method = RequestMethod.GET)
		   public String searchadmin(Model model, PageDTO pv, @RequestParam("option") String option,
		         @RequestParam("searchword") String searchword) {
			
		      List<NoticeDTO> dtos = null;
		      
			  System.out.println(option);
			  System.out.println(searchword);
			  
		      // 페이지네이션을 위한 결과 개수 가져오기
		      int totalCount = noticeService.searchCountProcess(option, searchword);

		      model.addAttribute("totalCount2", totalCount);
		      model.addAttribute("totalCount", totalCount);
		      System.out.println("totalCount : "+totalCount);
		      
		      if (totalCount >= 1) {
		         if (pv.getCurrentPage() == 0) 
		            pv.setCurrentPage(1);
		         this.pdto = new PageDTO(pv.getCurrentPage(), totalCount, searchword, option);
		         model.addAttribute("searchPv", this.pdto);
		         model.addAttribute("Number", pdto.getNumber());

			      dtos = noticeService.searchListProcess(this.pdto);
			      model.addAttribute("aList", dtos);
		      }
		      model.addAttribute("query",searchword);
		      model.addAttribute("option",option);
		      return "admin/notice";
		   }	
	

//	@RequestMapping("/notice/info")
//	public ModelAndView viewExcute(@ModelAttribute("pv") PageDTO pv, int num, ModelAndView mav) {
//		NoticeDTO bdto = noticeService.contentProcess(num);
//		
//		//사진링크가져오기
//		if(!(bdto.getUpload_img() == null)) {
//			String srcImg = "\\myapp\\resources\\images\\"+bdto.getUpload_img(); 
//			//System.out.println(srcImg);
//			mav.addObject("srcImg", srcImg);
//		}
//		mav.addObject("dto", bdto); //게시글내용
//		mav.addObject("pn", noticeService.preNextProcess(num)); //이전글,다음글
//		mav.addObject("currentPage", pv.getCurrentPage());
//		mav.setViewName("/notice/info");
//		return mav;
//	}	

//	@RequestMapping(value="/notice/write", method=RequestMethod.GET)
//	public ModelAndView writeExcute(@ModelAttribute("dto") NoticeDTO dto, @ModelAttribute("pv") PageDTO pv, ModelAndView mav) {
//		mav.setViewName("/notice/write");
//		return mav;
//	}	

	@RequestMapping(value="/admin/notice/write", method=RequestMethod.POST)
	public String writeProExcute(NoticeDTO dto, HttpServletRequest req, HttpSession session) {
		
		String admin_keynum = (String) session.getAttribute("adminKeynum");
		dto.setAdmin_keynum(admin_keynum);
		System.out.println("admin_keynum :" + admin_keynum);
		
		MultipartFile file = dto.getFilename();
		MultipartFile img = dto.getImgname();

		//첨부파일이 있으면
		if(!file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, req);
			dto.setUpload_file(random + "_" + file.getOriginalFilename());	
		}

		//사진첨부가 있으면
		if(!img.isEmpty()) {
			UUID random = FileUpload.saveCopyImg(img, req);
			dto.setUpload_img(random + "_" + img.getOriginalFilename());	
		}

		noticeService.insertProcess(dto);

		return "redirect:/admin/notice";
	}

//	@RequestMapping("/notice/contentdownload")
//	public ModelAndView downloadExecute(int num, ModelAndView mav) {
//		mav.addObject("num", num);
//		mav.setViewName("download");
//		return mav;
//	}

//	@RequestMapping(value="admin/notice/update", method=RequestMethod.GET)
//	public ModelAndView updateExecute(int num, ModelAndView mav) {
//		mav.addObject("updateDTO", noticeService.updateSelectProcess(num));
//		mav.addObject("currentPage", 1);
//		mav.setViewName("/admin/notice");
//		return mav;
//	}

	@RequestMapping(value = "admin/notice/update", method=RequestMethod.POST)                              
	public String updateExecute(NoticeDTO dto, Integer currentPage, HttpServletRequest req, RedirectAttributes ratt, HttpSession session) {

		String admin_id = (String) session.getAttribute("adminID");
		dto.setAdmin_id(admin_id);
		System.out.println("admin_id :" + admin_id);
		
		System.out.println(dto.getNum());
		
		MultipartFile file = dto.getFilename();
		MultipartFile img = dto.getImgname();

		//첨부파일이 있으면
		if(!file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, req);
			dto.setUpload_file(random + "_" + file.getOriginalFilename());	
		}

		//사진첨부가 있으면
		if(!img.isEmpty()) {
			UUID random = FileUpload.saveCopyImg(img, req);
			dto.setUpload_img(random + "_" + img.getOriginalFilename());	
		}
		noticeService.updateProcess(dto, FileUpload.urlPath(req), FileUpload.urlPathImg(req));

		//return "redirect:/board/list.do?currentPage=" + currentPage;

		ratt.addAttribute("currentPage", currentPage);
		return "redirect:/admin/notice";
	}

	@RequestMapping("admin/notice/delete")
	public String deleteExecute(int num, HttpServletRequest req, RedirectAttributes ratt) {
		System.out.println(num);
		
		noticeService.deleteProcess(num, FileUpload.urlPath(req), FileUpload.urlPathImg(req));
		return "redirect:/admin/notice";
	}
}
