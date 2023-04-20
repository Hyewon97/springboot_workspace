<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="subpage_wrap">
	<div class="inner">
		<h2>나의 도서관</h2>
		<div class="contents my_area">
			<p class="books_tab">
				<a class="books_tab_1 active"
					href="${pageContext.request.contextPath}/my">회원정보 관리</a> <span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<a class="books_tab_2"
					href="${pageContext.request.contextPath}/my/record">도서관 이용 이력</a> <span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<br /> <a class="books_tab_3 loading_btn"
					href="${pageContext.request.contextPath}/my/recommend">맞춤도서추천</a> <span>&nbsp;&nbsp;·&nbsp;&nbsp;</span>
				<a class="books_tab_4"
					href="${pageContext.request.contextPath}/my/map">가까운 도서관</a>
			</p>
			<!-- 나이 이름 성별 -->

			<!-- 내 정보 관리 -->
			<div class="my_tab_cont_1 active">
				<div class="mymy_area">

					<h2>${userDTO.user_name }(${userDTO.user_id }) ${userDTO.user_age }세 ${userDTO.user_sex == 'M' ? '남성' : '여성' }</h2>

					<h4 class="user_mod">회원정보 수정</h4>

					<form class="address_form" id="changeAdd" method="post"
						action="my/changeAdd">

						<ul>
							<li>
								<h4>주소</h4>
							</li>
							<li><input id="member_post" type="text" placeholder="우편번호"
								value="${post}" style="width: 50%; margin-right: 20px;" readonly>
								<button class="addr_btn" type="button"
									style="width: calc(50% - 20px);">주소찾기</button></li>
							<li><input id="member_addr" type="text" placeholder="주소"
								value="${address}" readonly></li>
							<li><input type="text" id="extra" placeholder="상세주소"
								value="${extra }"> <input type="hidden"
								name=user_address id="user_address"></li>
							<li>
								<h4>비밀번호 확인</h4>
							</li>
							<li><input type="password" placeholder="패스워드를 입력하세요."
								id="user_password" name="user_password" /> <input type="text"
								hidden="hidden" name="user_id"
								value="${sessionScope.authInfo.user_id }"></li>
						</ul>

						<button type="submit">수정하기</button>
					</form>

					<h4 class="user_pw_mob">비밀번호 변경</h4>
					<form class="password_form" id="changePwd" action="my/changePwd"
						method="POST">
						<ul>
							<li>
								<h4>현재 비밀번호</h4>
							</li>
							<li><input type="password" placeholder="패스워드를 입력하세요."
								id="user_password2" /></li>
							<li>
								<h4>새 비밀번호</h4>
							</li>
							<li><input type="password" placeholder="패스워드를 입력하세요."
								id="new_user_password" name="user_password" /></li>
							<li>
								<h4>비밀번호 확인</h4>
							</li>
							<li><input type="password" placeholder="패스워드를 입력하세요."
								id="chk_user_password" /> <input type="text" hidden="hidden"
								name="user_id" value="${sessionScope.authInfo.user_id }">
							</li>
						</ul>

						<button type="submit">변경하기</button>
					</form>

					<h4 class="user_del">회원탈퇴</h4>
					<form class="delete_form" id="deletefrm" method="post" style="margin-bottom: 0;" 
						action="my/delete">
						<p>※ 대출/예약된 도서가 있을 시 탈퇴가 불가합니다.</p>
						
						        <!-- 공통팝업 -->
				        <section class="del_popup_back">
				          <article class="del_popup">
				            <p>정말 탈퇴하시겠습니까?</p>
				            <div class="inner_userdel_btn" style="cursor: pointer; text-align: center; width: calc(50% - 5px); margin-right: 10px; float: left; background-color: #999; color: #fff;">취소</div>
							<button class="inner_userdel_btn_ggin" style="width: calc(50% - 5px); float: left; background-color: brown;" type="submit">탈퇴</button>
				          </article>
				        </section>
					</form>
					<button class="userdel_btn" style="background-color: brown;">탈퇴하기</button>
				</div>

			</div>

		</div>
	</div>
</section>

<script>
	$(document).ready(function() {
		
		
		$('.userdel_btn').on('click', function(){
			$('.del_popup_back').addClass('on');
		});

		$('.inner_userdel_btn').on('click', function(){
			$('.del_popup_back').removeClass('on');
		});

		$('.inner_userdel_btn_ggin').on('click', function(){
			$('.del_popup_back').removeClass('on');
		});
		
				//팝업 메세지
				var ic = false;
				var popup = $('.popup_back');
				var popupContent = $('.popup p');

				// check 할 정보 가져오기
				var spw = '${userDTO.user_password}';
				var pw = document.getElementById('user_password');
				var pw2 = document.getElementById('user_password2');
				var npw = document.getElementById('new_user_password');
				var cpw = document.getElementById('chk_user_password');

				// 주소 값
				var post = document.getElementById('member_post');
				var addr = document.getElementById('member_addr');
				var extra = document.getElementById('extra');

				var popupState = '${popupState}';
				var popupMessage = '${popupMessage}';

				if (popupState == "on") {
					$(".popup>p").text(popupMessage);
					$(".popup_back").addClass("on");
				}

				$(".popup>button").click(function() {
					console.log("closed");
					$(".popup_back").removeClass("on");
					$("#err").addClass("on");
				});

				if (popupState == 'on') {
					popupContent.text(popupMessage);
					popup.addClass('on');
					return false;

				}

				// 주소 변경. 변경한다. 
				$('#changeAdd').submit(
						function(event) {
							console.log("제출됬다.")
							$("#user_address").val(
									post.value + ' ' + addr.value + '/'
											+ extra.value);
							console.log($("#user_address").val());

							if (pw.value == '') {
								popupContent.text('비밀번호를 입력하세요.');
								popup.addClass('on');
								pw.focus();
								return false;
							}

							if (pw.value != spw) {
								popupContent.text('비밀번호가 틀렸습니다.');
								popup.addClass('on');
								return false;
							}

							if (post.value == '') {
								popupContent.text('우편번호를 입력하세요.');
								popup.addClass('on');
								post.focus();
								return false;
							}
							if (addr.value == '') {
								popupContent.text('주소를 입력하세요.');
								popup.addClass('on');
								addr.focus();
								return false;
							}
							if (extra.value == '') {
								popupContent.text('상세주소를 입력하세요.');
								popup.addClass('on');
								extra.focus();
								return false;
							}
						});

				//비밀번호 변경
				$('#changePwd').submit(function(event) {
					console.log('돼라');

					// 비밀번호와 확인하는 과정
					if (pw2.value == '') {
						popupContent.text('현재 비밀번호를 입력하세요.');
						popup.addClass('on');
						pw2.focus();
						return false;
					}
					if (npw.value == '') {
						popupContent.text('비밀번호를 입력하세요.');
						popup.addClass('on');
						npw.focus();
						return false;
					}
					if (cpw.value == '') {
						popupContent.text('비밀번호를 확인을 입력해주세요.');
						popup.addClass('on');
						cpw.focus();
						return false;
					}
					if (pw2.value != spw) {
						popupContent.text('현재 비밀번호가 잘못 입력되었습니다.');
						popup.addClass('on');
						pw2.focus();
						return false;
					}
					if (npw.value != cpw.value) {
						popupContent.text('비밀번호 확인이 잘못 입력되었습니다.');
						popup.addClass('on');
						cpw.focus();
						return false;
					}
				});

			});
</script>

