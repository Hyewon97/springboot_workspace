<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main -->
<section class="main_area">
	<article class="total_search_area">
		<div class="inner">
			<p class="main_title">당신의 영원한 독서문화 공간</p>
			<div class="search_box">
				<!-- 도서 통합검색 -->
				<form action="books/search" method="get">
					<select name="search_item">
						<option value="search_title" selected="selected">도서명</option>
						<option value="search_author">저자명</option>
						<option value="search_publisher">발행처</option>
					</select> <input type="text" name="query" placeholder="검색어를 입력하세요." /> <input
						class="bn_input" name="categories" type="hidden" value="a" />
					<button type="submit">
						<img src="/myapp/resources/assets/images/search_icon.png"
							alt="돋보기 검색 아이콘" />
					</button>
				</form>
			</div>
		</div>
		<div id="particles-js"></div>
		<script type="text/javascript"
			src="/myapp/resources/js/particles.min.js"></script>
	</article>

	<article class="notice_area">
		<div class="inner">
			<c:url var="path" value="notice/info">
				<c:param name="num" value="${latestOne.num}" />
			</c:url>
			<a href="${path}"><span>Notice</span>${latestOne.title}</a>
		</div>
	</article>

	<article class="book_area best_book">
		<div class="inner">
			<h2>이번 달 인기 도서</h2>
			<div class="book_inner">
				<c:forEach var="dto" items="${plist}">
					<div class="book_box">
						<a
							href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
							<img src="${dto.thumbnail }" alt="책 표지">
							<h4>${dto.title }</h4>
							<p>${dto.author }</p>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</article>

	<article class="book_area new_book">
		<div class="inner">
			<h2>신착 도서</h2>
			<div class="book_inner">
				<c:forEach var="dto" items="${nlist}">
					<div class="book_box">
						<a
							href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
							<img src="${dto.thumbnail }" alt="책 표지">
							<h4>${dto.title }</h4>
							<p>${dto.author }</p>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</article>

	<article class="category_area">
		<div class="inner">
			<h2>- 분류별 도서 조회 -</h2>
			<div class="category_box">
				<a onclick="sendData('0')">
					<div>
						<p>
							<span>000</span><br />총류
						</p>
					</div>
				</a> <a onclick="sendData('1')">
					<div>
						<p>
							<span>100</span><br />철학
						</p>
					</div>
				</a> <a onclick="sendData('2')">
					<div>
						<p>
							<span>200</span><br />종교
						</p>
					</div>
				</a> <a onclick="sendData('3')">
					<div>
						<p>
							<span>300</span><br />사회과학
						</p>
					</div>
				</a> <a onclick="sendData('4')">
					<div>
						<p>
							<span>400</span><br />순수과학
						</p>
					</div>
				</a> <a onclick="sendData('5')">
					<div>
						<p>
							<span>500</span><br />기술과학
						</p>
					</div>
				</a> <a onclick="sendData('6')">
					<div>
						<p>
							<span>600</span><br />예술
						</p>
					</div>
				</a> <a onclick="sendData('7')">
					<div>
						<p>
							<span>700</span><br />언어
						</p>
					</div>
				</a> <a onclick="sendData('8')">
					<div>
						<p>
							<span>800</span><br />문학
						</p>
					</div>
				</a> <a onclick="sendData('9')">
					<div>
						<p>
							<span>900</span><br />역사
						</p>
					</div>
				</a>
				<form action="books/search" method="get">
					<input class="bn_input" name="categories" id="categories"
						type="hidden" value="a" /> <input class="bn_input" type="hidden"
						name="search_item" value="search_title" /> <input
						class="bn_input" type="hidden" name="query" value="" />
					<button type="submit" name="searchbutton" style="display: none;">

					</button>
				</form>
			</div>
		</div>
	</article>

	<article class="info_area">
		<div class="inner">
			<div class="notice">
				<h2>도서관 소식</h2>
				<c:forEach items="${latestFive}" var="tdto">
					<c:url var="path" value="notice/info">
						<c:param name="num" value="${tdto.num}" />
					</c:url>
					<a href="${path}">
						<p>${tdto.title}</p>
						<p>${tdto.reg_date}</p>
					</a>
				</c:forEach>
			</div>
			<div class="time">
				<h2>
					이용시간 <span>(평일 및 주말 공통)</span>
				</h2>
				<p>
					<span>09</span> : <span>00</span>&nbsp;&nbsp;~&nbsp;&nbsp;<span>22</span>
					: <span>00</span>
				</p>

				<h2>정기휴관일</h2>
				<p>
					※ 매주 월요일, 정부가 지정한 공휴일<br />※ 장서 및 시설 점검 등 관장이 필요하다고 인정하는 날
				</p>
			</div>
		</div>
	</article>
</section>
<script>
	function sendData(value) {
		var category = document.getElementById("categories");
		category.value = value;
		$('button[name="searchbutton"]').trigger('click');
	}
</script>