package com.dotuian.springmvc.web.site.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dotuian.springmvc.common.annotations.Layout;
import com.dotuian.springmvc.web.base.controllers.BaseController;
import com.dotuian.springmvc.web.site.forms.LoginForm;
import com.dotuian.springmvc.web.site.validators.LoginFormValidator;

@Controller
@RequestMapping(value = "/site")
@Layout(value = "layouts/default")
public class SiteController extends BaseController {
	
	private String CONSTANT_USER = "USER"; 
	
	@Autowired
	private LoginFormValidator validator;
	
	
	// =============================================================================
	// Action执行之前，通过@ModelAttribute的执行，准备数据 
	// =============================================================================
	
	// 在该Controller中的Action执行之前，该方法会先先被调用，
	// 之后执行的Action，View可以通过 loginForm 获取该方法的返回值。
	@ModelAttribute("loginForm")
	public LoginForm loginForm() {
		return new LoginForm();
	}
	
	
	// =============================================================================
	// Action
	// =============================================================================
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index() {
		return "/site/index";
	}
	
	/**
	 * 登录页面
	 * @param request
	 * @param redirectURL
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request, String redirectURL) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/site/login");
		
		String actionUrl = "/site/login";
		if(!StringUtils.isEmpty(redirectURL)) {
			actionUrl += "?redirectURL=" + redirectURL;
		}
		
		model.addObject("actionUrl", actionUrl);

		return model;
	}
	
	/**
	 * 用户登录
	 * @param loginForm
	 * @param request
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String doLogin(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result,
			HttpServletRequest request, @RequestParam(value = "redirectURL", required=false) String redirectURL) {
		
		validator.validate(loginForm, result);
		
		if (result.hasErrors()) {
			return "site/login";
		}
		
		// 模拟登陆成功 用户admin 密码admin的用户
		if (loginForm.getPassword().equals("admin") && loginForm.getPassword().equals("admin")) {
			// 当登陆成功是，将用户信息存放到session中去
			HttpSession session = request.getSession();
			session.setAttribute(CONSTANT_USER, loginForm.getUsername());
			
			// 跳转到index页面
			return StringUtils.isEmpty(redirectURL) ? "redirect:/site/index" : "redirect:" + redirectURL;
		}

		return "site/login";
	}
	
	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute(CONSTANT_USER, null);
		session.invalidate();
		
		return "site/login";
	}
	
	
	
	// =============================================================================
	// 错误页面的URL处理 
	// =============================================================================
	@RequestMapping(value = "/error404", method = { RequestMethod.GET })
	public String error404(HttpServletRequest request, HttpServletResponse response) {
		return "errors/404";
	}
	
	@RequestMapping(value = "/error500", method = { RequestMethod.GET })
	public String error500(HttpServletRequest request, HttpServletResponse response) {
		return "errors/500";
	}
	
	
	
	
	
	
	
	
	
	
}
