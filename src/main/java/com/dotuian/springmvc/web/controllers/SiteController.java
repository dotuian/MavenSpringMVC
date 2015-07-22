package com.dotuian.springmvc.web.controllers;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dotuian.springmvc.common.annotations.Layout;
import com.dotuian.springmvc.common.interceptors.AuthInterceptor;
import com.dotuian.springmvc.common.interceptors.AuthPassport;
import com.dotuian.springmvc.web.forms.LoginForm;

@Controller
@RequestMapping(value = "/site")
public class SiteController extends BaseController {
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public ModelAndView layout() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/site/test");
		return model;
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request, String redirectURL) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/site/login");
		model.addObject("redirectURL", redirectURL);

		// 收集页面数据
		LoginForm loginForm = new LoginForm();
		model.addObject("loginForm", loginForm);

		return model;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String doLogin(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request,
			String redirectURL) {

		// 模拟登陆成功 用户admin 密码admin的用户
		if (StringUtils.isNotBlank(loginForm.getPassword()) && StringUtils.isNotBlank(loginForm.getPassword())
				&& loginForm.getPassword().equals("admin") && loginForm.getPassword().equals("admin")) {
			// 当登陆成功是，将用户信息存放到session中去
			HttpSession session = request.getSession();
			session.setAttribute(AuthInterceptor.SEESION_MEMBER, "admin");
			if (StringUtils.isNotBlank(redirectURL)) {
				return "redirect:" + URLDecoder.decode(redirectURL);
			}

			return "redirect:/site/index";
		} else {
			if (StringUtils.isNotBlank(redirectURL)) {
				return "redirect:/site/login?" + URLDecoder.decode(redirectURL);
			}
			return "redirect:/site/login";
		}
	}
	
	@Layout(value = "layouts/blank")
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index() {
		return "/site/index";
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute(AuthInterceptor.SEESION_MEMBER, null);
		return "/site/login";
	}
}