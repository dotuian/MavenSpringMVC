package com.dotuian.springmvc.web.sample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dotuian.springmvc.service.SampleService;
import com.dotuian.springmvc.web.user.forms.User;

@Controller
@RequestMapping(value = "/json")
public class JsonController {

	// 自动装配
	@Autowired
	private SampleService sampleService;

	// 返回Json字符串
	// =============== JSON ===============
	/**
	 * http://127.0.0.1:8080/springmvc/json/user/1.json
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{id}.json", method = RequestMethod.GET)
	public User getUser(@PathVariable int id) {
		User user = sampleService.getUserById(id);
		return user;
	}

	/**
	 * http://127.0.0.1:8080/springmvc/json/users.json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/users.json", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		List<User> users = sampleService.getAllUsers();
		return users;
	}

}
