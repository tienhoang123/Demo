package com.example.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.model.UserModel;

@PropertySource("classpath:application.properties")
public class UserValidator implements Validator {

	@Autowired
	private Environment environment;

	public boolean supports(Class<?> clazz) {
		return UserModel.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserModel userModel = (UserModel) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", environment.getProperty("field.required"));

		if (userModel.getEmail() == null || userModel.getEmail().length() == 0) {
			errors.rejectValue("email", environment.getProperty("field.required"));
		} else if (userModel.getEmail().length() < 6 || userModel.getEmail().length() > 12) {
			errors.rejectValue("email", environment.getProperty("email.required"));
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", environment.getProperty("field.required"));
		
		if(userModel.getRoleUsers().length == 0) {			
			errors.rejectValue("roleUsers", environment.getProperty("roleUsers.required"));
		}

	}
}
