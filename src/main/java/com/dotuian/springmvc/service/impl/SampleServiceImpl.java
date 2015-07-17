package com.dotuian.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;

import com.dotuian.springmvc.service.SampleService;
import com.dotuian.springmvc.web.forms.User;


@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public List<Long> getStringList() {
		List dataList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			dataList.add(RandomUtils.nextLong());
		}

		return dataList;
	}

	@Override
	public int[] getArray() {

		int[] temp = new int[10];
		for (int i = 0; i < 10; i++) {
			temp[i] = RandomUtils.nextInt();
		}

		return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setUserid(RandomUtils.nextInt());
			user.setUsername("Name_" + String.valueOf(RandomUtils.nextInt()));
			user.setAge(RandomUtils.nextInt());
			user.setSex(RandomUtils.nextInt(2));
			users.add(user);
		}
		return users;
	}
	
	@Override
	public User getUserById(long userid) {
		User user = new User();
		user.setUserid(RandomUtils.nextInt());
		user.setUsername("Name_" + String.valueOf(RandomUtils.nextInt()));
		user.setAge(RandomUtils.nextInt());
		user.setSex(RandomUtils.nextInt(2));
		return user;
	}
	
}
