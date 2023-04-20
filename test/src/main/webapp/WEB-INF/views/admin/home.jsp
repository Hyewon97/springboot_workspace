<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	// 중복확인 값 
	var ic = false;
	function check() {
		// check 할 정보 가져오기
		var id = document.getElementById("admin_id");
		var pw = document.getElementById("admin_password");
		var name = document.getElementById("admin_name");
		var pwc = document.getElementById("adminPwcheck");
		var join = document.getElementById("join");

		//공란이 있을시 focus 그리고 공란을 채울떄까지 안넘어감

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
		if (name.value == "") {
			alert("이름를입력하세요");
			name.focus();
			return false;
		}
		if (pwc.value == "") {
			alert("비밀번호확인을입력하세요");
			pwc.focus();
			return false;
		}
		// 비밀번호와 확인하는 과정에서 다를시 focus 하고 안넘어감 

		if (pw.value != pwc.value) {
			alert("비밀번호가 다릅니다.");
			pwc.focus();
			return false;
		}
		// 중복확인을 안할시 false값 유지
		if (ic == false) {
			alert('중복확인을하세요')
		}

		//위의 조건들을 다 통과하면 submit
		if (ic == true) {
			
			
			join.action = "admin/adminSignup";
			join.method = "post";
			join.submit();
		}

	};
	//중복확인
	function checkid() {
		// 아이디값 가져옴
		var id = $('#admin_id').val();
		if (id != "") {
			$.ajax({
				url : "admin/idcheck", //컨트롤러 주소 
				type : "post", // 전달방식
				data : {
					id : id
				// 전달할 파라미터 admin_id 
				},
				success : function(cnt) { // 성공시 ctn는 컨트롤러 실행 결과
					if (cnt == 0) { //cnt 값이 0이면 중복되는 아이디 없음
						alert("사용가능");
						ic = true; // ic값 true 로 변경
					} else {
						alert("불가능");
						$('#admin_id').val(''); // 중복되는 아이디가 있을시 아이디 입력란을 공란으로 만듬 
					}

				},
				error : function() {
					alert("통신오류");
				}
			});
		} else {
			alert('아이디를 입력하세요'); // 아이디를 입력하지 않았을 시 알러트 

		}

	};
	function login() {
		var loginform = document.getElementById("login");
		var id = document.getElementById("loginId");
		var pw = document.getElementById("loginPw");

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

		loginform.method = "POST";
		return true;
		
	};
</script>
<div class="container mt-5 content_area">
	<div class="row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">로그인</h5>
					<p class="card-text">관리자 로그인을 해주세요.</p>

					<form id="login" action="admin/login" onsubmit="return login()">
						<div class="mb-3">
							<input type="text" name="loginId" id="loginId"class="form-control" placeholder="아이디를 입력하세요.">
						</div>
						<div class="mb-3">
							<input type="password" name="loginPw" id="loginPw" class="form-control"
								placeholder="비밀번호를 입력하세요.">
						</div>
						<!-- <p class="input_err">※ 입력 오류 문구</p> -->
						<div class="d-grid gap-2">
							<button type="submit" class="btn btn-primary fw-bold"
								>로그인</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">회원가입</h5>
					<p class="card-text">관리자 회원가입을 해주세요.</p>
					<form id="join">
						<div class="input-group mb-3">
							<span class="input-group-text">아이디</span> <input type="text"
								class="form-control" name="admin_id" id="admin_id" placeholder="아이디를 입력하세요.">
							<button class="btn btn-outline-secondary" type="button"
								onclick="checkid()">중복확인</button>
							
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">비밀번호</span> <input type="password"
								class="form-control" id="admin_password" name="admin_password" placeholder="비밀번호를 입력하세요.">
		
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">비밀번호 재확인</span> <input
								type="password" class="form-control"
								id="adminPwcheck" placeholder="비밀번호를 다시 입력하세요.">

						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">성명</span> <input type="text"
								name="admin_name" id="admin_name"class="form-control" placeholder="성명 입력하세요.">
							
						</div>
						<div class="d-grid gap-2">
							<button type="button" class="btn btn-primary fw-bold"
								onclick="check()">회원가입</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>