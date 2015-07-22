package com.dotuian.springmvc.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dotuian.springmvc.web.forms.LoginForm;

@Component
public class LoginFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		LoginForm loginForm = (LoginForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.loginForm.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.loginForm.password");

	}

}
