package com.dotuian.springmvc.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dotuian.springmvc.web.forms.UserForm;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	// 在该Controller中的Action执行之前，该方法会先先被调用，
	// 之后执行的Action，View可以通过 userForm 获取该方法的返回值。
	@ModelAttribute("userForm")
	public UserForm populateVarieties() {
		return new UserForm();
	}
	
	@ModelAttribute("sexTypes")
	public List<String> sexTypes() {
		List<String> dataList = new ArrayList<String>();
		dataList.add("男");
		dataList.add("女");
		return dataList;
	}
	
	@ModelAttribute("allHobbys")
	public List<String> allHobbys() {
		List<String> dataList = new ArrayList<String>();
		dataList.add("足球");
		dataList.add("篮球");
		dataList.add("棒球");
		dataList.add("象棋");
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
	 * @return
	 */
	@RequestMapping(value="/create", method = { RequestMethod.GET })
	public String createUser() {

		return "user/create";
	}

	@RequestMapping(value="/create", method = { RequestMethod.POST })
	public String doCreateUser(@ModelAttribute("userForm") @Valid UserForm userForm,
			BindingResult result) {
		
		if(result.hasErrors()) {
			
		}
		
		return "user/create";
	}
	
	/**
	 * 用户检索
	 * @return
	 */
	@RequestMapping(value="/search", method = { RequestMethod.GET })
	public String searchUser() {

		return "user/search";
	}

	@RequestMapping(value="/search", method = { RequestMethod.POST })
	public String doSearchUser(UserForm userForm) {
		
		
		return "user/search";
	}
	
	
	
	
	
	
	
}
