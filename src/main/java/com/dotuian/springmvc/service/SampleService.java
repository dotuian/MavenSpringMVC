package com.dotuian.springmvc.service;

import java.util.List;

import com.dotuian.springmvc.dto.XmlUser;
import com.dotuian.springmvc.web.forms.User;

public interface SampleService {

	public List<String> getStringList();

	public int[] getArray();
	
	public User getUserById(long userid);
	
	public List<User> getAllUsers();
	

	public XmlUser getXmlUserById(long userid);
	
	public List<XmlUser> getAllXmlUsers();
	
}
