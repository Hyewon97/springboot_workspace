<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid mt-5 mb-5 content_area">
	<div class="card">
		<h5 class="card-header">도서후기관리</h5>
		<div class="card-body">

			<div class="row">
				<div class="col-md-6"></div>
				<div class="col-md-6">
					<form action="searchreview" method="get">
						<div class="input-group mb-3">
							<select class="form-select" name="option">
								<c:choose>
									<c:when test="${option eq 'title' }">
										<option value="title" selected="selected">도서명</option>
										<option value="id">작성회원아이디</option>
										<option value="name">작성회원이름</option>
									</c:when>

									<c:when test="${option eq 'id' }">
										<option value="title">도서명</option>
										<option value="id" selected="selected">작성회원아이디</option>
										<option value="name">작성회원이름</option>
									</c:when>

									<c:when test="${option eq 'name' }">
										<option value="title">도서명</option>
										<option value="id">작성회원아이디</option>
										<option value="name" selected="selected">작성회원이름</option>
									</c:when>
									<c:otherwise>
										<option value="title" selected="selected">도서명</option>
										<option value="id">작성회원아이디</option>
										<option value="name">작성회원이름</option>
									</c:otherwise>


								</c:choose>
							</select>


							<c:choose>
								<c:when test="${empty query }">
									<input type="text" class="form-control" name="search"
										placeholder="검색어를 입력하세요.">
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" name="search"
										placeholder="검색어를 입력하세요." value="${query }">
								</c:otherwise>
							</c:choose>
							<button class="btn btn-outline-primary" type="submit">검색</button>
						</div>
					</form>
				</div>

			</div>

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">ISBN</th>
						<th scope="col">도서명</th>
						<th scope="col">작성자 ID</th>
						<th scope="col">별점</th>
						<th scope="col">작성일자</th>
						<th scope="col">〓</th>
						<th scope="col">〓</th>
					</tr>
				</thead>
				<tbody>
					<!-- 반복 -->
					<c:choose>

						<c:when test="${totalcount eq 0 and empty revdtos }">
							<tr>
								<td colspan="8" class="text-center">등록된 후기가 없습니다..</td>
							</tr>
						</c:when>
						
						<c:when test="${not empty revdtos }">
						<c:set var="num" value="${number}" />
						
							<c:forEach var="dto" items="${revdtos }">

								<tr>
									<th class="align-middle" scope="row">${num }</th>
									<td class="align-middle">${dto.isbn }</td>
									<td class="align-middle">${dto.title }</td>
									<td class="align-middle">${dto.user_id }</td>
									<td class="align-middle">${dto.star_num }</td>
									<td class="align-middle">${dto.review_date }</td>
									<td class="align-middle text-end">
										<button type="button" class="btn btn-warning fw-bold"
											data-bs-toggle="modal" data-bs-target="#bookReviewModal${num }">상세</button>

										<div class="modal fade" id="bookReviewModal${num }"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5">도서 후기 상세</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body text-start">
														<ul class="bookreview_box">
															<li>ISBN : <span>${dto.isbn }</span></li>
															<li>도서명 : <span>${dto.title }</span></li>
															<li>작성자 ID : <span>${dto.user_id }</span></li>
															<li>작성일자 : <span>${dto.review_date }</span></li>
															<li>별점 : <span>${dto.star_num }</span></li>
															<li>후기내용 : <span>${dto.review_contents }</span></li>
														</ul>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">닫기</button>
													</div>
												</div>
											</div>
										</div>
									</td>
									<td class="align-middle text-end">
										<button type="button" class="btn btn-danger fw-bold"
											data-bs-toggle="modal"
											data-bs-target="#bookReviewDelModal${dto.review_keynum }">삭제</button>

										<div class="modal fade"
											id="bookReviewDelModal${dto.review_keynum }"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5">도서 후기 삭제</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body text-start">
														<p>선택하신 도서 후기를 삭제하시겠습니까?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">취소</button>

														<form name="frmBookDelete${dto.review_keynum}"
															id="frmBookDelete${dto.review_keynum}" method="get">
															<input type="hidden" name="review_keynum"
																value="${dto.review_keynum}" />
															<button type="button" class="btn btn-danger"
																onclick="btnBookDelete(${dto.review_keynum})">삭제</button>
														</form>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr>
								<c:set var="num" value="${num-1 }" />
							</c:forEach>
							
						</c:when>

						<c:when test="${not empty searchrevdtos }">
						<c:set var="num" value="${number }" />
							<c:forEach var="dto" items="${searchrevdtos }">

								<tr>
									<th class="align-middle" scope="row">${num }</th>
									<td class="align-middle">${dto.isbn }</td>
									<td class="align-middle">${dto.title }</td>
									<td class="align-middle">${dto.user_id }</td>
									<td class="align-middle">${dto.star_num }</td>
									<td class="align-middle">${dto.review_date }</td>
									<td class="align-middle text-end">
										<button type="button" class="btn btn-warning fw-bold"
											data-bs-toggle="modal" data-bs-target="#bookReviewModal">상세</button>

										<div class="modal fade" id="bookReviewModal"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5">도서 후기 상세</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body text-start">
														<ul class="bookreview_box">
															<li>ISBN : <span>${dto.isbn }</span></li>
															<li>도서명 : <span>${dto.title }</span></li>
															<li>작성자 ID : <span>${dto.user_id }</span></li>
															<li>작성일자 : <span>${dto.review_date }</span></li>
															<li>별점 : <span>${dto.star_num }</span></li>
															<li>후기내용 : <span>${dto.review_contents }</span></li>
														</ul>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">닫기</button>
													</div>
												</div>
											</div>
										</div>
									</td>
									<td class="align-middle text-end">
										<button type="button" class="btn btn-danger fw-bold"
											data-bs-toggle="modal"
											data-bs-target="#bookReviewDelModal${dto.review_keynum }">삭제</button>

										<div class="modal fade"
											id="bookReviewDelModal${dto.review_keynum }"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5">도서 후기 삭제</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body text-start">
														<p>선택하신 도서 후기를 삭제하시겠습니까?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">취소</button>

														<form name="frmBookDelete${dto.review_keynum}"
															id="frmBookDelete${dto.review_keynum}" method="get">
															<input type="hidden" name="review_keynum"
																value="${dto.review_keynum}" />
															<button type="button" class="btn btn-danger"
																onclick="btnBookDelete(${dto.review_keynum})">삭제</button>
														</form>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr>
								<c:set var="num" value="${num-1 }" />
							</c:forEach>

						</c:when>
					</c:choose>

					<!-- 반복 -->
				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:choose>
						<c:when test="${not empty searchResultPv }">
							<c:choose>
								<c:when test="${searchResultPv.startPage >1}">
									<li class="page-item"><a class="page-link"
										href="searchreview?option=${option}&search=${query}&currentPage=1"
										aria-label="Previous"><span aria-hidden="true">&laquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="searchreview?option=${option}&search=${query}&currentPage=1"
										aria-label="Previous"><span aria-hidden="true">&laquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 처음 출력 끝 -->

							<!-- 이전 출력 시작 -->
							<c:choose>
								<c:when test="${searchResultPv.startPage >1}">
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;"
										href="searchreview?option=${option}&search=${query}&currentPage=${searchResultPv.startPage-searchResultPv.blockPage}">
											> <span>&lsaquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: no-drop"> <span>&lsaquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 이전 출력 끝 -->

							<!-- 페이지번호 출력 시작 -->
							<c:forEach var="i" begin="${searchResultPv.startPage}"
								end="${searchResultPv.endPage}">
								<c:choose>
									<c:when test="${i==searchResultPv.currentPage}">
										<li class="page-item active"><a class="page-link"
											style="cursor: pointer;"
											href="searchreview?option=${option}&search=${query}&currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											style="cursor: pointer;"
											href="searchreview?option=${option}&search=${query}&currentPage=${i}">${i}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 페이지번호 출력 끝 -->

							<!-- 다음 출력 시작 -->
							<c:choose>
								<c:when
									test="${searchResultPv.endPage < searchResultPv.totalPage}">
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;"
										href="searchreview?option=${option}&search=${query}&currentPage=${searchResultPv.startPage + searchResultPv.blockPage}">
											<span>&rsaquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: no-drop"> <span>&rsaquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 다음 출력 끝 -->

							<!-- 마지막 출력 시작 -->
							<c:choose>
								<c:when
									test="${searchResultPv.endPage < searchResultPv.totalPage}">
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;"
										href="searchreview?option=${option}&search=${query}&currentPage=${searchResultPv.totalPage}">
											<span>&raquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: no-drop"> <span>&raquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${empty searchResultPv and not empty pv}">
							<c:choose>
								<c:when test="${pv.startPage >1}">
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;" href="?currentPage=1"> <span>&laquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: no-drop"> <span>&laquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 처음 출력 끝 -->

							<!-- 이전 출력 시작 -->
							<c:choose>
								<c:when test="${pv.startPage >1}">
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;"
										href="review?currentPage=${pv.startPage-pv.blockPage}"> <span>&lsaquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: no-drop"> <span>&lsaquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 이전 출력 끝 -->

							<!-- 페이지번호 출력 시작 -->
							<c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}">
								<c:choose>
									<c:when test="${i==pv.currentPage}">
										<li class="page-item active"><a class="page-link"
											style="cursor: pointer;" href="review?currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											style="cursor: pointer;" href="review?currentPage=${i}">${i}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 페이지번호 출력 끝 -->

							<!-- 다음 출력 시작 -->
							<c:choose>
								<c:when test="${pv.endPage < pv.totalPage}">
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;"
										href="review?currentPage=${pv.startPage + pv.blockPage}">
											<span>&rsaquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: no-drop"> <span>&rsaquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 다음 출력 끝 -->

							<!-- 마지막 출력 시작 -->
							<c:choose>
								<c:when test="${pv.endPage < pv.totalPage}">
									<li class="page-item"><a class="page-link"
										style="cursor: pointer;"
										href="review?currentPage=${pv.totalPage}"> <span>&raquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: no-drop"> <span>&raquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${totalcount eq 0 and empty revdtos}">
						
						</c:when>

					</c:choose>
				</ul>
			</nav>
		</div>
	</div>
</div>

<script>

function btnBookDelete(val) {
    // 검색했을때 특정값이 있으니깐 action을 /delete로 
    // 검색을안했을땐 action값을 books/delete
	$('#frmBookDelete'+val).attr('action', 'delete').submit();
}
    

</script>