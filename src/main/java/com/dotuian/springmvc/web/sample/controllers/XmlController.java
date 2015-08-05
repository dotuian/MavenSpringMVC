package com.dotuian.springmvc.web.sample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dotuian.springmvc.service.SampleService;
import com.dotuian.springmvc.service.dto.XmlUser;
import com.dotuian.springmvc.service.dto.XmlUserList;

@Controller
@RequestMapping(value = "/xml")
public class XmlController {
	
	// 自动装配
	@Autowired
	private SampleService sampleService;
	
	// 返回XML字符串
	// =============== XML ===============
	/**
	 * http://127.0.0.1:8080/springmvc//xml/user/{id}.xml
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{id}.xml", method = RequestMethod.GET)
	public XmlUser getXmlUser(@PathVariable int id) {
		XmlUser user = sampleService.getXmlUserById(id);
		return user;
	}
	
	/**
	 * 
	 * http://127.0.0.1:8080/springmvc/xml/users.xml
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/users.xml", method = RequestMethod.GET)
	public XmlUserList getAllXmlUsers() {
		List<XmlUser> users = sampleService.getAllXmlUsers();
		return new XmlUserList(users);
	}

	
}
