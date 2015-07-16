package com.dotuian.springmvc.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.parameters.ParameterException;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dotuian.springmvc.web.controllers.HelloController;

/**
 * 
Spring MVC处理异常有3种方式： 
（1）使用Spring MVC提供的简单异常处理器SimpleMappingExceptionResolver； 
   基于配置的对已有的代码没有侵入性，但是该方法仅仅能够获取到异常信息,对于其他数据的情况不适用。
   
（2）实现Spring的异常处理接口HandlerExceptionResolver 自定义自己的异常处理器； 
  具有集成简单、良好的扩展性、对已有代码没有侵入性等优点，
  同时由于自定义实现,我们可以在处理异常时进行额外的处理(日志的记录、异常信息的国际化等)，
  项目实际的开发中也是使用的这种集成方案

（3）使用@ExceptionHandler注解实现异常处理； 

 * springMVC提供的异常处理主要有两种方式:
 * 1. 直接实现HandlerExceptionResolver，自定义异常处理方式 
 *   定义了这样一个异常处理器之后就要在applicationContext中定义这样一个bean对象
 *   <bean id="exceptionHandler" class="com.dotuian.springmvc.common.MyExceptionHandler"/>
 * 
 * 2. 使用@ExceptionHandler进行处理
 *   使用@ExceptionHandler进行处理有一个不好的地方,是进行异常处理的方法必须与出错的方法
 *   在同一个Controller里面
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

	protected Logger logger = Logger.getLogger(HelloController.class);
	
	/**
	 * 
	 * Exception exception : 
	 * 表示对哪种类型的异常进行处理
	 * 如果想同时对多种异常进行处理，可以把它换成一个异常数组。
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
