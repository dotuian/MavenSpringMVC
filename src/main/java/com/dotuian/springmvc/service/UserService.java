package com.dotuian.springmvc.service;

import java.util.List;

import com.dotuian.springmvc.domain.User;
import com.dotuian.springmvc.web.user.forms.UserSearchForm;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public List<User> getUsersByCondition(UserSearchForm searchForm);
	
	
}
