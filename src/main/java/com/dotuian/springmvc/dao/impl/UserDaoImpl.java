package com.dotuian.springmvc.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dotuian.springmvc.dao.UserDao;
import com.dotuian.springmvc.domain.User;
import com.dotuian.springmvc.web.user.forms.UserSearchForm;


/**
 * @Repository : 指明该类是一个DAO
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	protected Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	@Override
	public User createUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		this.getCurrentSession().save(user);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> dataList = (List<User>) (this.getCurrentSession().createCriteria(User.class).list());
		return dataList;
	}
	
	@Override
	public List<User> getUsersByCondition(UserSearchForm searchForm) {

		Criteria criteria = this.getCurrentSession().createCriteria(User.class);

		if (!StringUtils.isEmpty(searchForm.getEmail())) {
			criteria.add(Restrictions.like("email", "%" + searchForm.getEmail() + "%"));
		}

		if (!StringUtils.isEmpty(searchForm.getUsername())) {
			criteria.add(Restrictions.like("username", "%" + searchForm.getUsername() + "%"));
		}

		if (!StringUtils.isEmpty(searchForm.getPassword())) {
			criteria.add(Restrictions.like("password", "%" + searchForm.getPassword() + "%"));
		}

		if (!StringUtils.isEmpty(searchForm.getHobby())) {
			criteria.add(Restrictions.like("hobbys", "%" + searchForm.getHobby() + "%"));
		}

		if (!StringUtils.isEmpty(searchForm.getSex())) {
			criteria.add(Restrictions.eq("sex", searchForm.getSex()));
		}

		if (!StringUtils.isEmpty(searchForm.getBirthday())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date minDate = formatter.parse(searchForm.getBirthday());
			    Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));
			    
//			    Conjunction and = Restrictions.conjunction();
			    criteria.add( Restrictions.ge("birthday", minDate) );
			    criteria.add( Restrictions.lt("birthday", maxDate) ); 

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return (List<User>) criteria.list();
	}
	
	
	@Override
	public User deleteUserById(long id) {
		User user = this.getUserById(id);
		this.getCurrentSession().delete(user);
		return user;
	}

	@Override
	public User updateUser(User newUser) {
		this.getCurrentSession().update(newUser);
		return newUser;
	}

	@Override
	public User getUserById(long id) {
		return (User) this.getCurrentSession().get(User.class, id);
	}

}
