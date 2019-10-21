package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.entity.RoleEntity;
import com.example.model.RoleModel;

public interface RoleService {

	Map<Long, String> findAllRole();
	List<RoleModel> findAllRoleByUser();
	List<RoleModel> findRoleByUser(long userID);
}
