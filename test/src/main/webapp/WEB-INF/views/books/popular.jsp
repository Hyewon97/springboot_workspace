<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 통합도서조회 -->
<section class="subpage_wrap">
	<div class="inner">
		<h2>통합도서조회</h2>
		<div class="contents books_cont">

			<p class="books_tab">
				<a class="books_tab_1"
					href="${pageContext.request.contextPath}/books">도서조회</a> · <a
					class="books_tab_2"
					href="${pageContext.request.contextPath}/books/new">신착도서</a> · <a
					class="books_tab_3 active"
					href="${pageContext.request.contextPath}/books/popular">인기도서</a>
			</p>


			<div class="books_tab_cont_3 active">

				<!-- 인기도서 -->
				<div class="booklist_box">
					<br/><br/>
					<c:forEach var="dto" items="${pbooklist }" varStatus="index">
						<div class="list">
							<a
								href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
                                <span class="best_tag">${index.index + 1 }</span>
								<img src="${dto.thumbnail }" alt="책 표지" />
							</a>
							<div class="list_text">
								<a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
									<h4>${dto.title}</h4>
								</a>
								<p>
									저자 : ${dto.author }<br /> 출판사 :
									${dto.publisher }<br />
									<c:choose>
										<c:when test="${dto.avg_rating != null }">
													평점 : <span>★${dto.avg_rating.charAt(0)}</span> (${dto.countisbn })
												</c:when>

										<c:otherwise>
													평점 : <span>★0</span>(0)
												</c:otherwise>
									</c:choose>


								</p>
								<p>${dto.contents }</p>
							</div>
							<p>
								대출 : <span>${dto.loan_state == 'Y' ? '가능' : '불가능' }</span> <br> 예약 : <span>${dto.borrow_state == 'Y' ? '가능' : '불가능' }</span>
							</p>
						</div>
					</c:forEach>

				</div>


			</div>
		</div>
	</div>
</section>