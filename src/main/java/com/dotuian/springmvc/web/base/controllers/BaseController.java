package com.dotuian.springmvc.web.base.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.avalon.framework.parameters.ParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {

	protected Logger logger = Logger.getLogger(BaseController.class);
	
	/**
	 * 基于注解@ExceptionHandler异常处理
	 * 因为使用@ExceptionHandler进行异常处理的方法必须与出错的方法在同一个Controller里面,
	 * 所以采用父类中实现错误处理，之类Contoller继承父类的方式来实现
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {

		logger.error("基于注解@ExceptionHandler异常处理  --->>> Request: " + request.getRequestURL() + " raised " + exception);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", exception);
		mv.addObject("url", request.getRequestURL());

		// 根据不同错误转向不同页面
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
