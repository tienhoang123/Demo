package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEntity role;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

}
