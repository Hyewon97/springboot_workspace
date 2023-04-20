package map.controller;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

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

public class rea {
	ArrayList<String> xlist;
	ArrayList<String> ylist;
	ArrayList<String> namelist;
	ArrayList<String> centerx;
	ArrayList<String> centery;
	ArrayList<String> centeraddress;
	ArrayList<String> urllist;
	ArrayList<String> phonelist;
	ArrayList<String> addresslist;
	ArrayList<String> roadlist;

	public rea() {
		GetAddress ga= new GetAddress();
		xlist = new ArrayList<String>();
		ylist = new ArrayList<String>();
		namelist = new ArrayList<String>();
		centerx = new ArrayList<String>();
		centery = new ArrayList<String>();
		centeraddress = new ArrayList<String>();
		
		urllist = new ArrayList<String>();
		phonelist= new ArrayList<String>();
		addresslist= new ArrayList<String>();
		roadlist=new ArrayList<String>();
		getmapcenter gmc = new getmapcenter(ga.GetAdr().get(0));
		centeraddress = gmc.centeraddress;
		centerx = gmc.centerx;
		centery = gmc.centery;
		  
		String city = centeraddress.get(0)+ " 도서관";
//		String city= "인천광역시 서구 도서관";
		System.out.println(city);
		// RestTemplate 은 스프링에서 제공하는 내장 클래스로
		// Rest api를 간편하게 호출하기위해 제공되는 클래스이다.

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
			encode = URLEncoder.encode(city, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		System.out.println(encode);

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
		System.out.println(docu);
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
			String asdc = "\"" + (String) asd.get("place_name") + "\"";
			namelist.add(asdc);

			xlist.add((String) asd.get("x"));
			ylist.add((String) asd.get("y"));
			urllist.add("\""+(String)asd.get("place_url")+"\"");
			phonelist.add("\""+(String)asd.get("phone")+"\"");
			addresslist.add("\""+(String)asd.get("address_name")+"\"");
			roadlist.add("\""+(String)asd.get("road_address_name")+"\"");
		}

	}

	public ArrayList<String> GetXlist() {

		return xlist;
	}

	public ArrayList<String> GetNamelist() {
		return namelist;
	}

	public ArrayList<String> GetYlist() {
		return ylist;
	}

	public ArrayList<String> GetCenterx() {
		return centerx;
	}

	public ArrayList<String> GetCentery() {
		return centery;
	}

	public ArrayList<String> GetCenteraddress() {
		return centeraddress;
	}
	public ArrayList<String> GetUrllist(){
		return urllist;
	}
	public ArrayList<String> GetPhonelist(){
		return phonelist;
	}
	public ArrayList<String> GetAddresslist(){
		return addresslist;
	}
	public ArrayList<String> GetRoadlist(){
		return roadlist;
	}

}
