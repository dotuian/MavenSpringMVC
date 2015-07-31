package com.dotuian.springmvc.web.site.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

	@Size(min=3, max=20)
	@NotNull
	private String j_username;
	
	@NotNull
	@Size(min=3, max=15)
	private String j_password;
	
	private boolean _spring_security_remember_me = false;

	/**
	 * @return the j_username
	 */
	public String getJ_username() {
		return j_username;
	}

	/**
	 * @param j_username the j_username to set
	 */
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	/**
	 * @return the j_password
	 */
	public String getJ_password() {
		return j_password;
	}

	/**
	 * @param j_password the j_password to set
	 */
	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

	/**
	 * @return the _spring_security_remember_me
	 */
	public boolean is_spring_security_remember_me() {
		return _spring_security_remember_me;
	}

	/**
	 * @param _spring_security_remember_me the _spring_security_remember_me to set
	 */
	public void set_spring_security_remember_me(boolean _spring_security_remember_me) {
		this._spring_security_remember_me = _spring_security_remember_me;
	}
	
}
