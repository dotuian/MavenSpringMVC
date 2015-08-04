package com.dotuian.springmvc.common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * http://www.cnblogs.com/hellojava/archive/2012/12/19/2824444.html
 * 
 * Filter开发两步走
 * 1. 编写java类实现Filter接口，并实现其doFilter方法。
 * 2. 在 web.xml 文件中使用<filter>和<filter-mapping>元素对编写的filter类进行注册，并设置它所能拦截的资源。
 * 
 * Filter链 : 在一个web应用中，可以开发编写多个Filter，这些Filter组合起来称之为一个Filter链。
 * 
 * Filter的生命周期: 和Servlet程序一样，Filter的创建和销毁由WEB服务器负责。
 * 1. web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，读取web.xml配置完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作（filter对象只会创建一次，init方法也只会执行一次）
 *    public void init(FilterConfig filterConfig) throws ServletException;//初始化
 * 2. 当客户请求访问与过滤器关联的URL的时候，Servlet过滤器将先执行doFilter方法。FilterChain参数用于访问后续过滤器。
 *    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
 * 3. Filter对象创建后会驻留在内存，当web应用移除或服务器停止时才销毁。在Web容器卸载 Filter 对象之前被调用。该方法在Filter的生命周期中仅执行一次。在这个方法中，可以释放过滤器使用的资源。
 *    public void destroy();//销毁
 * 
 * 
 * 当 Web 容器启动 Web 应用程序时，它会为您在部署描述符中声明的每一个过滤器创建一个实例。该过滤器执行的顺序是按它们在部署描述符中声明的顺序。
 * web.xml 中的 filter-mapping 元素的顺序决定了 Web 容器应用过滤器到 Servlet 的顺序。
 * 
 *
 */
public class AuthorizationFilter implements Filter {
	
	private Logger logger = Logger.getLogger(AuthorizationFilter.class);
	
	private FilterConfig config = null;

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("权限验证过滤器初始化");
		this.config = config;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		//logger.info("权限验证过滤器业务处理");
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;

		//logger.info("当前URL = " + request.getRequestURL());
		
		// 不需要过滤的请求
		String url = request.getRequestURI();
		if(url.toLowerCase().endsWith(".css") || url.toLowerCase().endsWith(".js")
				|| url.toLowerCase().endsWith(".woff2") || url.toLowerCase().endsWith(".woff") || url.toLowerCase().endsWith(".ttf")
				) {
			// 登录页面，CSS/JS 不过滤
			filterChain.doFilter(req, res);
			return;
		}
		
		// 登录画面不进行过滤
		String loginUrl = this.config.getInitParameter("login");
		if (url.endsWith(loginUrl)) {
			filterChain.doFilter(req, res);
			return;
		}
		
		
		// 判断用户是否登录
		Object user = request.getSession().getAttribute("USER");
		if (user == null) {
			// 跳转到登陆页面
			response.sendRedirect(request.getContextPath() + loginUrl);
		}
		
		filterChain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		logger.info("权限验证过滤器销毁");
		this.config = null;
	}

}
