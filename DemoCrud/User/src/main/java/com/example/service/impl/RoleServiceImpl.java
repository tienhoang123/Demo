package com.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.RoleEntity;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	public Map<Long, String> findAllRole() {
		Map<Long, String> maps = new HashMap<Long, String>();
		List<RoleEntity> roleEntities = repository.findAll();
		for (RoleEntity item : roleEntities) {
			maps.put(item.getId(), item.getName());
		}
		return maps;
	}

}
