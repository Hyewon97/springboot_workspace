<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginPw" value="${sessionScope.adminauthInfo.loginPw}" />

<script>
	function check() {
		var checkpa = document.getElementById("checkpa");

		var loginPw = "${sessionScope.adminauthInfo.loginPw}";
		var check = document.getElementById("oldpass").value;
		var newpass = document.getElementById("newpass").value;
		var newpasscheck = document.getElementById("newpasscheck").value;
		var able = false;
		if (check.value == "") {
			alert("기존 비밀번호를 입력하세요");
			check.focus();
			return false;
		}
		if (newpass.value == "") {
			alert("새로운 비밀번호를 입력하세요");
			newpass.focus();
			return false;
		}
		if (newpasscheck.value == "") {
			alert("비밀번호확인을 입력하세요");
			newpasscheck.focus();
			return false;
		}
		if (newpass.value != newpasscheck.value) {
			alert("비밀번호가 다릅니다.");
			newpasscheck.focus();
			return false;
		}
		if (check === loginPw) {
			alert("비밀번호가 일치합니다.");
			able = true;
		} else {
			alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			check.value = "";
			check.focus();
			return false;
		}
		if (able == true) {
			alert("비밀번호가 변경되었습니다. 다시 로그인해주세요");
			checkpa.action = "changepass";
			checkpa.method = "post";
			checkpa.submit();
			check.value = "";
			newpass.value = "";
			newpasscheck.value = "";
		}
		if (able == false) {
			alert("비밀번호가 확인되지 않습니다. 잠시후에 다시 시도해주세요")
		}

	}
	function checkpass() {

		var loginPw = "${sessionScope.adminauthInfo.loginPw}"
		var check = document.getElementById("oldpass").value;

		var able = false;

		if (check === loginPw) {
			document.getElementById("resultcheck").textContent = "비밀번호가 일치합니다.";
			able = true;
		} else {
			document.getElementById("resultcheck").textContent = "비밀번호가 일치하지 않습니다.";
			check.value = "";
			check.focus();
			return false;

		}

	}
