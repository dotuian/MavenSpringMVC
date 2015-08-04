package com.dotuian.springmvc.common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 计算网站点击次数的Filter
 *
 * 
 * ServletConfig是一个由Tomcat服务器在初始化Servlet的时候创建并传递进来的一个对象。该对象主要描述的时候一个servlet的配置信息。
 * 
 * ServletContext接口对象主要实现的是servlet之间的通信。该对象可以和web服务器进行通信。转发请求。
 * ServletContext接口是对网站的抽象描述。一个ServletContext代表一个web应用(网站)。
 * 
 * 
 * ServletConfig作用于某个特定的Servlet，即从该Servlet实例化，那么就开始有效，但是该Servlet之外的其他Servlet不能访问；
 * ServletContext作用于某个web应用，即在一个web应用中相当于一个全局对象，在Servlet容器启动时就已经加载，对于不同的web应用，有不同的ServletContext。
 */
public class SiteHitCountFilter implements Filter {

	private long count = 0;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		// 在容器启动时，将之前保存在数据库中的count，赋值给 this.count 
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		
		this.count++;
	}
	
	@Override
	public void destroy() {
		// 在容器销毁之前，将count的值写入到数据库 
	}
	
}
