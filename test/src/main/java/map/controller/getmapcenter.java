package map.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class getmapcenter {
	ArrayList<String> centerx;
	ArrayList<String> centery;
	ArrayList<String> centeraddress;
	public getmapcenter() {
		// TODO Auto-generated constructor stub
	}
	public getmapcenter(String a) {
		centerx = new ArrayList<String>();
		centery = new ArrayList<String>();
		centeraddress = new ArrayList<String>();

//		Scanner sc = new Scanner(System.in);
//		String lat = "";
//		String lng = "";
//		// 입력
//		String a = sc.nextLine();


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
			encode = URLEncoder.encode(a, "UTF-8");
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

		for (int i = 0; i < docu.size(); i++) {
			JSONObject asd = (JSONObject) docu.get(i);

			centeraddress.add((String) asd.get("address_name"));
			centerx.add((String) asd.get("x"));
			centery.add((String) asd.get("y"));

		}
	}


}
