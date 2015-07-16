package com.dotuian.springmvc.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.parameters.ParameterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController extends BaseController {
	
	@RequestMapping(value = "/hello/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		
		model.addAttribute("message", "Spring 3 MVC Hello World");
		
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;
	}
	
	
	@RequestMapping(value = "/hello/error", method = RequestMethod.GET)
	public ModelAndView exception(HttpServletRequest request,
			HttpServletResponse response, Integer id) throws Exception {
		
		if (id != null) {
			switch (id) {
			case 1:
				throw new NullPointerException();
			case 2:
				throw new ClassCastException();
			case 3:
				throw new ParameterException(null);
			default:
				break;
			}
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		return mv; 
	}
	
}
