package com.dotuian.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;

import com.dotuian.springmvc.dto.XmlUser;
import com.dotuian.springmvc.service.SampleService;
import com.dotuian.springmvc.web.forms.User;

@Service
@XmlRootElement(name = "student-list")
public class SampleServiceImpl implements SampleService {

	@Override
	public List<String> getStringList() {
		List<String> dataList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			dataList.add(String.valueOf(RandomUtils.nextLong()));
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

	@javax.xml.bind.annotation.XmlElement(name = "xmluser")  
	public List<XmlUser> getAllXmlUsers() {
		List<XmlUser> users = new ArrayList<XmlUser>();

		for (int i = 0; i < 20; i++) {
			XmlUser user = new XmlUser();
			user.setUserid(RandomUtils.nextInt());
			user.setUsername("Name_" + String.valueOf(RandomUtils.nextInt()));
			user.setAge(RandomUtils.nextInt());
			user.setSex(RandomUtils.nextInt(2));
			users.add(user);
		}
		return users;
	}

	public XmlUser getXmlUserById(long userid) {
		XmlUser user = new XmlUser();
		user.setUserid(RandomUtils.nextInt());
		user.setUsername("Name_" + String.valueOf(RandomUtils.nextInt()));
		user.setAge(RandomUtils.nextInt());
		user.setSex(RandomUtils.nextInt(2));
		return user;

	}

}
