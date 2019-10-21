package com.example.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity
{
    private static final long serialVersionUID = -5426562322680764256L;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private  String email;

    @Column
    private  String phone;

    @Column
    private int status;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<UserRoleEntity> UserRoleEntitys;

	public List<UserRoleEntity> getUserRoleEntitys() {
		return UserRoleEntitys;
	}

	public void setUserRoleEntitys(List<UserRoleEntity> userRoleEntitys) {
		UserRoleEntitys = userRoleEntitys;
	}

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
}