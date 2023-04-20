<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
</script>


<div class="container-fluid mt-5 mb-5 content_area">
	<div class="card">
		<h5 class="card-header">회원현황</h5>
		<div class="card-body">

			<div class="row">
				<div class="col-md-6"></div>
				<div class="col-md-6">
					<form action="searchUser" method="get">
						<div class="input-group mb-3">

							<select class="form-select" name="option">
								<c:choose>
									<c:when test="${option eq'idq' }">
										<option value="nmq">회원명</option>
										<option value="idq" selected="selected">아이디</option>
									</c:when>
									<c:otherwise>
										<option value="nmq" selected="selected">회원명</option>
										<option value="idq">아이디</option>
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
						<th scope="col">아이디</th>
						<th scope="col">회원명</th>
						<th scope="col">성별</th>
						<th scope="col">주소</th>
						<th scope="col">나이</th>
						<th scope="col" class=" text-end">〓</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${number}"/>
					<c:choose>
						<c:when test="${count eq 0 }">

							<tr>
								<td colspan="7" class="text-center">등록된유저가 없습니다..</td>
							</tr>

						</c:when>
						<c:when test="${empty dtos and searchcount eq 0 }">

							<tr>
								<td colspan="7" class="text-center">검색 결과가 없습니다..</td>
							</tr>

						</c:when>



						<c:when test="${not empty searchdtos }">

							

							<c:forEach var="dto" items="${searchdtos }" varStatus="status">

								<!-- 반복 -->
								<tr>
									<th class="align-middle" scope="row">${searchNum }</th>
									<td class="align-middle">${dto.user_id }</td>
									<td class="align-middle">${dto.user_name }</td>
									<td class="align-middle">${dto.user_sex }</td>
									<td class="align-middle">${dto.user_address }</td>
									<td class="align-middle">${dto.user_age }</td>
									<td class="align-middle text-end">
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-danger fw-bold"
											data-bs-toggle="modal"
											data-bs-target="#userDelModal${dto.user_id}">직권탈퇴</button> <!-- Modal -->
										<div class="modal fade" id="userDelModal${dto.user_id}"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5" id="info">${dto.user_name}님
															: ${dto.user_id}</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>

													</div>
													<div class="modal-body text-start">
														<p>직권탈퇴를 진행하시겠습니까?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">취소</button>
														<form id="del" action="deleteUser" method="post">
															<input type="hidden" name="user_id" value=${dto.user_id }>
															<button type="submit" class="btn btn-danger">직권탈퇴</button>
														</form>
													</div>
												</div>
											</div>
										</div>

									</td>
								</tr>
								<c:set var="num" value="${searchNum -1}" />
							</c:forEach>

							<!-- 반복 -->
						</c:when>
						<c:otherwise>

							<c:set var="num" value="${number}"/>
							<c:forEach var="dto" items="${dtos }">
								
								<!-- 반복 -->
								<tr>
									<th class="align-middle" scope="row">${num}</th>
									<td class="align-middle">${dto.user_id }</td>
									<td class="align-middle">${dto.user_name }</td>
									<td class="align-middle">${dto.user_sex }</td>
									<td class="align-middle">${dto.user_address }</td>
									<td class="align-middle">${dto.user_age }</td>
									<td class="align-middle text-end">
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-danger fw-bold"
											data-bs-toggle="modal"
											data-bs-target="#userDelModal${dto.user_id}">직권탈퇴</button> <!-- Modal -->
										<div class="modal fade" id="userDelModal${dto.user_id}"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5" id="info">${dto.user_name}님
															: ${dto.user_id}</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>

													</div>
													<div class="modal-body text-start">
														<p>직권탈퇴를 진행하시겠습니까?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">취소</button>
														<form id = "del2" action="deleteUser" method="post">
															<input type="hidden" name="user_id" value=${dto.user_id }>
															<button type="submit" class="btn btn-danger">직권탈퇴</button>
														</form>
													</div>
												</div>
											</div>
										</div>

									</td>
								</tr>
								<c:set var="num" value="${num-1}"></c:set>
							</c:forEach>

							<!-- 반복 -->
						</c:otherwise>
					</c:choose>




				</tbody>
			</table>


			<div class="pagination_area">
				<ul class="pagination justify-content-center">
					<!-- 처음 출력 시작 -->
					<c:choose>
						<c:when test="${not empty searchResultPv }">
							<c:choose>
								<c:when test="${searchResultPv.startPage >1}">
									<li class="page-item"><a class="page-link"
										href="searchUser?option=${option}&search=${query}&currentPage=1"
										aria-label="Previous"><span aria-hidden="true">&laquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="searchUser?option=${option}&search=${query}&currentPage=1"
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
										href="searchUser?option=${option}&search=${query}&currentPage=${searchResultPv.startPage-searchResultPv.blockPage}">
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
											href="searchUser?option=${option}&search=${query}&currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											style="cursor: pointer;"
											href="searchUser?option=${option}&search=${query}&currentPage=${i}">${i}</a>
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
										href="searchUser?option=${option}&search=${query}&currentPage=${searchResultPv.startPage + searchResultPv.blockPage}">
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
										href="searchUser?option=${option}&search=${query}&currentPage=${searchResultPv.totalPage}">
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
										style="cursor: pointer;" href="user?currentPage=1"> <span>&laquo;</span>
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
										href="user?currentPage=${pv.startPage-pv.blockPage}"> <span>&lsaquo;</span>
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
											style="cursor: pointer;" href="user?currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											style="cursor: pointer;" href="user?currentPage=${i}">${i}</a>
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
										href="user?currentPage=${pv.startPage + pv.blockPage}"> <span>&rsaquo;</span>
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
										href="user?currentPage=${pv.totalPage}"> <span>&raquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										style="cursor: no-drop"> <span>&raquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>


					</c:choose>

					<!-- 마지막 출력 끝 -->

				</ul>
			</div>
		</div>
	</div>
</div>