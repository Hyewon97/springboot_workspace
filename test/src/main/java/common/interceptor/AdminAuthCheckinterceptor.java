package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import admin.dto.adminAuthInfo;
import user.dto.AuthInfo;

public class AdminAuthCheckinterceptor extends HandlerInterceptorAdapter {
	public AdminAuthCheckinterceptor() {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//false이면서 세션이 없으면 그냥 null이고
		//true이면서 세션이 없으면 세션을 새로 생성해서 리턴
		HttpSession session = request.getSession(false);
		adminAuthInfo adminauthInfo = (adminAuthInfo)session.getAttribute("adminauthInfo");
		
		if(session != null) {
			if(adminauthInfo!=null) {
				response.sendRedirect(request.getContextPath());
				return false;
			}
			AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
			if(authInfo != null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
}
