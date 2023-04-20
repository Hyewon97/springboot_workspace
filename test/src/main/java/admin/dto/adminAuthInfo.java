package admin.dto;

// 관리자 인증 정보를 저장
public class adminAuthInfo {
		private String loginId;
		private String loginPw;
		
		public adminAuthInfo() {
			
		}

		public adminAuthInfo(String loginId, String loginPw) {
			this.loginId = loginId;
			this.loginPw = loginPw;
		}

		public String getLoginId() {
			return loginId;
		}

		public String getLoginPw() {
			return loginPw;
		}
		
}
