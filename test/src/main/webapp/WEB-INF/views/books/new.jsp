<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 통합도서조회 -->
<section class="subpage_wrap">
	<div class="inner">
		<h2>통합도서조회</h2>
		<div class="contents books_cont">

			<p class="books_tab">
				<a class="books_tab_1" href="${pageContext.request.contextPath}/books">도서조회</a>
				·
				<a class="books_tab_2 active" href="${pageContext.request.contextPath}/books/new">신착도서</a>
				·
				<a class="books_tab_3" href="${pageContext.request.contextPath}/books/popular">인기도서</a>
			</p>


			<!-- 신착도서 -->
			<div class="books_tab_cont_2 active">
				<div class="booklist_box">

					<c:forEach var="dto" items="${nbooklist }">
						<div class="list">
							<a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
								<img src="${dto.thumbnail }" alt="책 표지" />
							</a>
							<div class="list_text">
								<a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
									<h4>${dto.title}</h4>
								</a>
								<p>
									저자 : ${dto.author }
									<br />
									출판사 : ${dto.publisher }
									<br />
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
								대출 :
								<span>${dto.loan_state == 'Y' ? '가능' : '불가능' }</span>
								<br>
								예약 :
								<span>${dto.borrow_state == 'Y' ? '가능' : '불가능' }</span>
							</p>
						</div>
					</c:forEach>

				</div>

				<div class="pagination_area" style="width: 100%;">
					<ul class="pagination">
						<c:choose>
							<c:when test="${pv.startPage >1}">
								<li class="page-item disabled">
									<a class="page-link" style="cursor: pointer;" href="?currentPage=1">
										<img style="opacity: .5;" src="/myapp/resources/assets/images/first.svg">
										<span>first</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled">
									<a class="page-link" style="cursor: no-drop">
										<img style="opacity: .2;" src="/myapp/resources/assets/images/first.svg">
										<span>first</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
						<!-- 처음 출력 끝 -->

						<!-- 이전 출력 시작 -->
						<c:choose>
							<c:when test="${pv.startPage >1}">
								<li class="page-item disabled">
									<a class="page-link" style="cursor: pointer;" href="?currentPage=${pv.startPage-pv.blockPage}">
										<img style="opacity: .5;" src="/myapp/resources/assets/images/prev.svg">
										<span>prev</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled">
									<a class="page-link" style="cursor: no-drop">
										<img style="opacity: .2;" src="/myapp/resources/assets/images/prev.svg">
										<span>prev</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
						<!-- 이전 출력 끝 -->

						<!-- 페이지번호 출력 시작 -->
						<c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}">
							<c:choose>
								<c:when test="${i==pv.currentPage}">
									<li class="page-item active">
										<a class="page-link" style="cursor: pointer;" href="?currentPage=${i}">${i}</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link" style="cursor: pointer;" href="?currentPage=${i}">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 페이지번호 출력 끝 -->

						<!-- 다음 출력 시작 -->
						<c:choose>
							<c:when test="${pv.endPage < pv.totalPage}">
								<li class="page-item">
									<a class="page-link" style="cursor: pointer;" href="?currentPage=${pv.startPage + pv.blockPage}">
										<img style="opacity: .5;" src="/myapp/resources/assets/images/next.svg">
										<span>next</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" style="cursor: no-drop">
										<img style="opacity: .2;" src="/myapp/resources/assets/images/next.svg">
										<span>next</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
						<!-- 다음 출력 끝 -->

						<!-- 마지막 출력 시작 -->
						<c:choose>
							<c:when test="${pv.endPage < pv.totalPage}">
								<li class="page-item">
									<a class="page-link" style="cursor: pointer;" href="?currentPage=${pv.totalPage}">
										<img style="opacity: .5;" src="/myapp/resources/assets/images/last.svg">
										<span>last</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" style="cursor: no-drop">
										<img style="opacity: .2;" src="/myapp/resources/assets/images/last.svg">
										<span>last</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

			</div>
		</div>
	</div>
</section>