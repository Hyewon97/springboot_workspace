
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="subpage_wrap">
	<div class="inner">
		<h2>나의 도서관</h2>
		<div class="contents my_area">
			<p class="books_tab">
				<a class="books_tab_1" href="${pageContext.request.contextPath}/my">회원정보 관리</a>
				<span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<a class="books_tab_2" href="${pageContext.request.contextPath}/my/record">도서관 이용 이력</a>
				<span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<br />
				<a class="books_tab_3 loading_btn active" href="${pageContext.request.contextPath}/my/recommend">맞춤도서추천</a>
				<span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<a class="books_tab_4" href="${pageContext.request.contextPath}/my/map">가까운 도서관</a>
			</p>

			<!-- 성별, 연령도서추천 -->
			<div class="my_tab_cont_3 active recommend_area">

				<!-- 도서 큐레이팅 -->
				<c:choose>
					<c:when test="${borrow_count == 0}">
						<h4 style="margin: 120px 0;">아직 반납하신 도서가 없습니다. 책을 읽고 도서를 추천받아보세요</h4>
					</c:when>

					<c:when test="${borrow_count != 0}">
						<h4>
							최근 읽은 "
							<span>${lastBookTitle}</span>
							"(와)과
							<br />
							유사한 도서입니다.
						</h4>

						<div class="recommend_book_area">
							<c:forEach items="${recommendList}" var="dto">
								<div class="recommend_book">
									<a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum} ">
										<img src="${dto.thumbnail}" alt="책 표지" />
									</a>

									<ul>
										<li>
											<a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum} ">${dto.title}</a>
										</li>
										<li>${dto.author}</li>
									</ul>
								</div>
							</c:forEach>
						</div>
					</c:when>
				</c:choose>


				<h4>
					${user_name}님과 유사한
					<span>${user_age} ${user_sex}</span>
					의
					<br />
					인기도서입니다.
				</h4>


				<div class="recommend_list_area">

					<dl class="recommend_list_title">
						<dt>도서명</dt>
						<dt>저자</dt>
						<dt>-</dt>
					</dl>
					<c:forEach items="${recbookList }" var="dto">
						<dl class="recommend_list_cont">
							<dd>${dto.title }</dd>
							<dd>${dto.author.replace(";", "").replace("지은이: ", "").replace(" 옮긴이: ", ", ") }</dd>
							<dd>
								<form method="GET" action="/myapp/books/search">
									<input type="hidden" name="search_item" value="search_isbn" />
									<input type="hidden" name="categories" value="a" />
									<input type="hidden" name="query" value="${dto.isbn }" />
	
									<button type="submit">도서조회</button>
								</form>
							</dd>
						</dl>
					</c:forEach>
				</div>

			</div>
		</div>
	</div>
</section>