<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<section class="sign_area_pc">
	<p>
		<c:choose>
			<c:when test="${sessionScope.authInfo == null && sessionScope.adminauthInfo==null}">


				<a href="${pageContext.request.contextPath}/login">로그인</a>
				<span>|</span>
				<a href="${pageContext.request.contextPath}/signup">회원가입</a>
				<!-- 김누구(abd***)님 환영합니다. <span>|</span> <a href="">로그아웃</a> -->

			</c:when>
			<c:when test="${sessionScope.adminauthInfo != null && sessionScope.authInfo==null}">
				${sessionScope.adminauthInfo.loginId} 관리자로 접속중입니다.
			</c:when>
			

			<c:otherwise>
				${sessionScope.authInfo.user_id}님 환영합니다.
                <span>|</span>
				<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
	</p>
</section>
<nav class="nav_area">
	<article class="inner">
		<a href="${pageContext.request.contextPath}"> <img class="logo"
			src="/myapp/resources/assets/images/el_logo.png" alt="영원한 도서관 Logo" />
		</a>

		<div class="menu-trigger">
			<span></span> <span></span> <span></span>
		</div>

		<ul>
			<li><a href="${pageContext.request.contextPath}/notice/notice">도서관
					소식</a></li>
			<li><a href="${pageContext.request.contextPath}/info">이용안내</a></li>
			<li><a href="${pageContext.request.contextPath}/books">통합도서조회</a></li>
			<li><a href="${pageContext.request.contextPath}/request/request">희망도서신청</a></li>
			<li><a href="${pageContext.request.contextPath}/my">나의 도서관</a></li>

			<li class="sign_area_mob">
				<p>
					<a href="${pageContext.request.contextPath}/login">로그인</a> <span>·</span>
					<a href="${pageContext.request.contextPath}/signup">회원가입</a>
					<!-- 김누구(abd***)님 <span>·</span> <a href="">로그아웃</a> -->
				</p>
			</li>
		</ul>
	</article>
</nav>
<!-- header -->