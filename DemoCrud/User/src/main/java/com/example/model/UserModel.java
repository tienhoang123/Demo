package com.example.model;

import javax.persistence.Column;

public class UserModel extends AbstractModel<UserModel> {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private int status;
	private String nameRole;
	private Long[] roleUsers = new Long[] {};

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public Long[] getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Long[] roleUsers) {
		this.roleUsers = roleUsers;
	}

}
