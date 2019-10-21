package com.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.converter.UserConverter;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.entity.UserRoleEntity;
import com.example.model.RoleModel;
import com.example.model.UserModel;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserRepository userRepository;

	public Map<Long, String> findAllRole() {
		Map<Long, String> maps = new HashMap<Long, String>();
		List<RoleEntity> roleEntities = repository.findAll();
		for (RoleEntity item : roleEntities) {
			maps.put(item.getId(), item.getName());
		}
		return maps;
	}

	@Override
	public List<RoleModel> findAllRoleByUser() {
		List<RoleModel> models = new ArrayList<RoleModel>();
		List<RoleEntity> roleEntities =  repository.findAll();
		for (RoleEntity item : roleEntities) {
			RoleModel model = new RoleModel();
			model.setId(item.getId());
			model.setName(item.getName());
			List<UserModel> userModels = new ArrayList<>();
			for (UserRoleEntity userRoleEntity : item.getUserRoleEntitys()) {
				UserModel userModel = userConverter.convertToDto(userRoleEntity.getUser());
				userModels.add(userModel);
			}
			model.setUserModels(userModels);
			models.add(model);
		}
		return models;
	}

	@Override
	public List<RoleModel> findRoleByUser(long userID) {
		List<RoleModel> models = new ArrayList<RoleModel>();
		UserEntity entity = userRepository.findOne(userID);
		for (UserRoleEntity item : entity.getUserRoleEntitys()) {
			RoleModel roleModel = new RoleModel();
			roleModel.setId(item.getRole().getId());
			roleModel.setCode(item.getRole().getCode());
			roleModel.setName(item.getRole().getName());
			models.add(roleModel);
		}
		
		return models;
	}

}
