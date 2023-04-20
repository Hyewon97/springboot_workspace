<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 희망도서신청 -->
<section class="subpage_wrap">
	<div class="inner">
		<h2>희망도서신청</h2>
		<div class="contents request_area">
		
			<p class="books_tab">
				<a class="books_tab_1" href="${pageContext.request.contextPath}/request/request">도서신청</a>
				<a class="books_tab_2 active" href="${pageContext.request.contextPath}/request/list">신청내역</a>
			</p>

			<div class="request_tab_cont_2 active">

				<div class="request_list_area">
					<dl class="request_list_title">
						<dt>NO</dt>
						<dt>도서명</dt>
						<dt>저자</dt>
						<dt>신청일</dt>
						<dt>신청상태</dt>
					</dl>
				<c:if test="${totalCount >=1}">
					<c:set var="num" value="${Number}" />
					<c:forEach items="${aList}" var="dto">
						<dl class="request_list_cont">
							<dt>${num}</dt>
							<dd>${dto.title}</dd>
							<dd>${dto.author}</dd>
							<dd>${dto.request_date}</dd>
							<dd>
								<c:choose>
									<c:when test='${dto.request_state == "Y"}'>
										수락
									</c:when>
									<c:when test='${dto.request_state == "N"}'>
										반려
									</c:when>
									<c:otherwise>
										미처리
									</c:otherwise>
								</c:choose>
							</dd>
						</dl>
						<c:set var="num" value="${num-1}"></c:set>
					</c:forEach>
				</c:if>
				</div>

				<c:if test="${totalCount >=1}">
				<div class="pagination_area">
					<ul class="pagination">
						<!-- 처음 출력 시작 -->
						<c:choose>
							<c:when test="${pv.startPage >1}">
								<li class="page-item disabled"><a class="page-link"
									style="cursor: pointer;" href="list?currentPage=1"> <img
										style="opacity: .5;"
										src="/myapp/resources/assets/images/first.svg"> <span>first</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled"><a class="page-link"
									style="cursor: no-drop"> <img style="opacity: .2;"
										src="/myapp/resources/assets/images/first.svg"> <span>first</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 처음 출력 끝 -->

						<!-- 이전 출력 시작 -->
						<c:choose>
							<c:when test="${pv.startPage >1}">
								<li class="page-item disabled"><a class="page-link"
									style="cursor: pointer;"
									href="list?currentPage=${pv.startPage-pv.blockPage}"> <img
										style="opacity: .5;"
										src="/myapp/resources/assets/images/prev.svg"> <span>prev</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled"><a class="page-link"
									style="cursor: no-drop"> <img style="opacity: .2;"
										src="/myapp/resources/assets/images/prev.svg"> <span>prev</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 이전 출력 끝 -->

						<!-- 페이지번호 출력 시작 -->
						<c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}">
							<c:choose>
								<c:when test="${i==pv.currentPage}">
									<li class="page-item active"><a class="page-link"
										style="cursor: pointer;" href="list?currentPage=${i}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;" href="list?currentPage=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 페이지번호 출력 끝 -->

						<!-- 다음 출력 시작 -->
						<c:choose>
							<c:when test="${pv.endPage < pv.totalPage}">
								<li class="page-item"><a class="page-link"
									style="cursor: pointer;"
									href="list?currentPage=${pv.startPage + pv.blockPage}">
										<img style="opacity: .5;"
										src="/myapp/resources/assets/images/next.svg"> <span>next</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									style="cursor: no-drop"> <img style="opacity: .2;"
										src="/myapp/resources/assets/images/next.svg"> <span>next</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 다음 출력 끝 -->

						<!-- 마지막 출력 시작 -->
						<c:choose>
							<c:when test="${pv.endPage < pv.totalPage}">
								<li class="page-item"><a class="page-link"
									style="cursor: pointer;"
									href="list?currentPage=${pv.totalPage}"> <img
										style="opacity: .5;"
										src="/myapp/resources/assets/images/last.svg"> <span>last</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									style="cursor: no-drop"> <img style="opacity: .2;"
										src="/myapp/resources/assets/images/last.svg"> <span>last</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<!-- 마지막 출력 끝 -->
					</ul>
				</div>
				</c:if>			
			
			<c:if test="${totalCount == 0}">
				<div class="request_nodata">도서신청 정보가 존재하지 않습니다.</div>
			</c:if>
			</div>

		</div>
	</div>
</section>


