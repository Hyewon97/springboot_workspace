<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- /myapp 현재프로젝트 경로 리턴 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- 공지사항 리스트 -->
<section class="subpage_wrap notice_wrap">
	<div class="inner">
		<h2>도서관 소식</h2>

		<!-- 
		<c:choose>
			<c:when test="${not empty searchPv}">
				조회갯수 : ${totalCount}개
			</c:when>
		</c:choose>
		 -->

		<div class="notice_search_area">
			<form name="searchNoticefrm" id="searchNoticefrm" action="${pageContext.request.contextPath}/notice/search" method="get">
				<select class="form-select" name="option" id="option">
					<c:choose>
						<c:when test="${option eq 'title'}">
							<option value="title" selected="selected">제목</option>
							<option value="content">내용</option>
						</c:when>
						<c:when test="${option eq 'content'}">
							<option value="title">제목</option>
							<option value="content" selected="selected">내용</option>
						</c:when>
						<c:otherwise>
							<option value="title" selected="selected">제목</option>
							<option value="content">내용</option>
						</c:otherwise>
					</c:choose>
				</select>
				<c:choose>
					<c:when test="${empty query}">
						<input type="text" class="form-control" placeholder="검색어를 입력하세요." name="searchword" id="searchword" value="">
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" placeholder="검색어를 입력하세요." name="searchword" id="searchword" value="${query}">
					</c:otherwise>
				</c:choose>
				<button class="btn btn-outline-primary" type="button" id="btnNoticeSearch">
					<img src="/myapp/resources/assets/images/search_icon.png" alt="돋보기 검색 아이콘">
				</button>
			</form>
		</div>

		<div class="contents">
			<div class="notice_top">
				<p>NO</p>
				<p>제목</p>
				<p>작성자</p>
				<p>작성일</p>
				<p>조회</p>
			</div>

			<c:if test="${totalCount==0}">
				<p class="not_cont">조회된 게시글이 없습니다.</p>
			</c:if>
			<div>
				<c:forEach items="${topList}" var="tdto">
					<div class="notice_Y ${!empty tdto.upload_img ? 'img' : ''} ${!empty tdto.upload_file ? 'clip' : ''}">
						<p>-</p>
						<p>
							<c:url var="path" value="info">
								<c:param name="currentPage" value="${pv.currentPage}" />
								<c:param name="num" value="${tdto.num}" />
							</c:url>
							<a href="${path}">
								<b>${tdto.title}</b>
							</a>
						</p>
						<p>관리자</p>
						<p>${tdto.reg_date}</p>
						<p>${tdto.readcount}</p>
					</div>
				</c:forEach>

				<c:forEach items="${topList}" var="tdto">
					<div class="notice_Y_mob">
						<h3>
							<c:url var="path" value="info">
								<c:param name="currentPage" value="${pv.currentPage}" />
								<c:param name="num" value="${tdto.num}" />
							</c:url>
							<a href="${path}">
								<b>${tdto.title}</b>
							</a>
						</h3>
						<p>관리자 ｜ ${tdto.reg_date} | ${tdto.readcount}</p>
					</div>
				</c:forEach>

				<c:set var="num" value="${Number}" />
				<c:forEach items="${aList}" var="dto">
					<div class="notice_N ${!empty dto.upload_img ? 'img' : ''} ${!empty dto.upload_file ? 'clip' : ''}">
						<p>${num}</p>
						<p>
				            <c:choose>
				               <c:when test="${not empty searchPv}">
				                     <c:url var="path" value="info">
				                     <c:param name="currentPage" value="${searchPv.currentPage}" />
				                        <c:param name="num" value="${dto.num}" />
				                     </c:url>
				               </c:when>
				               <c:otherwise>
				                  <c:url var="path" value="info">
				                     <c:param name="currentPage" value="${pv.currentPage}" />
				                        <c:param name="num" value="${dto.num}" />
				                  </c:url>
				               </c:otherwise>
				            </c:choose>

							<a href="${path}"> ${dto.title}</a>

						</p>
						<p>관리자</p>
						<p>${dto.reg_date}</p>
						<p>${dto.readcount}</p>
					</div>
					<c:set var="num" value="${num-1}"></c:set>
				</c:forEach>

				<c:set var="num" value="${Number}" />
				<c:forEach items="${aList}" var="dto">
					<div class="notice_N_mob">
						<h3>
				            <c:choose>
				               <c:when test="${not empty searchPv}">
				                     <c:url var="path" value="info">
				                     <c:param name="currentPage" value="${searchPv.currentPage}" />
				                        <c:param name="num" value="${dto.num}" />
				                     </c:url>
				               </c:when>
				               <c:otherwise>
				                  <c:url var="path" value="info">
				                     <c:param name="currentPage" value="${pv.currentPage}" />
				                        <c:param name="num" value="${dto.num}" />
				                  </c:url>
				               </c:otherwise>
				            </c:choose>

							<a href="${path}"> ${dto.title}</a>
						</h3>
						<p>관리자 ｜ ${dto.reg_date} | ${dto.readcount}</p>
					</div>
					<c:set var="num" value="${num-1}"></c:set>
				</c:forEach>

			</div>

			<c:choose>
				<c:when test="${not empty searchPv}">
					<div class="pagination_area">
						<ul class="pagination">
							<!-- 처음 출력 시작 -->
							<c:choose>
								<c:when test="${searchPv.startPage >1}">
									<li class="page-item disabled">
										<a class="page-link" style="cursor: pointer;" href="search?option=${option}&searchword=${query}&currentPage=1">
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
								<c:when test="${searchPv.startPage >1}">
									<li class="page-item disabled">
										<a class="page-link" style="cursor: pointer;" href="search?option=${option}&searchword=${query}&currentPage=${searchPv.startPage-searchPv.blockPage}">
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
							<c:forEach var="i" begin="${searchPv.startPage}" end="${searchPv.endPage}">
								<c:choose>
									<c:when test="${i==searchPv.currentPage}">
										<li class="page-item active">
											<a class="page-link" style="cursor: pointer;" href="search?option=${option}&searchword=${query}&currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" style="cursor: pointer;" href="search?option=${option}&searchword=${query}&currentPage=${i}">${i}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 페이지번호 출력 끝 -->

							<!-- 다음 출력 시작 -->
							<c:choose>
								<c:when test="${searchPv.endPage < searchPv.totalPage}">
									<li class="page-item">
										<a class="page-link" style="cursor: pointer;" href="search?option=${option}&searchword=${query}&currentPage=${searchPv.startPage + searchPv.blockPage}">
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
								<c:when test="${searchPv.endPage < searchPv.totalPage}">
									<li class="page-item">
										<a class="page-link" style="cursor: pointer;" href="search?option=${option}&searchword=${query}&currentPage=${searchPv.totalPage}">
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
							<!-- 마지막 출력 끝 -->
						</ul>
					</div>
				</c:when>

				<c:when test="${empty searchPv and not empty pv}">
					<div class="pagination_area">
						<ul class="pagination">
							<!-- 처음 출력 시작 -->
							<c:choose>
								<c:when test="${pv.startPage >1}">
									<li class="page-item disabled">
										<a class="page-link" style="cursor: pointer;" href="notice?currentPage=1">
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
										<a class="page-link" style="cursor: pointer;" href="notice?currentPage=${pv.startPage-pv.blockPage}">
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
											<a class="page-link" style="cursor: pointer;" href="notice?currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" style="cursor: pointer;" href="notice?currentPage=${i}">${i}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 페이지번호 출력 끝 -->

							<!-- 다음 출력 시작 -->
							<c:choose>
								<c:when test="${pv.endPage < pv.totalPage}">
									<li class="page-item">
										<a class="page-link" style="cursor: pointer;" href="notice?currentPage=${pv.startPage + pv.blockPage}">
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
										<a class="page-link" style="cursor: pointer;" href="notice?currentPage=${pv.totalPage}">
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
							<!-- 마지막 출력 끝 -->
						</ul>
					</div>
				</c:when>
			</c:choose>

		</div>
	</div>
</section>

<script>
	$(document).ready(function() {

		//게시글검색
		$('#btnNoticeSearch').click(function() {
			$('#searchNoticefrm').submit();
		});

	});
</script>