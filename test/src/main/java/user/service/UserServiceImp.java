package user.service;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import admin.bookmanage.dto.BookmanageDTO;
import user.dao.UserDAO;
import user.dto.AuthInfo;
import user.dto.UserDTO;


// 유저 관련 서비스: 회원가입,	로그인, 정보수정, 회원탈퇴. DB에 정보를 insert, select, update, delete 한다.
// AuthInfo: 회원의 정보값을 세션에 저장 하기 위해 사용.
public class UserServiceImp implements UserService {
	private UserDAO userDao;

	public UserServiceImp() {
		// TODO Auto-generated constructor stub
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	// 유저 추가
	@Override
	public AuthInfo addUserProcess(UserDTO dto) {
		userDao.insertUser(dto);

		return new AuthInfo(dto.getUser_id(), dto.getUser_password());
	}

	// 유저 로그인
	@Override
	public UserDTO loginProcess(AuthInfo authInfo) {
		UserDTO user = userDao.selectByUserId(authInfo);
		return user;
	}

	// 회원가입시 유저 아이디 중복 체크
//   public UserDTO
	// 유저 정보 수정
	@Override
	public UserDTO updateUserProcess(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	// 유저 수정(유저 정보를 세션에 저장하기 위해 인증 정보 형태로 리턴)
	@Override
	public AuthInfo updateUserProcess(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int idcheck(String user_id) {
		// TODO Auto-generated method stub
		System.out.println("서비스 실행됨");
		System.out.println(user_id);
		return userDao.idcheck(user_id);
	}

	@Override
	public UserDTO selectUserProcess(String id) {
		// TODO Auto-generated method stub
		return userDao.selectByUserIdString(id);
	}

	@Override
	public String getKeynum(String id) {
		// TODO Auto-generated method stub
		return userDao.getKeynum(id);
	}

	// 주소 수정
	@Override
	public AuthInfo updateAddressProcess(UserDTO dto) {
		userDao.updateAddress(dto);
		AuthInfo ati = new AuthInfo(dto.getUser_id(), dto.getUser_password());
		UserDTO user = userDao.selectByUserId(ati);
		return new AuthInfo(user.getUser_id(), user.getUser_password(), user.getUser_name());
	}

	// 비밀번호 변경
	@Override
	public AuthInfo updatePassProcess(UserDTO dto) {
		userDao.updatePass(dto);
		AuthInfo ati = new AuthInfo(dto.getUser_id(), dto.getUser_password());
		UserDTO user = userDao.selectByUserId(ati);
		return new AuthInfo(user.getUser_id(), user.getUser_password(), user.getUser_name());
	}

	// 유저 삭제
	@Override
	public void deleteUserProcess(String user_keynum) {
		userDao.deleteUser(user_keynum);
	}

	@Override
	public int checkBookProcess(String user_id) {
		return userDao.checkBook(user_id);
	}

	@Override
	public List<BookmanageDTO> nbookprintService() {
		// TODO Auto-generated method stub
		return userDao.nbookprint();
	}

	@Override
	public List<BookmanageDTO> pbookprintService() {
		// TODO Auto-generated method stub
		return userDao.pbookprint();
	}

	@Override
	public List<BookmanageDTO> marurecom(String paramAge, String gender) {
		JSONObject popularBook = null;
		List<BookmanageDTO> recomList = new ArrayList<BookmanageDTO>();
		try {
			// 검색조건 입력

			// 정보나루 데이터 url
			StringBuffer popularBook_url = new StringBuffer();
			String key = "e06a89dc1b90dde990758b54fbfefba79260b91c0563b868e9107df6a568a960";
			popularBook_url.append("http://data4library.kr/api/loanItemSrch");
			popularBook_url.append("?authKey=" + key); // 인증키
			popularBook_url.append("&startDt=" + "2021-01-01");
			popularBook_url.append("&endDt=" + "2023-03-18");
			popularBook_url.append("&gender=" + gender); // 성별 : 남성0, 여성1
			popularBook_url.append("&age=" + paramAge); // 연령대 : 유아(0), 어린이(8), 청소년(14),
			// 20대(20), 30대(30), 40대(40), 50대(50), 60대이상(60)
			popularBook_url.append("&pageNo=1&pageSize=10");
			System.out.println(popularBook_url);
			
			// xml파일(url) 파싱
			URL url = new URL(popularBook_url.toString());
			BufferedInputStream xmldata = new BufferedInputStream(url.openStream());
			Document documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmldata);

			// xml 데이터 읽기
			NodeList aList = documentInfo.getElementsByTagName("doc");
			System.out.println(aList.getLength());
			for (int i = 0; i < aList.getLength(); i++) {
				Node nNode = aList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					// HashMap -> json
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("no", userDao.getTagValue("no", eElement));
					map.put("bookname", userDao.getTagValue("bookname", eElement));
					map.put("authors", userDao.getTagValue("authors", eElement));
					map.put("publisher", userDao.getTagValue("publisher", eElement));
					map.put("publication_year", userDao.getTagValue("publication_year", eElement));
					map.put("isbn", userDao.getTagValue("isbn13", eElement));
					map.put("bookImageURL", userDao.getTagValue("bookImageURL", eElement));

					popularBook = new JSONObject(map);
					System.out.println(popularBook);

					// 변수에 담기
					String bookname = userDao.getTagValue("bookname", eElement);
					String isbn = userDao.getTagValue("isbn13", eElement);
					String author = userDao.getTagValue("authors", eElement);
					String pubYear = userDao.getTagValue("publication_year", eElement);

//					System.out.println(bookname);
//					System.out.println(isbn);
//					System.out.println(author);
//					System.out.println(pubYear);

					BookmanageDTO recBook = new BookmanageDTO(isbn, bookname, author, pubYear);
					recomList.add(recBook);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(recomList);
		return recomList;
	}

}
