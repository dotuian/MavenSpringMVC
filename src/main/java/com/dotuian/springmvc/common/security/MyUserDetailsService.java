package com.dotuian.springmvc.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(MyUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("==>>> MyUserDetailsService#loadUserByUsername --- username = " + username);

		UserInfo user = new UserInfo("admin", "password", true, this.findUserAuthorities());
		user.setEmail("dotuian@outlook.com");

		return user;
	}

	public Collection<GrantedAuthority> findUserAuthorities() {
		List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();

		autthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		autthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return autthorities;
	}

}
