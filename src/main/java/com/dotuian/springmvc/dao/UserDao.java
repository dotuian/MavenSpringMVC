package com.dotuian.springmvc.dao;

import java.util.List;

import com.dotuian.springmvc.domain.User;
import com.dotuian.springmvc.web.user.forms.UserSearchForm;

public interface UserDao extends BaseDao {

	public User createUser(String username, String password);

	public List<User> getAllUsers();

	public List<User> getUsersByCondition(UserSearchForm searchForm);

	public User deleteUserById(long id);

	public User updateUser(User newUser);

	public User getUserById(long id);

}
