package com.dotuian.springmvc.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.parameters.ParameterException;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dotuian.springmvc.web.site.controllers.SiteController;

/**
 * http://www.mincoder.com/article/2500.shtml
 * 
 * Spring MVC处理异常有3种方式： 
 * （1）使用Spring MVC提供的简单异常处理器SimpleMappingExceptionResolver； 
 *    然后在配置文件中配置: <bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
 *    基于配置的对已有的代码没有侵入性，但是该方法仅仅能够获取到异常信息,对于其他数据的情况不适用。
 *    
 * （2）实现Spring的异常处理接口HandlerExceptionResolver 自定义自己的异常处理器； 
 *   然后在配置文件中配置 : <bean id="exceptionHandler" class="com.dotuian.springmvc.common.MyExceptionHandler"/>
 *   具有集成简单、良好的扩展性、对已有代码没有侵入性等优点，
 *   同时由于自定义实现,我们可以在处理异常时进行额外的处理(日志的记录、异常信息的国际化等)，
 *   项目实际的开发中也是使用的这种集成方案
 * 
 * （3）使用@ExceptionHandler注解实现异常处理； 
 *  使用@ExceptionHandler进行处理有一个不好的地方,是进行异常处理的方法必须与出错的方法,在同一个Controller里面，但是可以通过继承来处理。
 *  另外使用这种方法存在侵入性,而且在异常处理时也不能获取异常以外的数据,且Ajax请求产生的异常信息无法反馈给前端。
 * 
 * 如果在项目中上述3种处理方式都包含了的话，其中的优先度为
 * 全局异常处理SimpleMappingExceptionResolver < 实现接口HandlerExceptionResolver异常处理 < @ExceptionHandler注解
 * 
 * 
 *  Spring MVC集成异常处理3种方式都可以达到统一异常处理的目标。
 *  从3种方式的优缺点比较，若只需要简单的集成异常处理，推荐使用SimpleMappingExceptionResolver即可；
 *  若需要集成的异常处理能够更具个性化，提供给用户更详细的异常信息，推荐自定义实现HandlerExceptionResolver接口的方式；
 *  若不喜欢Spring配置文件或要实现“零配置”，且能接受对原有代码的适当入侵，则建议使用@ExceptionHandler注解方式。
 * 
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

	protected Logger logger = Logger.getLogger(SiteController.class);
	
	/**
	 * 第4个参数Exception exception : 表示对哪种类型的异常进行处理，如果想同时对多种异常进行处理，可以把它换成一个异常数组。
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception) {
		
		logger.error("基于实现接口HandlerExceptionResolver异常处理  --->>> Request: " + request.getRequestURL() + " raised " + exception);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", exception);
		mv.addObject("url", request.getRequestURL());
		
        // 根据异常的种类不同，跳转到不同的页面  
		if (exception instanceof NullPointerException) {
			mv.setViewName("/errors/exception");
		} else if (exception instanceof ParameterException) {
			mv.setViewName("/errors/exception");
		} else {
			mv.setViewName("/errors/exception");
		}
		
		return mv;
	}

}
