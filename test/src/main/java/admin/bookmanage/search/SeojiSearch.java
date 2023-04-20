package admin.bookmanage.search;

import java.io.BufferedInputStream;

import java.net.URL;

import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SeojiSearch {

	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		if (nValue == null)
			return null;

		return nValue.getNodeValue();
	}//getTagValue()
	
	
	//isbn을 넣으면 부가기호를 검색해주는 메서드
	public static String SearchCategory(String isbn) {
		
		String book_category ="";
		
		try {
			//isbn받아오기
			String encode = isbn;

			//서지정보url생성
			StringBuffer seojiSearch_url = new StringBuffer();
			
			String key= "4bdd93877b64c8f0a612cd9f463d77ba31b0d0739e1854d27ce5773838d31c2f";
			seojiSearch_url.append("https://www.nl.go.kr/seoji/SearchApi.do");
			seojiSearch_url.append("?cert_key="+key); //인증키
			//seojiSearch_url.append("&result_style=json");  //json
			seojiSearch_url.append("&result_style=xml");  //xml
			seojiSearch_url.append("&page_no=1&page_size=10");  //필수파라미터
			seojiSearch_url.append("&ebook_yn=N"); //오프라인도서만 검색
			seojiSearch_url.append("&isbn="+encode); //isbn검색
			
			//System.out.println("seojiSearch_url : "+seojiSearch_url);
			
			
			///////////////////////////////////////
			//xml파일(url) 파싱
			URL url = new URL(seojiSearch_url.toString());
			BufferedInputStream xmldata = new BufferedInputStream(url.openStream());
			Document documentInfo= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmldata);

			//xml 데이터 읽기
			NodeList aList = documentInfo.getElementsByTagName("e");
			//System.out.println("검색갯수 : "+aList.getLength()); 
			for (int i=0; i<aList.getLength(); i++) {
				Node nNode = aList.item(i);
				if(nNode.getNodeType()==Node.ELEMENT_NODE) {
					Element eElement=(Element) nNode;
			
					//변수에 담기
					book_category = getTagValue("EA_ADD_CODE", eElement).substring(2);
					//System.out.println("부가기호 : "+ book_category);
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return book_category;
	}//SearchCategory
}//class
												