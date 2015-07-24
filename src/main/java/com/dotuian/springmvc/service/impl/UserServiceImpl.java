package com.dotuian.springmvc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dotuian.springmvc.dao.UserDao;
import com.dotuian.springmvc.domain.User;
import com.dotuian.springmvc.service.UserService;
import com.dotuian.springmvc.web.user.forms.UserSearchForm;

@Service
public class UserServiceImpl implements UserService {

	protected Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return this.userDao.getAllUsers();
	}
	
	@Override
	public List<User> getUsersByCondition(UserSearchForm searchForm) {
		
		return this.userDao.getUsersByCondition(searchForm);
	}

}
