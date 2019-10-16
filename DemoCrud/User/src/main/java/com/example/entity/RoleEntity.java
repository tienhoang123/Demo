package com.example.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {
	private static final long serialVersionUID = -6982320088331012164L;

	@Column
	private String name;

	@Column
	private String code;

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<UserRoleEntity> UserRoleEntitys;

	public List<UserRoleEntity> getUserRoleEntitys() {
		return UserRoleEntitys;
	}

	public void setUserRoleEntitys(List<UserRoleEntity> userRoleEntitys) {
		UserRoleEntitys = userRoleEntitys;
	}

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

}