</script>
<div class="container-fluid mt-5 mb-5 content_area">
	<div class="card">
		<h5 class="card-header">관리자현황</h5>

		<div class="card-body">
			<p class="card-text">현재 접속중인 관리자
				:${sessionScope.adminauthInfo.loginId}(${sessionScope.adminName})</p>

			<div class="row">
				<div class="col-md-6">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger fw-bold"
						data-bs-toggle="modal" data-bs-target="#adminDelModal">직권탈퇴</button>
					<button type="button" class="btn btn-warning fw-bold"
						data-bs-toggle="modal" data-bs-target="#adminPwMobModal">비밀번호
						변경</button>

					<!-- Modal -->
					<div class="modal fade" id="adminDelModal" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5">회원명님 : 아이디</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body text-start">
									<p>관리자 탈퇴를 진행하시겠습니까?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">취소</button>
									<form action="deleteAdmin" method="post">
										<input type="hidden" name="admin_id"
											value=${sessionScope.adminauthInfo.loginId }>
										<button type="submit" class="btn btn-danger">관리자 탈퇴</button>
									</form>
								</div>
							</div>
						</div>
					</div>

					<div class="modal fade" id="adminPwMobModal" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5">비밀번호 변경</h1>

									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<form id="checkpa">
									<input type="hidden" name="admin_id" id="admin_id"
										value=${sessionScope.adminauthInfo.loginId }>
									<div class="modal-body text-start">
										<div class="input-group mb-3">
											<span class="input-group-text">기존 비밀번호</span> <input
												type="password" id="oldpass" name="oldpass"
												onblur="checkpass()" class="form-control"
												placeholder="비밀번호를 입력하세요." autofocus="autofocus">
											<p id="resultcheck" class="input_inner_err"></p>
										</div>
										<div class="input-group mb-3">
											<span class="input-group-text">비밀번호</span> <input
												type="password" class="form-control" name="newpass"
												id="newpass" placeholder="비밀번호를 입력하세요.">
											<!-- <p class="input_inner_err">※ 입력 오류 문구</p> -->
										</div>
										<div class="input-group mb-3">
											<span class="input-group-text">비밀번호 재확인</span> <input
												type="password" class="form-control" id="newpasscheck"
												placeholder="비밀번호를 다시 입력하세요.">
											<!-- <p class="input_inner_err">※ 입력 오류 문구</p> -->
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">취소</button>
										<input type="hidden" value="">
										<button type="button" onclick="check()"
											class="btn btn-warning">비밀번호 변경</button>
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-6">
					<form action="searchAdmin" method="get">
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
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty searchdtos }">
							<c:set var="num" value="${number}" />
							<c:forEach var="dto" items="${searchdtos }">
								<tr>
									<th class="align-middle" scope="row">${num }</th>
									<td class="align-middle">${dto.admin_id }</td>
									<td class="align-middle">${dto.admin_name }</td>
								</tr>
								<c:set var="num" value="${num-1}"/>
							</c:forEach>
						</c:when>
						<c:when test="${empty dtos and searchcount eq 0 }">

							<tr>
								<td colspan="7" class="text-center">검색 결과가 없습니다..</td>
							</tr>

						</c:when>
						<c:otherwise>
							<c:set var="num" value="${number}" />
							<c:forEach var="dto" items="${dtos }">
								<tr>
									<th class="align-middle" scope="row">${num }</th>
									<td class="align-middle">${dto.admin_id }</td>
									<td class="align-middle">${dto.admin_name }</td>
								</tr>
								<c:set var="num" value="${num-1}" />
							</c:forEach>
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
									<li class="page-item disabled"><a class="page-link"
										style="cursor: pointer;"
										href="searchAdmin?option=${option}&search=${query}&currentPage=1">
											<span>&laquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled"><a class="page-link"
										style="cursor: no-drop"> <span>&laquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 처음 출력 끝 -->

							<!-- 이전 출력 시작 -->
							<c:choose>
								<c:when test="${searchResultPv.startPage >1}">
									<li class="page-item disabled"><a class="page-link"
										style="cursor: pointer;"
										href="searchAdmin?option=${option}&search=${query}&currentPage=${searchResultPv.startPage-searchResultPv.blockPage}">
											<span>&lsaquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled"><a class="page-link"
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
											href="searchAdmin?option=${option}&search=${query}&currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											style="cursor: pointer;"
											href="searchAdmin?option=${option}&search=${query}&currentPage=${i}">${i}</a>
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
										href="searchAdmin?option=${option}&search=${query}&currentPage=${searchResultPv.startPage + searchResultPv.blockPage}">
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
										href="searchAdmin?option=${option}&search=${query}&currentPage=${searchResultPv.totalPage}">
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
						<c:when test="${empty searchResultPv and not empty pv }">
							<c:choose>
								<c:when test="${pv.startPage >1}">
									<li class="page-item disabled"><a class="page-link"
										style="cursor: pointer;" href="admin?currentPage=1"> <span>&laquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled"><a class="page-link"
										style="cursor: no-drop"> <span>&laquo;</span>
									</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 처음 출력 끝 -->

							<!-- 이전 출력 시작 -->
							<c:choose>
								<c:when test="${pv.startPage >1}">
									<li class="page-item disabled"><a class="page-link"
										style="cursor: pointer;"
										href="admin?currentPage=${pv.startPage-pv.blockPage}"> <span>&lsaquo;</span>
									</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled"><a class="page-link"
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
											style="cursor: pointer;" href="admin?currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											style="cursor: pointer;" href="admin?currentPage=${i}">${i}</a>
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
										href="admin?currentPage=${pv.startPage + pv.blockPage}"> <span>&rsaquo;</span>
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
										href="admin?currentPage=${pv.totalPage}"> <span>&raquo;</span>
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