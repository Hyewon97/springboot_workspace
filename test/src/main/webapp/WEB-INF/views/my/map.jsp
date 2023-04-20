<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<section class="subpage_wrap">
	<div class="inner">
		<h2>나의 도서관</h2>
		<div class="contents my_area">
			<p class="books_tab">
				<a class="books_tab_1" href="${pageContext.request.contextPath}/my">회원정보 관리</a> <span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<a class="books_tab_2" href="${pageContext.request.contextPath}/my/record">도서관 이용 이력</a> <span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<br /> <a class="books_tab_3 loading_btn" href="${pageContext.request.contextPath}/my/recommend">맞춤도서추천</a> <span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<a class="books_tab_4 active" href="${pageContext.request.contextPath}/my/map">가까운 도서관</a>
			</p>

			<!-- 가까운 도서관 -->
			<div class="my_tab_cont_4 active">
				<div class="map_area" id="map" style="width: 100% height: auto;"></div>

				<c:if test="${!empty mapdtos }">
					<div class="map_table">
						<table class="table_v1">
							<thead>
								<tr>
									<th>#</th>
									<th>도서관</th>
									<th>주소</th>
									<th>전화번호</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty mapdtos }">
									<c:forEach var="dto" items="${mapdtos }" varStatus="status">
										<tr>
											<td>${status.count}</td>

											<td><a href=${dto.url }>${dto.name.replace('"', '') }</a>
											</td>
											<td>${dto.address.replace('"', '') }</td>
											<td>${ dto.phone.replace('"', '') == '' ? '-' : dto.phone.replace('"', '') }</td>
										</tr>
										<span style="display: none;" id="x${status.count}">${dto.x }</span>
										<span style="display: none;" id="y${status.count}">${dto.y }</span>
										<span style="display: none;" id="name${status.count}">${dto.name }</span>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</c:if>

				<c:if test="${empty mapdtos }">
					<p class="map_nodata">회원님의 주소 범위 내 도서관이 존재하지 않습니다.</p>
				</c:if>

				<span style="display: none;" id="centerx">${x }</span> <span
					style="display: none;" id="centery">${y }</span>
			</div>

		</div>
	</div>
</section>

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=eeb3697ba67b965b981c89f05428889e"></script>
<script>
	var size = parseInt("${mapdtos.size() }");

	var centery = document.getElementById('centery');
	var centerx = document.getElementById('centerx');

	var infowindow = new kakao.maps.InfoWindow({
		zIndex : 1
	});
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center : new kakao.maps.LatLng(centery.textContent, centerx.textContent), // 지도의 중심좌표
		level : 5
	// 지도의 확대 레벨
	};

	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);

	/* for(var i =0; i<namelist.length; i++){
		console.log(namelist[i])
	} */

	// 마커 찍는 함수 
	var name = "name";
	var x = "x";
	var y = "y";

	for (var i = 1; i < size + 1; i++) {
		var nameval = name + i;
		var xval = x + i;
		var yval = y + i;
		var gn = document.getElementById(nameval);
		var gx = document.getElementById(xval);
		var gy = document.getElementById(yval);
		var contn = gn.textContent;
		var contx = gx.textContent;
		var conty = gy.textContent;

		displayMarker(i-1, contx, conty, contn);
	}

	function displayMarker(idx, place_x, place_y, place_name) {

		// 마커 이미지의 이미지 크기 입니다
		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', imageSize = new kakao.maps.Size(
				36, 37), imgOptions = {
			spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
			spriteOrigin : new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			offset : new kakao.maps.Point(13, 37)
		// 마커 좌표에 일치시킬 이미지 내에서의 좌표
		};
		// 마커 이미지를 생성합니다    
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
				imgOptions);
		// 마커를 생성하고 지도에 표시합니다
		var marker = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(place_y, place_x),
			title : place_name,
			image : markerImage
		});
		// 마커에 클릭이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'click', function() {
			// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			infowindow.setContent('<div style="padding:5px;font-size:12px;">'
					+ place_name + '</div>');
			infowindow.open(map, marker);
		});
		function displayInfowindow(marker, title) {
			var content = '<div style="padding:5px;z-index:1;">' + title
					+ '</div>';
			infowindow.setContent(content);
			infowindow.open(map, marker);
		}
	}
</script>


