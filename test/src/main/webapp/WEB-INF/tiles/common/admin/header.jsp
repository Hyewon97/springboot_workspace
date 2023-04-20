<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<nav class="navbar navbar-expand-lg bg-body-secondary sticky-top" data-bs-theme="dark">
	<div class="container-fluid">
		<h4 class="navbar-brand">ADMIN - 영원한도서관</h4>

		<c:choose>
			<c:when test="${sessionScope.adminauthInfo == null}">


			</c:when>


			<c:otherwise>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarColor01"
					aria-controls="navbarColor01" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarColor01"
					style="flex-grow: 0;">
					<ul class="navbar-nav mb-2 mb-lg-0 nav justify-content-end me-4">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/admin/user">회원현황</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/admin/admin">관리자현황</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/admin/notice">공지사항관리</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/admin/books">도서관리</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/admin/borrow">도서대출관리</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/admin/review">도서후기관리</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/admin/request">도서신청관리</a>
						</li>
					</ul>
					<form class="d-flex" action="${pageContext.request.contextPath}/admin/logout" role="search">
						<button type="submit" class="btn btn-warning fw-bold">로그아웃</button>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
		<!-- 로그인 해야 보임 - end -->
	</div>
</nav>
<!-- header -->