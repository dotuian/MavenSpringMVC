package com.dotuian.springmvc.common.listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * 
 * 统计在线用户数的监听器
 * 
 * 监听器的详细介绍： http://www.cnblogs.com/xing901022/p/4378727.html
 *
 * ServletContextListener 接口，它能够监听 ServletContext 对象的生命周期，实际上就是监听 Web 应用的生命周期。
 */
@WebListener
public class OnlineUserCountListener implements HttpSessionListener {

	public static String USERS_COUNT = "USERS_COUNT";
	
	protected Logger logger = Logger.getLogger(OnlineUserCountListener.class);
	
	private long usersCount;
	
	/**
	 * Default constructor.
	 */
	public OnlineUserCountListener() {
		this.usersCount = 0;
	}
	
	/**
	 * HttpSessionListener接口方法实现
	 * 
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		logger.info("=>>> 当前用户人数： " + this.usersCount);
		
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		
		this.usersCount++;
		application.setAttribute(USERS_COUNT, this.usersCount);
	}
	
	/**
	 * 在web.xml中定义session的timeout
	 * 
	 * 2中情况下，会执行该方法
	 * 1. 执行session.invalidate()方法时
	 * 2. session会话超时
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		
		this.usersCount--;
		application.setAttribute(USERS_COUNT, this.usersCount);
	}


}
