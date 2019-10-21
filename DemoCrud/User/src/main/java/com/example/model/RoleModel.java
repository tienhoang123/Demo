package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

public class RoleModel extends AbstractModel<RoleModel> {

	private static final long serialVersionUID = -3274233698008694565L;

	private String name;

	private String code;
	
	private List<UserModel> userModels = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<UserModel> getUserModels() {
		return userModels;
	}

	public void setUserModels(List<UserModel> userModels) {
		this.userModels = userModels;
	}
	
	
}
