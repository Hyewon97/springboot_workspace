package map.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.client.RestTemplate;

import map.dao.MapDAO;
import map.dto.MapDTO;

public class MapServiceImp implements MapService {
	public MapDAO dao;
	ArrayList<String> centerxy;

	public MapServiceImp() {
		// TODO Auto-generated constructor stub
	}

	public void setDao(MapDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<MapDTO> getlibs(String user_id) {

		System.out.println("서비스 실행");
		String addr = dao.getAddr(user_id).substring(6);

		System.out.println(addr);
		List<MapDTO> mapdtolist = new ArrayList<MapDTO>();
		RestTemplate rest = new RestTemplate();

		// HttpHeaders 객체 생성
		HttpHeaders headers = new HttpHeaders();

		// hearders에 ContentType을 Json으로 설정한다.
		headers.setContentType(MediaType.APPLICATION_JSON);

		// 사용할 api 키
		String key = "KakaoAK dacb438d04968f33fd5fcc9f9a1c6cd2";

		// headers 에 이름이 "Authorization"인 부분을 키로 설정한다.
		headers.set("Authorization", key);

		// headers를 HttpEntity 객체를 생성한 후 저장한다.
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String encode = null;
		System.out.println(addr.split("/")[0]);

		try {
			// 입력받은 지역 정보를 UTF-8로 인코딩한다.
			encode = URLEncoder.encode(addr.split("/")[0] + "도서관", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(encode);

		// 사용할 api URL
		String rawurl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + encode;
		URI uri = null;
		System.out.println(rawurl);
		try {
			uri = new URI(rawurl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println(uri);

		// api를 호출하기위해서 exchange()메소드 사용
		ResponseEntity<String> res = rest.exchange(uri, HttpMethod.GET, entity, String.class);

		// JSON 파서 객체 생성
		JSONParser jsonParser = new JSONParser();
		JSONObject body = null;
		try {
			// res에 저장된 정보를 getBody()를 통해서 가져오고 toString()을 통해
			// 스트링으로 변환하고 JSONObject에 저장한다.
			body = (JSONObject) jsonParser.parse(res.getBody().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// JSONArray객체를 생성한 후, body에 저장된 내용중에 "documents" 라는 이름을 가지고 있는 부분을 가져온다.
		JSONArray docu = (JSONArray) body.get("documents");
		System.out.println(docu.size());
//		System.out.println(docu);
//		System.out.println(docu.size());
		// 카카오 지도 api에서 검색을 할 경우 지정된 위치에서 가장 가까운 곳부터 순서대로 가져오기때문에
		// 거리순으로 추천할 경우에 따로 수정할 부분이 없다고 사료된다.
		// 배열에 저장된 데이터를 가져오기 위해 반복문을 사용하여 저장된 데이터를 순서대로 가져온다.
		for (int i = 0; i < docu.size(); i++) {
			JSONObject asd = (JSONObject) docu.get(i);
			// 저장된 부분중에 place_name을 가져온다.
//			System.out.println(asd.get("place_name"));
//			System.out.println(asd.get("x"));
//			System.out.println(asd.get("y"));
			MapDTO a = new MapDTO();
			String asdc = "\"" + (String) asd.get("place_name") + "\"";

			a.setName(asdc);
			a.setX((String) asd.get("x"));
			a.setY((String) asd.get("y"));
			a.setUrl("\"" + (String) asd.get("place_url") + "\"");
			a.setPhone("\"" + (String) asd.get("phone") + "\"");
			a.setAddress("\"" + (String) asd.get("road_address_name") + "\"");
			mapdtolist.add(a);
		}

		return mapdtolist;
	}

	public List<String> GetCenterX(String user_id) {
		centerxy = new ArrayList<String>();
		String addr = dao.getAddr(user_id).substring(6);
		addr = addr.split("/")[0];
		System.out.println(addr);

		// RestTemplate 객체 생성
		RestTemplate rest = new RestTemplate();

		// HttpHeaders 객체 생성
		HttpHeaders headers = new HttpHeaders();

		// hearders에 ContentType을 Json으로 설정한다.
		headers.setContentType(MediaType.APPLICATION_JSON);

		// 사용할 api 키
		String key = "KakaoAK dacb438d04968f33fd5fcc9f9a1c6cd2";

		// headers 에 이름이 "Authorization"인 부분을 키로 설정한다.
		headers.set("Authorization", key);

		// headers를 HttpEntity 객체를 생성한 후 저장한다.
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String encode = null;

		try {
			// 입력받은 지역 정보를 UTF-8로 인코딩한다.
			encode = URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		System.out.println(encode);
		String address = "https://dapi.kakao.com/v2/local/search/address.json?query=" + encode;

		URI uri = null;
		try {
			uri = new URI(address);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println(uri);

		// api를 호출하기위해서 exchange()메소드 사용
		ResponseEntity<String> res = rest.exchange(uri, HttpMethod.GET, entity, String.class);

		// JSON 파서 객체 생성
		JSONParser jsonParser = new JSONParser();
		JSONObject body = null;
		try {
			// res에 저장된 정보를 getBody()를 통해서 가져오고 toString()을 통해
			// 스트링으로 변환하고 JSONObject에 저장한다.
			body = (JSONObject) jsonParser.parse(res.getBody().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// JSONArray객체를 생성한 후, body에 저장된 내용중에 "documents" 라는 이름을 가지고 있는 부분을 가져온다.
		JSONArray docu = (JSONArray) body.get("documents");
		System.out.println(docu.size());
		System.out.println(docu);
		if (docu.size() == 0) {

		} else {
			for (int i = 0; i < docu.size(); i++) {
				JSONObject asd = (JSONObject) docu.get(i);

				centerxy.add((String) asd.get("x"));
				centerxy.add((String) asd.get("y"));

			}
		}

		
		return centerxy;
	}
	@Override
	public String getaddrService(String user_id) {
		// TODO Auto-generated method stub
		return dao.getAddr(user_id);
	}
}
