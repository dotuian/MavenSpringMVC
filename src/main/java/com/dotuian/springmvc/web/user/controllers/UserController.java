package com.dotuian.springmvc.web.user.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dotuian.springmvc.domain.User;
import com.dotuian.springmvc.service.UserService;
import com.dotuian.springmvc.web.base.controllers.BaseController;
import com.dotuian.springmvc.web.base.dto.KV;
import com.dotuian.springmvc.web.user.forms.UserForm;
import com.dotuian.springmvc.web.user.forms.UserSearchForm;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	protected Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// 在该Controller中的Action执行之前，该方法会先先被调用，
	// 之后执行的Action，View可以通过 userForm 获取该方法的返回值。
	@ModelAttribute("userForm")
	public UserForm createUserFrom() {
		return new UserForm();
	}

	@ModelAttribute("searchForm")
	public UserSearchForm searchUserForm() {
		return new UserSearchForm();
	}

	@ModelAttribute("sexTypes")
	public List<KV> sexTypes() {
		List<KV> dataList = new ArrayList<KV>();
		dataList.add(new KV("M", "男"));
		dataList.add(new KV("F", "女"));
		return dataList;
	}

	@ModelAttribute("allHobbys")
	public List<KV> allHobbys() {
		List<KV> dataList = new ArrayList<KV>();
		dataList.add(new KV("H1", "足球"));
		dataList.add(new KV("H2", "篮球"));
		dataList.add(new KV("H3", "棒球"));
		dataList.add(new KV("H4", "象棋"));
		return dataList;
	}

	@ModelAttribute("years")
	public List<Integer> years() {
		List<Integer> dataList = new ArrayList<Integer>();
		for (int i = 2015; i > 1980; i--) {
			dataList.add(i);
		}
		return dataList;
	}

	@ModelAttribute("months")
	public List<Integer> months() {
		List<Integer> dataList = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			dataList.add(i);
		}
		return dataList;
	}

	@ModelAttribute("days")
	public List<Integer> days() {
		List<Integer> dataList = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++) {
			dataList.add(i);
		}
		return dataList;
	}

	/**
	 * 用户创建
	 * 
	 * @return
	 */
	@RequestMapping(value = "/create", method = { RequestMethod.GET })
	public String createUser() {

		return "user/create";
	}

	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	public String doCreateUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult result) {

		if (result.hasErrors()) {

		}

		return "user/create";
	}

	/**
	 * 用户检索
	 * 
	 * @ModelAttribute 定义的值的获取
	 * http://stackoverflow.com/questions/3423262/what-is-modelattribute-in-spring-mvc
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public String searchUser(Model model, @ModelAttribute("allHobbys") List<KV> allHobbys,
			@ModelAttribute("sexTypes") List<KV> sexTypes) {

		List<User> dataList = userService.getAllUsers();
		model.addAttribute("users", dataList);
		
		allHobbys.add(0, new KV("", "-----"));
		sexTypes.add(0, new KV("", "-----"));

		return "user/search";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.POST })
	public String doSearchUser(@ModelAttribute("searchForm") UserSearchForm searchForm, Model model,
			@ModelAttribute("allHobbys") List<KV> allHobbys, @ModelAttribute("sexTypes") List<KV> sexTypes) {

		List<User> dataList = userService.getUsersByCondition(searchForm);
		model.addAttribute("users", dataList);

		allHobbys.add(0, new KV("", "-----"));
		sexTypes.add(0, new KV("", "-----"));
		
		return "user/search";
	}

}
