<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<section class="subpage_wrap">
	<div class="inner">
		<h2>나의 도서관</h2>
		<div class="contents my_area">
			<p class="books_tab">
				<a class="books_tab_1" href="${pageContext.request.contextPath}/my">회원정보 관리</a>
				<span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<a class="books_tab_2 active" href="${pageContext.request.contextPath}/my/record">도서관 이용 이력</a>
				<span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<br />
				<a class="books_tab_3 loading_btn" href="${pageContext.request.contextPath}/my/recommend">맞춤도서추천</a>
				<span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<a class="books_tab_4" href="${pageContext.request.contextPath}/my/map">가까운 도서관</a>
			</p>

			<!-- 맞춤도서추천 -->
			<div class="my_tab_cont_2 active">

				<h4>대출·예약 도서</h4>

				<div class="ing_record">
					<c:if test="${empty cbdto}">
						<p class="no_record">대출·예약 도서가 없습니다.</p>
					</c:if>

					<c:forEach var="cbdto" items="${cbdto }" varStatus="status">
						<div class="record">
							<h4>
								<a href="#">${cbdto.title }</a>
							</h4>
							<!-- 예약중일 때 나오는 표 -->
							<c:choose>
								<c:when test="${fn:contains(cbdto.borrow_state, '예약중')}">
									<ul>
										<li>
											상태 :
											<span>${cbdto.borrow_state }</span>
										</li>
										<li>
											예약 예정일 :
											<c:set var="borrowDate">
												<fmt:formatDate value="${cbdto.borrow_date }" pattern="yyyy년 M월 d일" />
											</c:set>
											<span>${borrowDate }</span>
										</li>
										<li>
											반납 예정일 :
											<c:set var="returnDueDate">
												<fmt:formatDate value="${cbdto.return_due_date }" pattern="yyyy년 M월 d일" />
											</c:set>
											<span>${returnDueDate }</span>
										</li>
										<li>
											대출연장여부 :
											<span>해당사항 없음</span>
										</li>
									</ul>
								</c:when>
								<c:otherwise>
									<!-- 대출중일 때 나오는 표 -->
									<ul>
										<li>
											상태 :
											<span>${cbdto.borrow_state }</span>
										</li>
										<li>
											대출일 :
											<c:set var="borrowDate">
												<fmt:formatDate value="${cbdto.borrow_date }" pattern="yyyy년 M월 d일" />
											</c:set>
											<span>${borrowDate }</span>
										</li>
										<li>
											반납예정일 :
											<c:set var="returnDueDate">
												<fmt:formatDate value="${cbdto.return_due_date }" pattern="yyyy년 M월 d일" />
											</c:set>
											<span>${returnDueDate }</span>
										</li>
										<li>
											대출연장여부 :
											<span>${cbdto.return_extend }</span>
										</li>
									</ul>
								</c:otherwise>
							</c:choose>
							<div class="record_btn">
								<!-- 도서 연장 신청 -->
								<c:if test="${fn:contains(cbdto.borrow_state, '대출중') && fn:contains(cbdto.return_extend, 'N')}">
									<form id="returnForm_${cbdto.borrow_keynum}" method="post" action="return">
										<input type="hidden" name="borrow_keynum" value="${cbdto.borrow_keynum }" />
										<button class="record_btn_2" type="submit">반납하기</button>
									</form>

									<form id="extendForm_${cbdto.borrow_keynum}" method="post" action="extend">
										<input type="hidden" name="borrow_keynum" value="${cbdto.borrow_keynum }" />
										<input type="hidden" name="book_keynum" value="${cbdto.book_keynum }" />
										<button class="record_btn_2 record_btn_color" type="submit">대출연장</button>
									</form>
								</c:if>

								<!-- 도서 연장 신청 -->
								<c:if test="${fn:contains(cbdto.borrow_state, '대출중') && fn:contains(cbdto.return_extend, 'Y')}">
									<form id="returnForm_${cbdto.borrow_keynum}" method="post" action="return">
										<input type="hidden" name="borrow_keynum" value="${cbdto.borrow_keynum }" />
										<button type="submit">반납하기</button>
									</form>
								</c:if>

								<!-- 예약 취소 신청 -->
								<c:if test="${fn:contains(cbdto.borrow_state, '예약중')}">
									<form id="cancelForm_${cbdto.borrow_keynum}" method="post" action="cancelReserve">
										<input type="hidden" name="borrow_keynum" value="${cbdto.borrow_keynum }" />
										<input type="hidden" name="book_keynum" value="${cbdto.book_keynum }" />
										<button type="submit" class="record_btn_color">예약취소</button>
									</form>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>

				<h4>반납 도서</h4>

				<div class="user_record_table">
					<table class="record_table">
						<thead>
							<tr>
								<th>도서명</th>
								<th>저자</th>
								<th>대출일</th>
								<th>반납일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pbdto" items="${pbdto }" varStatus="status">
								<tr>
									<c:if test="${empty pbdto}">
										<td colspan="4">반납 도서가 없습니다.</td>
									</c:if>
								</tr>
								<tr>
									<td>
										<a href="${pageContext.request.contextPath}/books/view?page=${pbdto.book_keynum}">${pbdto.title }</a>
									</td>
									<td>${pbdto.author}</td>
									<c:set var="borrowDate">
										<fmt:formatDate value="${pbdto.borrow_date }" pattern="yyyy년 M월 d일" />
									</c:set>
									<td>${borrowDate }</td>
									<c:set var="returnDate">
										<fmt:formatDate value="${pbdto.return_date }" pattern="yyyy년 M월 d일" />
									</c:set>
									<td>${returnDate }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="pagination_area">
					<ul class="pagination">
						<c:choose>
							<c:when test="${not empty pv}">
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
							</c:when>

						</c:choose>


					</ul>
				</div>
			</div>

		</div>
	</div>
</section>



