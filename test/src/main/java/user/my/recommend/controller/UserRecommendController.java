package user.my.recommend.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import user.dto.AuthInfo;
import user.dto.UserDTO;
import user.my.recommend.dto.BookmanageDTO;
import user.my.recommend.dto.UserRecommendDTO;
import user.my.recommend.service.UserRecommendService;
import user.request.dto.UserRequestDTO;
import user.service.UserService;

//http://localhost:8090/myapp

@Controller
public class UserRecommendController {

	private UserRecommendService reService;
	private UserRequestDTO rdto;
	private static final Map<String, String> CACHE = new HashMap<>();
	private UserService userService;

	public UserRecommendController() {
		// TODO Auto-generated constructor stub
	}

	public void setReService(UserRecommendService reService) {
		this.reService = reService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// recommend페이지
	@RequestMapping(value = "/my/recommend")
	public ModelAndView recommendView(HttpSession session, ModelAndView mav) {
		if (session.getAttribute("adminauthInfo") != null) {
			mav.setViewName("redirect:/");
			return mav;
		}
		
		System.out.println("my");

		CloseableHttpClient httpClient = HttpClients.createDefault();

		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String user_keynum = (String) session.getAttribute("keynum");

		System.out.println("recommend 접속 keynum : " + user_keynum);

		///////////////////////////////////////////////

		// 유저 정보
		UserRecommendDTO userDTO = reService.userInfoProcess(user_keynum);
		String user_name = userDTO.getUser_name();
		int user_age = userDTO.getUser_age();
		String age = "";
		String paramAge = "0";
		if (user_age <= 5 && user_age >= 0) {
			age = "영유아";
			paramAge = "0";
		} else if (user_age >= 6 && user_age <= 7) {
			age = "유아";
			paramAge = "6";
		} else if (user_age >= 8 && user_age <= 13) {
			age = "초등";
			paramAge = "8";
		} else if (user_age >= 14 && user_age <= 19) {
			age = "청소년";
			paramAge = "14";
		} else if (user_age >= 20 && user_age <= 29) {
			age = "20대";
			paramAge = "20";
		} else if (user_age >= 30 && user_age <= 39) {
			age = "30대";
			paramAge = "30";
		} else if (user_age >= 40 && user_age <= 49) {
			age = "40대";
			paramAge = "40";
		} else if (user_age >= 50 && user_age <= 59) {
			age = "50대";
			paramAge = "50";
		} else if (user_age >= 60) {
			age = "60세 이상";
			paramAge = "60";
		}else {
			paramAge = "-1";
		}

		String user_sex = userDTO.getUser_sex();
		String gender = "0";
		if (user_sex.equals("M")) {
			user_sex = "남성";
			gender = "0";
		}
		else if (user_sex.equals("F")) {
			user_sex = "여성";
			gender = "1";
		}

		mav.addObject("user_name", user_name);
		mav.addObject("user_age", age);
		mav.addObject("user_sex", user_sex);
		System.out.println(user_name);
		System.out.println(user_age);
		System.out.println(user_sex);

		///////////////////////////////
		// 정보나루 추천도서 (성별, 연령)
		List<admin.bookmanage.dto.BookmanageDTO> recbookList = new ArrayList<admin.bookmanage.dto.BookmanageDTO>();
		recbookList = userService.marurecom(String.valueOf(paramAge), gender);
//		System.out.println("----------------");
//		System.out.println(gender);
//		System.out.println(paramAge);
//		System.out.println("----------------");
		mav.addObject("recbookList", recbookList);

		//////////////////////////////////
		// 큐레이팅 도서

		// 반납한 책이 있는지 확인
		int borrow_count = reService.borrowCountProcess(user_keynum);
		mav.addObject("borrow_count", borrow_count);
		System.out.println(borrow_count);

		// 반납한 도서가 있을 경우에만 실행
		if (borrow_count != 0) {
			// 마지막으로 대출한 책 isbn, 제목
			UserRecommendDTO rdto = reService.lastBookProcess(user_keynum);
			mav.addObject("lastBookTitle", rdto.getTitle());

			/////////////////////////////////////////////////
			// 파이썬 작업 시작
			String inputIsbn = rdto.getIsbn();
			System.out.println("user가 마지막으로 읽은 책 : " + inputIsbn);

			String result = "";
			if (CACHE.containsKey(inputIsbn)) {
				result = CACHE.get(inputIsbn);
				System.out.println("캐시된 결과: " + result);
			} else {
				try {
					URI uri = new URI("http://localhost:5000/recommendation?isbn=" + inputIsbn);
					HttpGet httpGet = new HttpGet(uri);
					CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
					String responseString = EntityUtils.toString(httpResponse.getEntity());
					System.out.println("API 결과: " + responseString);
					httpResponse.close();
					CACHE.put(inputIsbn, responseString);
					result = responseString;
				} catch (URISyntaxException e) {
					System.err.println("잘못된 URI 구문: " + e.getMessage());
				} catch (IOException e) {
					System.err.println("I/O 오류 발생: " + e.getMessage());
				}
			}
			//////////////////////////////////
			System.out.println(result.getClass().getName());

			String[] dataArray = result.replaceAll("\\s", "").replaceAll("[\\[\\]\"]", "").split(",");

			List<BookmanageDTO> reList = new ArrayList<BookmanageDTO>();
			for (int i = 0; i <= 9; i++) {
				String recommendIsbn = dataArray[i];
				BookmanageDTO br = reService.bookInfoProcess(recommendIsbn, user_keynum);
				if (br != null) {

					System.out.println(recommendIsbn);
					reList.add(br);
				} else {

				}
				if(reList.size() > 5) {
					reList.remove(5);
				}
			}


			mav.addObject("recommendList", reList);
		} // end of if

		///////////////////////////////
		mav.setViewName("my/recommend");
		return mav;
	}

}
