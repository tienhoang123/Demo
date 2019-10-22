package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.model.RoleModel;
import com.example.model.UserModel;

public interface UserService {

	public List<UserModel> findAll(UserModel model);
	public int countTotalItem(UserModel model);
	public UserModel findOneById(Long id);
	public UserModel update(UserModel userModel, long id);
	public UserModel save(UserModel userModel);
	public UserModel updateRoleUser(UserModel userModel, long id);
	/* public Map<RoleModel,String> getRoleByUser(long id); */
}
