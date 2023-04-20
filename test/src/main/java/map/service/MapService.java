package map.service;

import java.util.List;

import map.dto.MapDTO;

public interface MapService {
	
	//주변 도서관 검색
	public List<MapDTO> getlibs(String addr);
	
	//xy값 가져옴 지도 중심좌표를 지정
	public List<String> GetCenterX(String a);
	
	//저장된 주소값
	public String getaddrService(String user_id);
}
