<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	function login() {
		var loginform = document.getElementById("login");
		var id = document.getElementById("user_id");
		var pw = document.getElementById("user_pw");

		if (id.value == "") {
			alert("아이디를입력하세요");
			id.focus();
			return false;
		}
		if (pw.value == "") {
			alert("비밀번호를입력하세요");
			pw.focus();
			return false;
		}
		loginform.action = "login";
		loginform.method = "POST";

		return true;
	};
	let popupState = '${popupState}';
	let popupContent = '${popupContent}';

	console.log(popupState);
	console.log(popupContent);

	$(document).ready(function() {
		if (popupState == "on") {
			$(".popup>p").text(popupContent);
			$(".popup_back").addClass("on");
		}

		$(".popup>button").click(function() {
			console.log("closed");
			$(".popup_back").removeClass("on");
			$("#err").addClass("on");
		});
	});
</script>
<!-- login -->
<section class="sign_area">
	<article class="inner">
		<h2>로그인</h2>
		<p>영원한 도서관에 방문하신것을 진심으로 환영합니다.</p>
		<form class="login_form" id="login" onsubmit="return login()">
			<input type="text" name="user_id" id="user_id"
				placeholder="아이디를 입력하세요." /> <input type="password" name="user_pw"
				id="user_pw" placeholder="패스워드를 입력하세요." />
			<p id="err" class="">※ 아이디 또는 비밀번호가 틀렸습니다.</p>
			<div>
				<input type="checkbox" id="save_id" value=""> <label
					for="save_id">&nbsp;&nbsp;아이디 저장</label>
				<p>
					아직 회원이 아니신가요?<a href="${pageContext.request.contextPath}/signup">회원가입</a>
				</p>
			</div>
			<button type="submit">로그인</button>
		</form>
	</article>
</section>