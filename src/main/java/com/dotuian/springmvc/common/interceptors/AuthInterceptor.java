package com.dotuian.springmvc.common.interceptors;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dotuian.springmvc.common.annotations.AuthPassport;


/**
 * HandlerInterceptor是Spring MVC为我们提供的拦截器接口，来让我们实现自己的处理逻辑 
 * 借助于HandlerInterceptor我们可以实现很多其它功能，比如日志记录、请求处理时间分析等，权限验证
 *
 *
 * HandlerInterceptorAdapter适配器是Spring MVC为了方便我们使用HandlerInterceptor而对HandlerInterceptor 的默认实现，
 * 里面的3个方法没有做任何处理，在preHandle方法直接返回true，
 * 这样我们继承HandlerInterceptorAdapter后只需要实现3个方法中我们需要的方法即可，
 * 而不像继承HandlerInterceptor一样不管是否需要3个方法都要实现。
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger.getLogger(HandlerInterceptor.class);
	
	public final static String SEESION_MEMBER = "SEESION_MEMBER";

	/**
	 * 在执行action里面的处理逻辑之前执行，它返回的是boolean，
	 * 这里如果我们返回true在接着执行postHandle和afterCompletion，如果我们返回false则中断执行。
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
		
		AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
		
		
        if(authPassport == null || authPassport.validate() == false) {
        	return true;
        }
		
		// 请求的路径
		String contextPath = request.getContextPath();
		String url = request.getServletPath().toString();
		HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute(SEESION_MEMBER);
		// 这里可以根据session的用户来判断角色的权限，根据权限来重定向不同的页面，简单起见，这里只是做了一个重定向
		if (StringUtils.isEmpty(user)) {
			// 被拦截，重定向到login界面
			response.sendRedirect(contextPath + "/site/login?redirectURL=" + URLEncoder.encode(url));
			return false;
		}
		
		return true;
	}	

	
	/**
	 * 在action返回视图后执行
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		logger.info("=== afterCompletion === ");

	}

	
	/**
	 * 在执行action里面的逻辑后返回视图之前执行
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		logger.info("=== postHandle === ");

	}


}
