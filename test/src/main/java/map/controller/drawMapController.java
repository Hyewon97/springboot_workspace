package map.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import map.dao.MapDAO;
import map.dto.MapDTO;

import map.service.MapService;
import user.dto.AuthInfo;

// http://localhost:8090/myapp/my/map

@Controller
public class drawMapController {
	private MapService mapService;
	SqlSession sqlSession;

	public drawMapController() {
		// TODO Auto-generated constructor stub
	}

	public void setMapService(MapService mapService) {
		this.mapService = mapService;
	}

	@RequestMapping(value = "/my/map")
	public String drawmap(Model model, HttpSession session) {
		if (session.getAttribute("authInfo") == null) {
			return "redirect:/login";
		} //세션에 저장된 authinfo 가 없다면 로그인 화면으로 리다이렉트
		System.out.println("컨트롤러 실행");
		AuthInfo authinfo = (AuthInfo) session.getAttribute("authInfo");  // 세션에 저장된 authinfo 가져옴
		String a = authinfo.getUser_id(); // 저장된 아이디
		System.out.println(a);
		List<MapDTO> mapdtos = mapService.getlibs(a); //아이디로 주소 값 가져옴

		model.addAttribute("mapdtos", mapdtos); //결과 리스트 추가

		List<String> xy = mapService.GetCenterX(a); //주소 값 가져옴
		if (xy.size() != 0) {
			String x = xy.get(0);
			String y = xy.get(1);
			model.addAttribute("x", x);
			model.addAttribute("y", y);

		} else {
			model.addAttribute("x", 126.570667);
			model.addAttribute("y", 33.450701);
		}
		String addr= mapService.getaddrService(a);
		System.out.println(addr);
		addr=addr.substring(6);
		addr=addr.split("/")[0];
		model.addAttribute("addr",addr); // 주소 추가
		return "my/map";
	}

}
