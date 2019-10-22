package com.example.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.converter.RoleConverter;
import com.example.converter.UserConverter;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.entity.UserRoleEntity;
import com.example.model.RoleModel;
import com.example.model.UserModel;
import com.example.paging.PageRequest;
import com.example.paging.Pageable;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.repository.custorm.UserRepositoryCustorm;
import com.example.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepositoryCustorm userRepositoryCustorm;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter converter;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleConverter roleConverter;

	public List<UserModel> findAll(UserModel model) {
		Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems());
		List<UserEntity> userEntities = userRepositoryCustorm.findAll(model, pageable);
		List<UserModel> models = new ArrayList<UserModel>();
		for (UserEntity item : userEntities) {
			models.add(converter.convertToDto(item));
		}

		return models;
	}

	public int countTotalItem(UserModel model) {

		return userRepositoryCustorm.getTotalItems(model).intValue();
	}

	public UserModel findOneById(Long id) {
		UserEntity entity = userRepository.findOneById(id);
		UserModel model = converter.convertToDto(entity);
		Long[] roleUsers = new Long[entity.getUserRoleEntitys().size()];
		for (int i = 0; i < entity.getUserRoleEntitys().size(); i++) {
			roleUsers[i] = entity.getUserRoleEntitys().get(i).getRole().getId();
		}
		model.setRoleUsers(roleUsers);
		return model;
	}

	public UserModel update(UserModel userModel, long id) {

		UserEntity entityOld = userRepository.findOneById(id);
		userModel.setId(id);
		userModel.setCreatedBy(entityOld.getCreatedBy());
		userModel.setCreatedDate((Timestamp) entityOld.getCreatedDate());
		UserEntity entity = converter.convertToEntity(userModel);
		entity.setUserRoleEntitys(entityOld.getUserRoleEntitys());

		List<UserRoleEntity> userRole = new ArrayList<UserRoleEntity>();

		for (Long item : userModel.getRoleUsers()) {
			UserRoleEntity roleEntity = new UserRoleEntity();
			roleEntity.setRole(roleRepository.findOne(item));
			roleEntity.setUser(entity);
			userRole.add(roleEntity);
		}

		entity.getUserRoleEntitys().clear();
		entity.getUserRoleEntitys().addAll(userRole);

		entity = userRepository.save(entity);
		return converter.convertToDto(entity);
	}

	public UserModel save(UserModel userModel) {
		UserEntity entity = converter.convertToEntity(userModel);
		entity.setPassword(passwordEncoder.encode(userModel.getPassword()));
		List<UserRoleEntity> userRole = new ArrayList<UserRoleEntity>();
		for (Long item : userModel.getRoleUsers()) {
			UserRoleEntity roleEntity = new UserRoleEntity();
			roleEntity.setRole(roleRepository.findOne(item));
			roleEntity.setUser(entity);
			userRole.add(roleEntity);
		}
		entity.setUserRoleEntitys(userRole);
		entity = userRepository.save(entity);
		return converter.convertToDto(entity);
	}

	@Override
	public UserModel updateRoleUser(UserModel userModel, long id) {
		UserEntity entityOld = userRepository.findOneById(id);
		List<UserRoleEntity> userRole = new ArrayList<UserRoleEntity>();
		for (Long item : userModel.getRoleUsers()) {
			UserRoleEntity roleEntity = new UserRoleEntity();
			roleEntity.setRole(roleRepository.findOne(item));
			roleEntity.setUser(entityOld);
			userRole.add(roleEntity);
		}
		entityOld.getUserRoleEntitys().clear();
		entityOld.getUserRoleEntitys().addAll(userRole);
		entityOld = userRepository.save(entityOld);
		return converter.convertToDto(entityOld);
	}

	/*
	 * @Override public Map<RoleModel,String > getRoleByUser(long id) {
	 * Map<RoleModel, String> maps = new HashMap<>(); List<RoleEntity> roleEntities
	 * = roleRepository.findAll(); for (RoleEntity item : roleEntities) { maps.put(
	 * roleConverter.convertToDto(item),"checked"); } List<UserRoleEntity>
	 * finRoleByUsers = userRepository.findOneById(id).getUserRoleEntitys();
	 * 
	 * 
	 * for (Map.Entry<RoleModel, String> entry : maps.entrySet()) { finRoleByUsers.
	 * }
	 * 
	 * 
	 * 
	 * return null; }
	 */

}
