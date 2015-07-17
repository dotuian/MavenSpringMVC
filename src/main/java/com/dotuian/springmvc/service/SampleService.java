package com.dotuian.springmvc.service;

import java.util.List;

import com.dotuian.springmvc.web.forms.User;

public interface SampleService {

	public List<Long> getStringList();

	public int[] getArray();
	
	public User getUserById(long userid);
	
	public List<User> getAllUsers();
}
