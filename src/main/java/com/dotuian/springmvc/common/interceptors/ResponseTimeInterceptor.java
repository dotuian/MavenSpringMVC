package com.dotuian.springmvc.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * HandlerInterceptor是Spring MVC为我们提供的拦截器接口，来让我们实现自己的处理逻辑 
 * 借助于HandlerInterceptor我们可以实现很多其它功能，比如日志记录、请求处理时间分析等，权限验证
 *
 *
 * HandlerInterceptorAdapter适配器是Spring MVC为了方便我们使用HandlerInterceptor而对HandlerInterceptor 的默认实现，
 * 里面的3个方法没有做任何处理，在preHandle方法直接返回true，
 * 这样我们继承HandlerInterceptorAdapter后只需要实现3个方法中我们需要的方法即可，
 * 而不像继承HandlerInterceptor一样不管是否需要3个方法都要实现。
 *
 * 拦截器的配置：
 * <mvc:interceptors>
 * 	<bean class="com.dotuian.springmvc.common.interceptors.ResponseTimeInterceptor" />
 * </mvc:interceptors>
 */
public class ResponseTimeInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(ResponseTimeInterceptor.class);
	
	private long start = 0;
	
	/**
	 * 预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器
	 * 
	 * 返回值：
	 * true表示继续流程（如调用下一个拦截器或处理器）；
	 * false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		this.start = System.currentTimeMillis();
		return true;
	}

	/**
	 * 后处理回调方法，实现处理器的后处理（但在渲染视图之前）
	 * 可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理
	 * 
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	/**
	 * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理。
	 * 但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		double interval = (System.currentTimeMillis() - start ) / 1000; 
		
		logger.info("==================================================");
		logger.info(request.getRequestURL() + " | response time : " + interval + "s");
		logger.info("==================================================");
	}

	
}
